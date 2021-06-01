package com.example.cfvirtual.ui;

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cfvirtual.entities.Contestant;
import com.example.cfvirtual.entities.Party;
import com.example.cfvirtual.entities.StandingsRow;
import com.example.cfvirtual.entities.StandingsRows;
import com.example.cfvirtual.entities.StandingsRowsStatus;
import com.example.cfvirtual.entities.Submission;
import com.example.cfvirtual.entities.SubmissionsListStatus;
import com.example.cfvirtual.retrofit.CfvirtualApiClient;
import com.example.cfvirtual.retrofit.CodeforcesApiClient;
import com.example.cfvirtual.utility.Constants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RatingGeneratorViewModel extends ViewModel {
    private Retrofit codeforcesRetrofit;
    private Retrofit cfvirtualRetrofit;
    private CodeforcesApiClient codeforcesApiClient;
    private CfvirtualApiClient cfvirtualApiClient;
    MutableLiveData<Pair<Integer, Integer>> contestsCountLiveData = new MutableLiveData<>();
    MutableLiveData<ItemContest> retrievedItemContestLiveData = new MutableLiveData<>();
    MutableLiveData<List<Contestant>> contestantsListLiveData = new MutableLiveData<>();

    private String handle;
    private List<Integer> contestIdList;
    private int retrievedTillNow = 0;

    private String initialRating;
    private String contestInfo;

    public void start(String handle){
        this.handle = handle;

        retrievedTillNow = 0;
        initRetrofit();
        observeVirtualContestList();
    }

    public void startCfvirtual(String initialRating, String contestInfo){
        this.initialRating = initialRating;
        this.contestInfo = contestInfo;

        observeCfvirtualRatings();

    }

    private void initRetrofit(){
        codeforcesRetrofit = new Retrofit.Builder()
                .baseUrl(CodeforcesApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        codeforcesApiClient = codeforcesRetrofit.create(CodeforcesApiClient.class);

        cfvirtualRetrofit = new Retrofit.Builder()
                .baseUrl(CfvirtualApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        cfvirtualApiClient = cfvirtualRetrofit.create(CfvirtualApiClient.class);
    }



    private void observeCfvirtualRatings(){
        getCfvirtualRatingsObservabe().observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<List<Contestant>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Contestant> contestants) {
                        Log.v("Nitin", "cfvirtual api contestants total " + contestants.size());
                        contestantsListLiveData.postValue(contestants);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    private Observable<List<Contestant>> getCfvirtualRatingsObservabe(){
        Observable<List<Contestant>> observable = Observable.create(new ObservableOnSubscribe<List<Contestant>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Contestant>> emitter) throws Exception {
                Call<List<Contestant>> contestantsCall = cfvirtualApiClient.getVirtualRatings(initialRating, contestInfo);
                Response<List<Contestant>> contestantsListResponse = contestantsCall.clone().execute();

                if(contestantsListResponse.isSuccessful() && contestantsListResponse.body()!=null){
                    List<Contestant> contestantsList = contestantsListResponse.body();
                    emitter.onNext(contestantsList);
                    emitter.onComplete();

                }
            }

        }).subscribeOn(Schedulers.newThread());

        return observable;
    }



    private void observeVirtualContestList() {
        getVirtualContestIdsObservable().observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> contestIdList) {
                        Log.v("Nitin", "contestIds retrieved");
                        if (contestIdList != null) {
                            RatingGeneratorViewModel.this.contestIdList = new ArrayList<>();
                            for(int contestId: contestIdList){
                                RatingGeneratorViewModel.this.contestIdList.add(contestId);
                            }

                            observeItemContest(contestIdList);

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private Observable<List<Integer>> getVirtualContestIdsObservable() {
        Observable<List<Integer>> virtualContestIdsObservable = Observable.create(new ObservableOnSubscribe<List<Integer>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Integer>> emitter) throws Exception {
                Log.v("Nitin", "request for submissions");

                Call<SubmissionsListStatus> submissionsListStatusCall = codeforcesApiClient.getSubmission(handle).clone();
                Response<SubmissionsListStatus> submissionsListStatusResponse = submissionsListStatusCall.execute();

                Log.v("Nitin", "response of submissions");

                if (submissionsListStatusResponse.isSuccessful()) {
                    SubmissionsListStatus submissionsListStatus = submissionsListStatusResponse.body();
                    if (submissionsListStatus != null) {
                        if (submissionsListStatus.getSubmissions() != null) {
                            List<Integer> contestIds = getVirtualContests(submissionsListStatus.getSubmissions());
                            if (!emitter.isDisposed()) {
                                emitter.onNext(contestIds);
                                emitter.onComplete();
                            }
                        }
                    }
                }
            }
        }).subscribeOn(Schedulers.newThread());

        return virtualContestIdsObservable;
    }

    public List<Integer> getVirtualContests(List<Submission> submissionsList) {
        ArrayList<Integer> virtualContestIds = new ArrayList<>();
        if (submissionsList != null) {
            for (int i = submissionsList.size() - 1; i >= 0; i--) {
                Submission submission = submissionsList.get(i);
                if (Constants.VIRTUAL.equals(submission.getAuthor().getParticipantType())) {
                    if (virtualContestIds.size() == 0 || virtualContestIds.get(virtualContestIds.size() - 1) != submission.getContestId()) {
                        virtualContestIds.add(submission.getContestId());
                    }
                }
            }
        }
        return virtualContestIds;
    }





    private void observeItemContest(final List<Integer> contestIdList) {
        getItemContestObservable(contestIdList).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<ItemContest>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ItemContest itemContest) {
                        Log.v("Nitin", "new contestInfo retrieved");
                        retrievedTillNow++;
                        contestsCountLiveData.postValue(new Pair<Integer, Integer>(retrievedTillNow, contestIdList.size()));
                        retrievedItemContestLiveData.postValue(itemContest);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    private Observable<ItemContest> getItemContestObservable(final List<Integer> contestIdList) {
        Observable<ItemContest> contestInfoObservable = Observable.create(new ObservableOnSubscribe<ItemContest>() {
            @Override
            public void subscribe(ObservableEmitter<ItemContest> emitter) throws Exception {

                for (int i = 0; i < contestIdList.size(); i++) {
                    int contestId = contestIdList.get(i);

                    Log.v("Nitin", "contest API call started " + contestId);
                    Call<StandingsRowsStatus> virtuaStandingsRowsStatusCall = codeforcesApiClient.getVirtualParticipantStanding(String.valueOf(contestId), true, handle);
                    Response<StandingsRowsStatus> virtualStandingsRowsStatusResponse = virtuaStandingsRowsStatusCall.clone().execute();

                    Log.v("Nitin", "response");
                    if (virtualStandingsRowsStatusResponse.isSuccessful()
                            && virtualStandingsRowsStatusResponse.body()!=null) {

                        StandingsRow virtualStandingRow = getVirtualPaticipantStandingsRow(virtualStandingsRowsStatusResponse.body());
                        ItemContest itemContest = new ItemContest(contestId, virtualStandingRow.getRank(), virtualStandingRow.getPoints());
                        emitter.onNext(itemContest);

                    } else {
                        Log.v("Nitin", contestId + " virtual standings reponse code " + virtualStandingsRowsStatusResponse.code());
//                        ContestInfo contestInfo = new ContestInfo(contestId);
//                        contestInfo.setStatus(2);
//                        emitter.onNext(new Pair<>(i, contestInfo));
                    }

                    Thread.sleep(250);

                }
                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.newThread());

        return contestInfoObservable;
    }

    private List<StandingsRow> getStandingsRowList(StandingsRowsStatus standingsRowsStatus) {
        return standingsRowsStatus.getStandingsRows().getRows();
    }

    private StandingsRow getVirtualPaticipantStandingsRow(StandingsRowsStatus standingsRowsStatus) {
        StandingsRows standingsRows = standingsRowsStatus.getStandingsRows();
        List<StandingsRow> rowList = standingsRows.getRows();
        for (StandingsRow standingsRow : rowList) {
            Party party = standingsRow.getParty();
            if (party.getParticipantType().equals(Constants.VIRTUAL)) {
                return standingsRow;
            }
        }

        return null;
    }

    public MutableLiveData<Pair<Integer, Integer>> getContestsCountLiveData() {
        return contestsCountLiveData;
    }

    public MutableLiveData<ItemContest> getRetrievedItemContestLiveData() {
        return retrievedItemContestLiveData;
    }

    public MutableLiveData<List<Contestant>> getContestantsListLiveData() {
        return contestantsListLiveData;
    }
}

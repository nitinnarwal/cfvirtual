package com.example.cfvirtual.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cfvirtual.R;
import com.example.cfvirtual.entities.Contestant;

import java.util.ArrayList;
import java.util.List;

public class RatingGeneratorFragment extends Fragment {
    private Context mContext;
    private RatingGeneratorViewModel mViewModel;

    private EditText handleET;
    private Button fetchContestsButton;
    private RecyclerView recyclerView;
    private Button generateRatingButton;
    private TextView allStatusTV;

    private RatingGeneratorAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mViewModel = ViewModelProviders.of(this).get(RatingGeneratorViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating_generator, container, false);
        initViews(view);
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setObservers();
        setListeners();
    }

    private void initViews(View view){
        handleET = view.findViewById(R.id.handle_et);
        fetchContestsButton = view.findViewById(R.id.fetch_vc);
        recyclerView = view.findViewById(R.id.recycler_view);
        generateRatingButton = view.findViewById(R.id.generate_rating_button);
        allStatusTV = view.findViewById(R.id.all_status);
    }

    private void initRecyclerView(){
        adapter = new RatingGeneratorAdapter(mContext);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setObservers(){
        mViewModel.getRetrievedItemContestLiveData().observe(this, new Observer<ItemContest>() {
            @Override
            public void onChanged(ItemContest itemContest) {
                adapter.addItemContest(itemContest);
            }
        });

        mViewModel.getContestsCountLiveData().observe(this, new Observer<Pair<Integer, Integer>>() {
            @Override
            public void onChanged(Pair<Integer, Integer> pair) {
                allStatusTV.setText(pair.first + "/" + pair.second + " contests retreived");
                if(pair.first == pair.second){
                    generateRatingButton.setVisibility(View.VISIBLE);
                }
            }
        });

        mViewModel.getContestantsListLiveData().observe(this, new Observer<List<Contestant>>() {
            @Override
            public void onChanged(List<Contestant> contestants) {
                List<ItemContest> itemContestList = new ArrayList<>();
                for(Contestant contestant : contestants){
                    ItemContest itemContest = new ItemContest(1111, (int)contestant.rank, (int)contestant.points);
                    itemContest.setCalculated(true);
                    itemContest.setChecked(true);
                    itemContest.setPrevRating(contestant.rating);
                    itemContest.setNextrating(contestant.rating + contestant.delta);

                    itemContestList.add(itemContest);
                }

                adapter.setData(itemContestList);
            }
        });
    }

    private void setListeners(){
        fetchContestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String handle = handleET.getText().toString();
                if(handle.isEmpty()){
                    Toast.makeText(mContext, "Enter handle please", Toast.LENGTH_SHORT).show();
                } else{
                    mViewModel.start(handle);
                    adapter.resetData();
                    generateRatingButton.setVisibility(View.GONE);
                }
            }
        });

        generateRatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ItemContest> itemContestList = adapter.getItemContestList();
                StringBuilder stringBuilder = new StringBuilder("");
                for(ItemContest itemContest : itemContestList){
                    if(itemContest.isChecked()){
                        stringBuilder.append(itemContest.getContestId()).append(",").append(itemContest.getPoints()).append(";");
                    }
                }

                mViewModel.startCfvirtual(String.valueOf(1500), stringBuilder.toString());


            }
        });
    }
}

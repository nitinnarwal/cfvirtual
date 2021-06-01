package com.example.cfvirtual.retrofit;

import com.example.cfvirtual.entities.StandingsRowsStatus;
import com.example.cfvirtual.entities.SubmissionsListStatus;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CodeforcesApiClient {
    String BASE_URL = "https://codeforces.com/api/";

    @GET("user.status")
    Call<SubmissionsListStatus> getSubmission(@Query("handle") String handle);

    @GET("contest.standings")
    Call<StandingsRowsStatus> getVirtualParticipantStanding(@Query("contestId") String contestId, @Query("showUnofficial") boolean showUnofficial, @Query("handles") String handle);
}

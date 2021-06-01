package com.example.cfvirtual.retrofit;

import com.example.cfvirtual.entities.Contestant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CfvirtualApiClient {
    String BASE_URL = "http://ec2-54-226-254-6.compute-1.amazonaws.com:8080/";

    @GET("virtual_rating")
    Call<List<Contestant>> getVirtualRatings(@Query("initial_rating") String initialRating, @Query("contestInfo") String contestInfoList);
}

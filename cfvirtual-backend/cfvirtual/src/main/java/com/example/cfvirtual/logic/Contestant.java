package com.example.cfvirtual.logic;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Contestant implements Serializable {
    @SerializedName("handle")
    public String handle;

    @SerializedName("rank")
    public double rank;

    @SerializedName("points")
    public double points;

    @SerializedName("rating")
    public int rating;

    @SerializedName("needRating")
    public int needRating;

    @SerializedName("seed")
    public double seed;

    @SerializedName("delta")
    public int delta;

     public Contestant(String handle, int rank, double points, int rating) {
        this.handle = handle;
        this.rank = rank;
        this.points = points;
        this.rating = rating;
    }
}

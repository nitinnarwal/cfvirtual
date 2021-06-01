package com.example.cfvirtual.entities;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class RatingChange implements Serializable {

    @SerializedName("oldRating")
    private int oldRating;

    @SerializedName("contestId")
    private int contestId;

    @SerializedName("ratingUpdateTimeSeconds")
    private int ratingUpdateTimeSeconds;

    @SerializedName("newRating")
    private int newRating;

    @SerializedName("rank")
    private int rank;

    @SerializedName("handle")
    private String handle;

    @SerializedName("contestName")
    private String contestName;

    public void setOldRating(int oldRating){
        this.oldRating = oldRating;
    }

    public int getOldRating(){
        return oldRating;
    }

    public void setContestId(int contestId){
        this.contestId = contestId;
    }

    public int getContestId(){
        return contestId;
    }

    public void setRatingUpdateTimeSeconds(int ratingUpdateTimeSeconds){
        this.ratingUpdateTimeSeconds = ratingUpdateTimeSeconds;
    }

    public int getRatingUpdateTimeSeconds(){
        return ratingUpdateTimeSeconds;
    }

    public void setNewRating(int newRating){
        this.newRating = newRating;
    }

    public int getNewRating(){
        return newRating;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public int getRank(){
        return rank;
    }

    public void setHandle(String handle){
        this.handle = handle;
    }

    public String getHandle(){
        return handle;
    }

    public void setContestName(String contestName){
        this.contestName = contestName;
    }

    public String getContestName(){
        return contestName;
    }

    public int getChange() {
        return newRating-oldRating;
    }

    @Override
    public String toString(){
        return
                "RatingChange{" +
                        "oldRating = '" + oldRating + '\'' +
                        ",contestId = '" + contestId + '\'' +
                        ",ratingUpdateTimeSeconds = '" + ratingUpdateTimeSeconds + '\'' +
                        ",newRating = '" + newRating + '\'' +
                        ",rank = '" + rank + '\'' +
                        ",handle = '" + handle + '\'' +
                        ",contestName = '" + contestName + '\'' +
                        "}";
    }
}
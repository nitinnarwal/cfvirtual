package com.company.entities;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Problem implements Serializable {

    @SerializedName("contestId")
    private int contestId;

    @SerializedName("index")
    private String index;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("points")
    private double points;

    @SerializedName("rating")
    private int rating;

    @SerializedName("tags")
    private List<String> tags;

    public void setContestId(int contestId){
        this.contestId = contestId;
    }

    public int getContestId(){
        return contestId;
    }

    public void setIndex(String index){
        this.index = index;
    }

    public String getIndex(){
        return index;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setPoints(double points){
        this.points = points;
    }

    public double getPoints(){
        return points;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public int getRating(){
        return rating;
    }

    public void setTags(List<String> tags){
        this.tags = tags;
    }

    public List<String> getTags(){
        return tags;
    }

    @Override
    public String toString(){
        return
                "Problem{" +
                        "contestId = '" + contestId + '\'' +
                        ",index = '" + index + '\'' +
                        ",name = '" + name + '\'' +
                        ",type = '" + type + '\'' +
                        ",points = '" + points + '\'' +
                        ",rating = '" + rating + '\'' +
                        ",tags = '" + tags + '\'' +
                        "}";
    }
}
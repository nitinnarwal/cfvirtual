package com.example.cfvirtual.entities;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class StandingsRow implements Serializable {

    @SerializedName("party")
    private Party party;

    @SerializedName("rank")
    private int rank;

    @SerializedName("points")
    private double points;

    @SerializedName("penalty")
    private int penalty;

    @SerializedName("successfulHackCount")
    private int successfulHackCount;

    @SerializedName("unsuccessfulHackCount")
    private int unsuccessfulHackCount;

    @SerializedName("problemResults")
    private List<Problem> problemResults;

    public void setParty(Party party){
        this.party = party;
    }

    public Party getParty(){
        return party;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public int getRank(){
        return rank;
    }

    public void setPoints(double points){
        this.points = points;
    }

    public double getPoints(){
        return points;
    }

    public void setPenalty(int penalty){
        this.penalty = penalty;
    }

    public int getPenalty(){
        return penalty;
    }

    public void setSuccessfulHackCount(int successfulHackCount){
        this.successfulHackCount = successfulHackCount;
    }

    public int getSuccessfulHackCount(){
        return successfulHackCount;
    }

    public void setUnsuccessfulHackCount(int unsuccessfulHackCount){
        this.unsuccessfulHackCount = unsuccessfulHackCount;
    }

    public int getUnsuccessfulHackCount(){
        return unsuccessfulHackCount;
    }

    public void setProblemResults(List<Problem> problemResults){
        this.problemResults = problemResults;
    }

    public List<Problem> getProblemResults(){
        return problemResults;
    }

    @Override
    public String toString(){
        return
                "StandingsRow{" +
                        "party = '" + party + '\'' +
                        ",rank = '" + rank + '\'' +
                        ",points = '" + points + '\'' +
                        ",penalty = '" + penalty + '\'' +
                        ",successfulHackCount = '" + successfulHackCount + '\'' +
                        ",unsuccessfulHackCount = '" + unsuccessfulHackCount + '\'' +
                        ",problemResults = '" + problemResults + '\'' +
                        "}";
    }
}
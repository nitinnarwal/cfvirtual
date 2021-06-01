package com.company.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StandingsRowsStatus implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("result")
    private StandingsRows standingsRows;

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public void setStandingsRows(StandingsRows standingsRows){
        this.standingsRows = standingsRows;
    }

    public StandingsRows getStandingsRows(){
        return standingsRows;
    }

    @Override
    public String toString(){
        return
                "StandingsRowsStatus{" +
                        "status = '" + status + '\'' +
                        ",standingsRows = '" + standingsRows + '\'' +
                        "}";
    }
}

package com.example.cfvirtual.entities;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class SubmissionsListStatus implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("result")
    private List<Submission> submissions;

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public void setSubmissions(List<Submission> submissions){
        this.submissions = submissions;
    }

    public List<Submission> getSubmissions(){
        return submissions;
    }

    @Override
    public String toString(){
        return
                "SubmissionsListStatus{" +
                        "status = '" + status + '\'' +
                        ",submissions = '" + submissions + '\'' +
                        "}";
    }
}

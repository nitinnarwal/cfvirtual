package com.example.cfvirtual.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RatingChangeStatus implements Serializable {

	@SerializedName("status")
	private String status;

	@SerializedName("result")
	private List<RatingChange> ratingChangeList;

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setRatingChangeList(List<RatingChange> ratingChangeList){
		this.ratingChangeList = ratingChangeList;
	}

	public List<RatingChange> getRatingChangeList(){
		return ratingChangeList;
	}

	@Override
 	public String toString(){
		return
			"SubmissionsListStatus{" +
			"status = '" + status + '\'' +
			",submissions = '" + ratingChangeList + '\'' +
			"}";
		}
}
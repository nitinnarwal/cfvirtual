package com.example.cfvirtual.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StandingsRows implements Serializable {

	@SerializedName("contest")
	private Contest contest;

	@SerializedName("problems")
	private List<Problem> problems;

	@SerializedName("rows")
	private List<StandingsRow> rows;

	public void setContest(Contest contest){
		this.contest = contest;
	}

	public Contest getContest(){
		return contest;
	}

	public void setProblems(List<Problem> problems){
		this.problems = problems;
	}

	public List<Problem> getProblems(){
		return problems;
	}

	public void setRows(List<StandingsRow> rows){
		this.rows = rows;
	}

	public List<StandingsRow> getRows(){
		return rows;
	}

	@Override
 	public String toString(){
		return 
			"StandingsRows{" +
			"contest = '" + contest + '\'' + 
			",problems = '" + problems + '\'' + 
			",rows = '" + rows + '\'' + 
			"}";
		}
}
package com.example.cfvirtual.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Contest implements Serializable {

	@SerializedName("id")
	private int id;

	@SerializedName("name")
	private String name;

	@SerializedName("type")
	private String type;

	@SerializedName("phase")
	private String phase;

	@SerializedName("frozen")
	private boolean frozen;

	@SerializedName("durationSeconds")
	private int durationSeconds;

	@SerializedName("startTimeSeconds")
	private int startTimeSeconds;

	@SerializedName("relativeTimeSeconds")
	private int relativeTimeSeconds;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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

	public void setPhase(String phase){
		this.phase = phase;
	}

	public String getPhase(){
		return phase;
	}

	public void setFrozen(boolean frozen){
		this.frozen = frozen;
	}

	public boolean isFrozen(){
		return frozen;
	}

	public void setDurationSeconds(int durationSeconds){
		this.durationSeconds = durationSeconds;
	}

	public int getDurationSeconds(){
		return durationSeconds;
	}

	public void setStartTimeSeconds(int startTimeSeconds){
		this.startTimeSeconds = startTimeSeconds;
	}

	public int getStartTimeSeconds(){
		return startTimeSeconds;
	}

	public void setRelativeTimeSeconds(int relativeTimeSeconds){
		this.relativeTimeSeconds = relativeTimeSeconds;
	}

	public int getRelativeTimeSeconds(){
		return relativeTimeSeconds;
	}

	@Override
 	public String toString(){
		return 
			"Contest{" + 
			"id = '" + id + '\'' + 
			",name = '" + name + '\'' + 
			",type = '" + type + '\'' + 
			",phase = '" + phase + '\'' + 
			",frozen = '" + frozen + '\'' + 
			",durationSeconds = '" + durationSeconds + '\'' + 
			",startTimeSeconds = '" + startTimeSeconds + '\'' + 
			",relativeTimeSeconds = '" + relativeTimeSeconds + '\'' + 
			"}";
		}
}
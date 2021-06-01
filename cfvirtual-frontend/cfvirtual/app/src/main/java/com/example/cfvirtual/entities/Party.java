package com.example.cfvirtual.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Party implements Serializable {

	@SerializedName("contestId")
	private int contestId;

	@SerializedName("members")
	private List<MembersItem> members;

	@SerializedName("participantType")
	private String participantType;

	@SerializedName("ghost")
	private boolean ghost;

	@SerializedName("room")
	private int room;

	@SerializedName("startTimeSeconds")
	private int startTimeSeconds;

	public void setContestId(int contestId){
		this.contestId = contestId;
	}

	public int getContestId(){
		return contestId;
	}

	public void setMembers(List<MembersItem> members){
		this.members = members;
	}

	public List<MembersItem> getMembers(){
		return members;
	}

	public void setParticipantType(String participantType){
		this.participantType = participantType;
	}

	public String getParticipantType(){
		return participantType;
	}

	public void setGhost(boolean ghost){
		this.ghost = ghost;
	}

	public boolean isGhost(){
		return ghost;
	}

	public void setRoom(int room){
		this.room = room;
	}

	public int getRoom(){
		return room;
	}

	public void setStartTimeSeconds(int startTimeSeconds){
		this.startTimeSeconds = startTimeSeconds;
	}

	public int getStartTimeSeconds(){
		return startTimeSeconds;
	}

	@Override
 	public String toString(){
		return 
			"Party{" + 
			"contestId = '" + contestId + '\'' + 
			",members = '" + members.toString() + '\'' +
			",participantType = '" + participantType + '\'' + 
			",ghost = '" + ghost + '\'' + 
			",room = '" + room + '\'' + 
			",startTimeSeconds = '" + startTimeSeconds + '\'' + 
			"}";
	}

	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}
}
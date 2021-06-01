package com.example.cfvirtual.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MembersItem implements Serializable {

	@SerializedName("handle")
	private String handle;

	public void setHandle(String handle){
		this.handle = handle;
	}

	public String getHandle(){
		return handle;
	}

	@Override
 	public String toString(){
		return 
			"MembersItem{" + 
			"handle = '" + handle + '\'' + 
			"}";
	}

	public MembersItem(String handle) {
		this.handle = handle;
	}
}
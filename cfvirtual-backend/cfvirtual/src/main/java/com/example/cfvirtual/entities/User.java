package com.example.cfvirtual.entities;

import com.google.gson.annotations.SerializedName;

public class User{

    @SerializedName("contribution")
    private int contribution;

    @SerializedName("lastOnlineTimeSeconds")
    private int lastOnlineTimeSeconds;

    @SerializedName("rating")
    private int rating;

    @SerializedName("friendOfCount")
    private int friendOfCount;

    @SerializedName("titlePhoto")
    private String titlePhoto;

    @SerializedName("rank")
    private String rank;

    @SerializedName("handle")
    private String handle;

    @SerializedName("maxRating")
    private int maxRating;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("registrationTimeSeconds")
    private int registrationTimeSeconds;

    @SerializedName("maxRank")
    private String maxRank;

    public void setContribution(int contribution) {
        this.contribution = contribution;
    }

    public int getContribution() {
        return contribution;
    }

    public void setLastOnlineTimeSeconds(int lastOnlineTimeSeconds) {
        this.lastOnlineTimeSeconds = lastOnlineTimeSeconds;
    }

    public int getLastOnlineTimeSeconds() {
        return lastOnlineTimeSeconds;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setFriendOfCount(int friendOfCount) {
        this.friendOfCount = friendOfCount;
    }

    public int getFriendOfCount() {
        return friendOfCount;
    }

    public void setTitlePhoto(String titlePhoto) {
        this.titlePhoto = titlePhoto;
    }

    public String getTitlePhoto() {
        return titlePhoto;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getHandle() {
        return handle;
    }

    public void setMaxRating(int maxRating) {
        this.maxRating = maxRating;
    }

    public int getMaxRating() {
        return maxRating;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setRegistrationTimeSeconds(int registrationTimeSeconds) {
        this.registrationTimeSeconds = registrationTimeSeconds;
    }

    public int getRegistrationTimeSeconds() {
        return registrationTimeSeconds;
    }

    public void setMaxRank(String maxRank) {
        this.maxRank = maxRank;
    }

    public String getMaxRank() {
        return maxRank;
    }
}
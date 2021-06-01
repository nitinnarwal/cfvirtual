package com.company.entities;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Submission implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("contestId")
    private int contestId;

    @SerializedName("creationTimeSeconds")
    private int creationTimeSeconds;

    @SerializedName("relativeTimeSeconds")
    private int relativeTimeSeconds;

    @SerializedName("problem")
    private Problem problem;

    @SerializedName("author")
    private Party author;

    @SerializedName("programmingLanguage")
    private String programmingLanguage;

    @SerializedName("verdict")
    private String verdict;

    @SerializedName("testset")
    private String testset;

    @SerializedName("passedTestCount")
    private int passedTestCount;

    @SerializedName("timeConsumedMillis")
    private int timeConsumedMillis;

    @SerializedName("memoryConsumedBytes")
    private int memoryConsumedBytes;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setContestId(int contestId){
        this.contestId = contestId;
    }

    public int getContestId(){
        return contestId;
    }

    public void setCreationTimeSeconds(int creationTimeSeconds){
        this.creationTimeSeconds = creationTimeSeconds;
    }

    public int getCreationTimeSeconds(){
        return creationTimeSeconds;
    }

    public void setRelativeTimeSeconds(int relativeTimeSeconds){
        this.relativeTimeSeconds = relativeTimeSeconds;
    }

    public int getRelativeTimeSeconds(){
        return relativeTimeSeconds;
    }

    public void setProblem(Problem problem){
        this.problem = problem;
    }

    public Problem getProblem(){
        return problem;
    }

    public void setAuthor(Party author){
        this.author = author;
    }

    public Party getAuthor(){
        return author;
    }

    public void setProgrammingLanguage(String programmingLanguage){
        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage(){
        return programmingLanguage;
    }

    public void setVerdict(String verdict){
        this.verdict = verdict;
    }

    public String getVerdict(){
        return verdict;
    }

    public void setTestset(String testset){
        this.testset = testset;
    }

    public String getTestset(){
        return testset;
    }

    public void setPassedTestCount(int passedTestCount){
        this.passedTestCount = passedTestCount;
    }

    public int getPassedTestCount(){
        return passedTestCount;
    }

    public void setTimeConsumedMillis(int timeConsumedMillis){
        this.timeConsumedMillis = timeConsumedMillis;
    }

    public int getTimeConsumedMillis(){
        return timeConsumedMillis;
    }

    public void setMemoryConsumedBytes(int memoryConsumedBytes){
        this.memoryConsumedBytes = memoryConsumedBytes;
    }

    public int getMemoryConsumedBytes(){
        return memoryConsumedBytes;
    }

    @Override
    public String toString(){
        return
                "Submission{" +
                        "id = '" + id + '\'' +
                        ",contestId = '" + contestId + '\'' +
                        ",creationTimeSeconds = '" + creationTimeSeconds + '\'' +
                        ",relativeTimeSeconds = '" + relativeTimeSeconds + '\'' +
                        ",problem = '" + problem + '\'' +
                        ",author = '" + author + '\'' +
                        ",programmingLanguage = '" + programmingLanguage + '\'' +
                        ",verdict = '" + verdict + '\'' +
                        ",testset = '" + testset + '\'' +
                        ",passedTestCount = '" + passedTestCount + '\'' +
                        ",timeConsumedMillis = '" + timeConsumedMillis + '\'' +
                        ",memoryConsumedBytes = '" + memoryConsumedBytes + '\'' +
                        "}";
    }
}
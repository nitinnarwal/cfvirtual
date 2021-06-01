package com.example.cfvirtual.ui;

public class ItemContest {
    private int contestId;
    private int rank;
    private int points;
    private int prevRating;
    private int nextrating;
    private boolean isChecked;
    private boolean isCalculated;

    public ItemContest() {
    }

    public ItemContest(int contestId, int rank, int points) {
        this.contestId = contestId;
        this.rank = rank;
        this.points = points;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPrevRating() {
        return prevRating;
    }

    public void setPrevRating(int prevRating) {
        this.prevRating = prevRating;
    }

    public int getNextrating() {
        return nextrating;
    }

    public void setNextrating(int nextrating) {
        this.nextrating = nextrating;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isCalculated() {
        return isCalculated;
    }

    public void setCalculated(boolean calculated) {
        isCalculated = calculated;
    }
}

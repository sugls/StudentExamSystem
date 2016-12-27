package com.guigu.ses.DTO;

/**
 * Created by Lsc on 2016/12/27.
 */
public class Scores {

    private int stuno;
    private String stage;
    private String score;

    public Scores() {
    }

    public Scores(int stuno, String stage, String score) {
        this.stuno = stuno;
        this.stage = stage;
        this.score = score;
    }

    public int getStuno() {
        return stuno;
    }

    public void setStuno(int stuno) {
        this.stuno = stuno;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

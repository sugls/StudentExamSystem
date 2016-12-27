package com.guigu.ses.DTO;

import java.util.List;

/**
 * Created by Lsc on 2016/12/27.
 */
public class Questions {

    private int queno;
    private String note;
    private String detail;
    private String choice;
    private String answer;
    private String stage;

    public Questions() {
    }

    public Questions(int queno, String note, String detail, String choice, String answer, String stage) {
        this.queno = queno;
        this.note = note;
        this.detail = detail;
        this.choice = choice;
        this.answer = answer;
        this.stage = stage;
    }

    public int getQueno() {
        return queno;
    }

    public void setQueno(int queno) {
        this.queno = queno;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public static List<Questions> getQuestionsByStage(String stage){
        List<Questions> list = null;
        return list;
    }

}

package com.guigu.ses.DTO;

/**
 * Created by Lsc on 2016/12/27.
 */
public class Students {

    private int stuno;
    private String stuname;
    private String stuclass;
    private String sex;
    private String passwd;

    public Students() {
    }

    public Students(int stuno, String stuname, String stuclass, String sex, String passwd) {
        this.stuno = stuno;
        this.stuname = stuname;
        this.stuclass = stuclass;
        this.sex = sex;
        this.passwd = passwd;
    }

    public int getStuno() {
        return stuno;
    }

    public void setStuno(int stuno) {
        this.stuno = stuno;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}

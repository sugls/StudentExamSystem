package com.guigu.ses.DTO;

import com.guigu.ses.Util.DBUtil;
import com.guigu.ses.Util.parseExamXml;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 通过学生姓名和阶段获取成绩
     * @param sname 学生姓名
     * @param stage 阶段
     * @return 成绩
     */
    public static String getStuScoreByStage(String sname,String stage){
        DBUtil dbUtil = new DBUtil();
        String sql = "SELECT score FROM student NATURAL JOIN score WHERE score.stage = ? AND student.stu_name = ?";
        PreparedStatement ps = dbUtil.getPreparedStatement(sql);
        ResultSet rs = null;
        String result = null;
        try {
            ps.setString(1,stage);
            ps.setString(2,sname);
            rs = ps.executeQuery();
            while (rs.next()){
                result = String.valueOf(rs.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        dbUtil.close();
        System.out.println(sname+"   "+stage);
        System.out.println(result);
        return result;
    }

    public static double countScore(Map<String,String> answer, List<Questions> list){
        double score = 0;
        Map<String,String> correctAnswer = new HashMap<>();
        for (int i=0;i<list.size();i++) {
            correctAnswer.put(String.valueOf(i+1),list.get(i).getAnswer());
        }
        for (int i=0;i<correctAnswer.size();i++){
            if (correctAnswer.get(String.valueOf(i+1)).equals(answer.get(String.valueOf(i+1)))){
                score +=10;
            }
        }
        for (int i=0;i<correctAnswer.size();i++) {
            System.out.println(correctAnswer.get(String.valueOf(i+1)));
        }
        System.out.println("---------------");
        for (int i=0;i<answer.size();i++){
            System.out.println(answer.get(String.valueOf(i+1)));
        }
        return score;
    }

    public static void saveScore(String sno,String stage,double score){
        System.out.println(sno+"sno");
        System.out.println(stage);
        System.out.println(score+"score");
        String sql = "INSERT INTO score(stu_no, stage, score) VALUES(?,?,?)";
        DBUtil dbUtil = new DBUtil();
        PreparedStatement ps = dbUtil.getPreparedStatement(sql);
        try {
            ps.setString(1,sno);
            ps.setString(2,stage);
            ps.setInt(3,(int)score);
            System.out.println(ps.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        dbUtil.close();
    }

}

package com.guigu.ses.DTO;

import com.guigu.ses.Util.DBUtil;
import com.guigu.ses.Util.parseExamXml;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 *
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

    public static List<Questions> getQuestionsByStage(String stage) {
        List<Questions> list = new Vector<>();
        String sql = "SELECT que_no, que_note, que_detail, que_choice, que_answer FROM question WHERE stage = ?";
        DBUtil dbUtil = new DBUtil();
        ResultSet rs = null;
        PreparedStatement ps = dbUtil.getPreparedStatement(sql);
        try {
            ps.setString(1, stage);
            rs = ps.executeQuery();
            while (rs.next()) {
                Questions q = new Questions();
                q.setQueno(rs.getInt("que_no"));
                q.setNote(rs.getString("que_note"));
                q.setDetail(rs.getString("que_detail"));
                q.setChoice(rs.getString("que_choice"));
                q.setAnswer(rs.getString("que_answer"));
                list.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        dbUtil.close();
        return list;
    }

    /**
     * 通过阶段号获取试题集合
     *
     * @param stage 阶段 String
     * @return 试题集合List
     */
    public List<Questions> getQusetionsListByStageFromXml(String stage) {
        File que = new File("questions");
        String filename = null;
        String[] files = que.list();
        for (String s : files) {
            while (s.contains("stage" + stage)) ;
            {
                filename = s;
            }
        }
        // String filename = new StringBuffer(new SimpleDateFormat("yyyyMMdd").format(new Date())).append("_stage").append(stage).append(".xml").toString();
        return parseExamXml.parseExamXml(filename);
    }

    public static boolean addQuestions(List<Questions> questions) {
        String sql = "INSERT INTO question(que_no, que_note, que_detail, que_choice, que_answer, stage) VALUES(?,?,?,?,?,?)";
        int[] i = null;
        DBUtil dbUtil = new DBUtil();
        PreparedStatement ps = dbUtil.getPreparedStatement(sql);
        try {
            for (Questions q :
                    questions) {
                ps.setInt(1, q.getQueno());
                ps.setString(2, q.getNote());
                ps.setString(3, q.getDetail());
                ps.setString(4, q.getChoice());
                ps.setString(5, q.getAnswer());
                ps.setString(6, q.getStage());
                ps.addBatch();
            }
            i = ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        dbUtil.close();
        return i != null && i.length > 0;
    }


}

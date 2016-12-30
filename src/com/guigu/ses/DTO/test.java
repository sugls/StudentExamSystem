package com.guigu.ses.DTO;

import com.guigu.ses.Util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Lsc on 2016/12/29.
 */
public class test {

    public static void saveScore(String sno,String stage,double score){
        String sql = "INSERT INTO score(stu_no, stage, score) VALUES(?,?,?)";
        DBUtil dbUtil = new DBUtil();
        PreparedStatement ps = dbUtil.getPreparedStatement(sql);
        try {
            ps.setString(1,sno);
            ps.setString(2,stage);
            ps.setInt(3,(int)score);
            System.out.println(ps.execute());

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
    public static void main(String[] args) {
        saveScore("1024","1",0.0);
    }

}

package com.guigu.ses.DTO;

import com.guigu.ses.Util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Lsc on 2016/12/29.
 */
public class test1 {

    public static void insert(){
        DBUtil dbUtil = new DBUtil();
        String sql = "INSERT INTO student (stu_no, stu_name, stu_sex, stu_class, stu_passwd) VALUES(?,?,?,?,?)";

        PreparedStatement ps= dbUtil.getPreparedStatement(sql);

        try {
            ps.setString(1,"2001");
            ps.setString(2,"aaaa");
            ps.setString(3,"1");
            ps.setString(4,"J2010");
            ps.setString(5,"123456");
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
        //insert();
        System.out.println(-10%-3);
    }

}

package com.guigu.ses.DTO;

import com.guigu.ses.Util.DBUtil;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

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

    /**
     * 登录验证
     *
     * @param sno    学号
     * @param passwd 密码
     * @return
     */
    public static String[] checkLogin(String sno, String passwd) {
        String result[] = new String[2];
        int login_status = 0;
        DBUtil dbUtil = new DBUtil();
        String sql = "{CALL check_login(?,?,?,?)}";
        CallableStatement cs = dbUtil.getCallableStatement(sql);
        try {
            cs.setString(1,sno);
            cs.setString(2, passwd);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.registerOutParameter(4,Types.CHAR);
            cs.execute();
            login_status = cs.getInt(3);
            result[1] = cs.getString(4);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        dbUtil.close();
        switch (login_status) {
            case 1:
                result[0] = "登录成功";
                break;
            case 2:
                result[0] = "用户名错误";
                break;
            case 3:
                result[0] = "密码错误";
                break;
            default:
                result[0] = "数据库错误";
                break;
        }

        return result;
    }

    /**
     * 注册
     * @param sno 学号
     * @param name 姓名
     * @param sex 性别，值为 1或2
     * @param sclass 班级
     * @param passwd 密码
     * @return 布尔值
     */
    public static boolean regist(String sno,String name,String sex,String sclass,String passwd){
        boolean result = false;
        DBUtil dbUtil = new DBUtil();
        String sql = "{call add_student(?,?,?,?,?)}";
        PreparedStatement ps = dbUtil.getCallableStatement(sql);
        try {
            ps.setString(1,sno);
            ps.setString(2,name);
            ps.setString(3,sex);
            ps.setString(4,sclass);
            ps.setString(5,passwd);
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        dbUtil.close();
        return result;
    }

}

package com.guigu.ses.DTO;

import com.guigu.ses.Util.DBUtil;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lsc on 2016/12/27.
 */
public class Students {

    private int stuno;
    private String stuname;
    private String stuclass;
    private String sex;
    private String passwd;

    private static final String reg_sno = "\\d{4,12}";
    private static final String reg_pwd = "([a-z]|[A-z]|[0-9]){4,20}";
    private static final String reg_class = "\\A\\p{Alpha}{1}\\d{0,4}";

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
                result[0] = "学号不存在";
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
     * @return -1 表示学号重复 1 表示注册成功
     */
    public static int regist(String sno,String name,String sex,String sclass,String passwd){
        int result = 0;
        DBUtil dbUtil = new DBUtil();
        String sql = "{call add_student(?,?,?,?,?,?)}";
        CallableStatement cs = dbUtil.getCallableStatement(sql);
        try {
            cs.setString(1,sno);
            cs.setString(2,name);
            cs.setString(3,sex);
            cs.setString(4,sclass);
            cs.setString(5,passwd);
            cs.registerOutParameter(6,Types.INTEGER);
            cs.executeUpdate();
            result  = cs.getInt(6);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(cs!=null){
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        dbUtil.close();
        return result;
    }

    /**
     * 注册验证
     * @param infos 注册信息
     * @return
     */
    public static String checkRegist(String[] infos){
        String result = "验证成功";
        Pattern p_sno = Pattern.compile(reg_sno);
        Pattern p_pwd = Pattern.compile(reg_pwd);
        Pattern p_class = Pattern.compile(reg_class);

        Matcher m_sno = p_sno.matcher(infos[0]);
        Matcher m_class = p_class.matcher(infos[3]);
        Matcher m_pwd = p_pwd.matcher(infos[4]);

        if (!m_sno.matches()) {
            result = "学号必须为4-12位数字";
        }else if (!m_class.matches()){
            result = "班级必须为一个字母开头接0到4个数字";
        }
        else if (!m_pwd.matches()){
            result = "密码必须为4-20位数字或英文字符";
        }else if (!infos[4].equals(infos[5])){
            result = "两次密码输入不相同";
        }
        return result;

    }



}

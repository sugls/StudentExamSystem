package com.guigu.ses.UI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Lsc on 2016/12/27.
 */
public class Regist {

    private JFrame f_regist = new JFrame("考生注册");
    private JLabel l_title = new JLabel("考生注册", JLabel.CENTER);
    private JLabel l_stuno = new JLabel("学        号：");
    private JTextField tf_stuno = new JTextField(15);
    private JLabel l_stuname = new JLabel("姓        名：");
    private JTextField tf_stuname = new JTextField(15);
    private JLabel l_class = new JLabel("班        级：");
    private JTextField tf_class = new JTextField(15);
    private JLabel l_passwd = new JLabel("密        码：");
    private JPasswordField pf_passwd = new JPasswordField(15);
    private JLabel l_confirm = new JLabel("确认密码：");
    private JPasswordField pf_confirm = new JPasswordField(15);
    private JLabel l_sex = new JLabel("性        别：");
    private ButtonGroup group_sex = new ButtonGroup();
    private JRadioButton rb_male = new JRadioButton("男");
    private JRadioButton rb_female = new JRadioButton("女");
    private JButton b_regist = new JButton("提交");
    private JButton b_reset = new JButton("重置");
    private JPanel p_south = new JPanel();
    private JPanel p_center = new JPanel();
    private JPanel p_sno = new JPanel();
    private JPanel p_sname = new JPanel();
    private JPanel p_sex = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel p_class = new JPanel();
    private JPanel p_passwd = new JPanel();
    private JPanel p_confirm = new JPanel();
    private JLabel l_space = new JLabel("                           ");

    public Regist() {
        l_title.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
        f_regist.add(l_title, "North");
        p_south.add(b_regist);
        p_south.add(b_reset);
        f_regist.add(p_south, "South");

        p_sno.add(l_stuno);
        p_sno.add(tf_stuno);
        p_sname.add(l_stuname);
        p_sname.add(tf_stuname);
        p_center.add(p_sno);
        p_center.add(p_sname);
        f_regist.add(p_center);
        group_sex.add(rb_male);
        group_sex.add(rb_female);

        p_sex.add(l_sex);
        p_sex.add(rb_male);
        p_sex.add(rb_female);
        p_sex.add(l_space);
        p_center.add(p_sex);

        p_class.add(l_class);
        p_class.add(tf_class);
        p_center.add(p_class);

        p_passwd.add(l_passwd);
        p_passwd.add(pf_passwd);
        p_center.add(p_passwd);

        p_confirm.add(l_confirm);
        p_confirm.add(pf_confirm);
        p_center.add(p_confirm);



        f_regist.setVisible(true);
        f_regist.setSize(300, 350);
        f_regist.setLocation(300, 300);
        f_regist.setResizable(false);

    }

    /*public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new Regist();
    }*/
}

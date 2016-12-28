package com.guigu.ses.UI;

import com.guigu.ses.DTO.Students;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lsc on 2016/12/27.
 */
public class Regist implements ActionListener {

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
    private JRadioButton rb_male = new JRadioButton("男", true);
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

        //注册监听器
        b_regist.addActionListener(this);
        b_reset.addActionListener(this);

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
        f_regist.setLocation(350, 250);
        f_regist.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_regist) {
            String sno = tf_stuno.getText().trim();
            String sname = tf_stuname.getText().trim();
            String sex = rb_male.isSelected() ? "1" : "0";
            String sclass = tf_class.getText().trim();
            String passwd = String.valueOf(pf_passwd.getPassword()).trim();
            String passrepet = String.valueOf(pf_confirm.getPassword()).trim();
            String[] infos = {sno, sname, sex, sclass, passwd, passrepet};
            if (sno == null || "".equals(sno)) {
                JOptionPane.showMessageDialog(f_regist, "学号不能为空", "提示", JOptionPane.WARNING_MESSAGE);
                tf_stuno.requestFocus();
            } else if (sname == null || "".equals(sname)) {
                JOptionPane.showMessageDialog(f_regist, "姓名不能为空", "提示", JOptionPane.WARNING_MESSAGE);
                tf_stuname.requestFocus();
            } else if (sclass == null || "".equals(sclass)) {
                JOptionPane.showMessageDialog(f_regist, "班级不能为空", "提示", JOptionPane.WARNING_MESSAGE);
                tf_class.requestFocus();
            } else if (passwd == null || "".equals(passwd)) {
                JOptionPane.showMessageDialog(f_regist, "密码不能为空", "提示", JOptionPane.WARNING_MESSAGE);
                pf_passwd.requestFocus();
            } else if (passrepet == null || "".equals(passrepet)) {
                JOptionPane.showMessageDialog(f_regist, "确认密码不能为空", "提示", JOptionPane.WARNING_MESSAGE);
                pf_confirm.requestFocus();
            } else {
                String msg = Students.checkRegist(infos);
                if (!"验证成功".equals(msg)) {
                    JOptionPane.showMessageDialog(f_regist, msg, "提示", JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean reg = Students.regist(sno, sname, sex, sclass, passwd);
                    if (reg == true) {

                        //JOptionPane.showConfirmDialog(f_regist,"注册成功，是否去登录？","提示",JOptionPane.YES_NO_OPTION);
                        JButton[] btns = new JButton[]{new JButton("去登陆"), new JButton("取消")};
                        //JOptionPane  dialog= new JOptionPane("注册成功",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION,new ImageIcon(),btns);
                        JDialog dialog = new JDialog(f_regist, "提示", true);
                        JLabel meg = new JLabel("注册成功", JLabel.CENTER);
                        dialog.add(meg);
                        JPanel p = new JPanel();
                        p.add(btns[0]);
                        p.add(btns[1]);
                        dialog.add(p, "South");

                        dialog.setSize(250, 200);
                        dialog.setLocation(f_regist.getX() + 50, f_regist.getY() + 60);
                        dialog.setVisible(true);

                        btns[0].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dialog.dispose();
                                f_regist.dispose();
                            }
                        });
                        btns[1].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dialog.dispose();
                            }
                        });
                    }
                }
            }


        }
        if (e.getSource() == b_reset) {
            tf_stuno.setText("");
            tf_stuname.setText("");
            tf_class.setText("");
            pf_passwd.setText("");
            pf_confirm.setText("");
        }
    }

    /*public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new Regist();
    }*/
}

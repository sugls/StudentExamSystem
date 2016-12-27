package com.guigu.ses.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 2016/12/27.
 */
public class Login implements ActionListener{

    private JFrame frame = new JFrame("考生登录");
    private JLabel l_title = new JLabel("杭州归谷考试系统",JLabel.CENTER);
    private JLabel l_stuno = new JLabel("学 号：");
    private JLabel l_passwd = new JLabel("密 码：");
    private JTextField tf_stuno = new JTextField(15);
    private JPasswordField pf_passwd = new JPasswordField(15);
    private JPanel p_stuno = new JPanel();
    private JPanel p_passwd = new JPanel();
    private JButton b_login = new JButton("登录");
    private JButton b_regist = new JButton("注册");
    private JPanel p_south = new JPanel();
    private JPanel p_center = new JPanel();

    public Login() {
        l_title.setFont(new Font("Microsoft YaHei",Font.BOLD,20));
        frame.add(l_title,"North");
        p_south.add(b_login);
        p_south.add(b_regist);
        frame.add(p_south,"South");

        p_stuno.add(l_stuno);
        p_stuno.add(tf_stuno);
        p_center.add(p_stuno);

        p_passwd.add(l_passwd);
        p_passwd.add(pf_passwd);
        p_center.add(p_passwd);
        frame.add(p_center);

        b_regist.addActionListener(this);
        b_login.addActionListener(this);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocation(300,300);
        frame.setSize(400,200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==b_regist){
            new Regist();
        }
    }
}

package com.guigu.ses.UI;

import com.guigu.ses.DTO.Scores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lsc on 2016/12/27.
 */
public class Main implements ActionListener{

    private JFrame frame = new JFrame("考试系统");
    private JLabel l_title = new JLabel("杭州归谷考试系统",JLabel.CENTER);
    private JButton b_first = new JButton("第一阶段");
    private JButton b_second = new JButton("第二阶段");
    private JButton b_third = new JButton("第三阶段");
    private JPanel p_south = new JPanel();
    private JLabel l_main = new JLabel();
    private String name;
    private String score_s1;

    public Main() {
        l_title.setFont(new Font("Microsoft YaHei",Font.BOLD,20));
        frame.add(l_title,"North");
        p_south.add(b_first);
        p_south.add(b_second);
        p_south.add(b_third);
        b_first.addActionListener(this);
        b_second.addActionListener(this);
        b_third.addActionListener(this);
        frame.add(p_south,"South");
        frame.add(l_main);
        l_main.setFont(new Font("Microsoft YaHei",Font.PLAIN,20));

        l_main.setHorizontalAlignment(JLabel.CENTER);
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setLocation(300,300);
    }

    public Main(String name) {
        this();
        this.name = name;
        l_main.setText(name+"同学你好,欢迎使用考试系统");
        score_s1 = Scores.getStuScoreByStage(name,"1");
        if (score_s1==null){
            b_second.setEnabled(false);
            b_third.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==b_first){
            if (score_s1!=null){
                l_main.setText(name+"同学,你的第一阶段考试成绩为："+score_s1);
            }else{
                new Exam(name,"1");
            }
        }

    }

    public static void main(String[] args) {
        new Main();
    }
}

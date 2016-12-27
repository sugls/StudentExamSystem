package com.guigu.ses.UI;

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

    public Main() {
        l_title.setFont(new Font("Microsoft YaHei",Font.BOLD,20));
        frame.add(l_title,"North");
        p_south.add(b_first);
        p_south.add(b_second);
        p_south.add(b_third);
        frame.add(p_south,"South");
        frame.add(l_main);
        l_main.setFont(new Font("Microsoft YaHei",Font.PLAIN,30));
        l_main.setText("你好");
        l_main.setHorizontalAlignment(JLabel.CENTER);
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setLocation(300,300);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Main();
    }
}

package com.guigu.ses.UI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Lsc on 2016/12/27.
 */
public class Exam {

    private JFrame frame = new JFrame("考试系统");
    private JPanel p_north = new JPanel();
    private JPanel p_south = new JPanel(new GridLayout(1,4));
    private JPanel p_center = new JPanel();

    private JLabel l_north_1 = new JLabel();
    private JLabel l_north_2 = new JLabel();
    private JTextArea ta_question = new JTextArea();
    private JScrollPane sp_question = new JScrollPane(ta_question);
    private JPanel p_choice = new JPanel();
    private JLabel l_choice = new JLabel("选项");
    private CheckboxGroup group_choice = new CheckboxGroup();
    private JCheckBox[] cb_choice;

    private JButton b_start = new JButton("开始");
    private JPanel p_south_1 = new JPanel();
    private JButton b_pre = new JButton("上一题");
    private JButton b_next = new JButton("下一题");
    private JPanel p_south_2 = new JPanel();
    private JLabel l_num = new JLabel("序号：");
    private JTextField tf_num = new JTextField(2);
    private JButton b_go = new JButton("确定");
    private JPanel p_south_3 = new JPanel();
    private JButton b_submit = new JButton("交卷");
    private JPanel p_south_4 = new JPanel();

    public Exam() {

        p_north.add(l_north_1,JPanel.LEFT_ALIGNMENT);
        p_north.add(l_north_2,JPanel.RIGHT_ALIGNMENT);
        frame.add(p_north,"North");
        p_south_1.add(b_start);
        p_south.add(p_south_1);
        p_south_2.add(b_pre);
        p_south_2.add(b_next);
        p_south.add(p_south_2);
        p_south_3.add(l_num);
        p_south_3.add(tf_num);
        p_south_3.add(b_go);
        p_south.add(p_south_3);
        p_south_4.add(b_submit);
        p_south.add(p_south_4);
        frame.add(p_south,"South");
        frame.setVisible(true);
        frame.setSize(600,500);
        frame.setLocation(400,400);

    }

    public static void main(String[] args) {
        new Exam();
    }

}

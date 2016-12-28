package com.guigu.ses.UI;

import com.guigu.ses.DTO.Questions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Lsc on 2016/12/27.
 */
public class Exam implements ActionListener{

    private JFrame frame = new JFrame("考试系统");
    private JPanel p_north = new JPanel();
    private JPanel p_south = new JPanel(new GridLayout(1,4));
    private CardLayout card = new CardLayout();
    private JPanel p_center = new JPanel(card);

    private JLabel l_north_1 = new JLabel();
    private JLabel l_north_2 = new JLabel();
    private JTextArea ta_question = new JTextArea();
    private JScrollPane sp_question = new JScrollPane(ta_question);
    private JPanel p_choice = new JPanel();
    private JLabel l_choice = new JLabel("选项");
    private CheckboxGroup group_choice = new CheckboxGroup();
    private JCheckBox[] cb_choice;
    private JPanel[] p_card;

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
    private String name;
    private String stage;
    private List<Questions> list;

    public Exam() {

        p_north.add(l_north_1,JPanel.LEFT_ALIGNMENT);
        p_north.add(l_north_2,JPanel.RIGHT_ALIGNMENT);
        frame.add(p_north,"North");
        p_south_1.add(b_start);
        b_start.addActionListener(this);
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
        frame.add(p_center);
        frame.setVisible(true);
        frame.setSize(650,500);
        frame.setLocation(400,400);
        frame.setResizable(false);

    }

    public Exam(String name,String stage){
        this();
        l_north_1.setText(name+"同学，第"+stage+"阶段考试");
        list = Questions.getQuestionsByStage(stage);
        p_card = new JPanel[list.size()];

    }


    public static void main(String[] args) {
        new Exam();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==b_start){
            p_choice.add(l_choice);
            String[] choice;
            System.out.println(list.size());
            for (int i=0;i<list.size();i++){
                p_card[i] = new JPanel();
                ta_question.setText(list.get(i).getDetail());
                System.out.println(list.get(i).getDetail());
                choice = list.get(i).getChoice().trim().split("\n");
                for (String s:choice){
                    System.out.println("s"+s);
                }
                cb_choice = new JCheckBox[choice.length];
                for (int j=0;j<cb_choice.length;j++){
                    cb_choice[j] = new JCheckBox(choice[j]);
                    p_choice.add(cb_choice[j]);
                }
                p_card[i].add(sp_question);
                p_card[i].add(p_choice);
                p_center.add(p_card[i]);
            }

        }
    }
}

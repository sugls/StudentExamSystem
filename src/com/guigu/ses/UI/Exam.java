package com.guigu.ses.UI;

import com.guigu.ses.DTO.Questions;
import com.guigu.ses.DTO.Scores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * 考试界面
 * Created by Lsc on 2016/12/27.
 */
public class Exam implements ActionListener, ItemListener {

    private JFrame frame = new JFrame("考试系统");
    private JPanel p_north = new JPanel(new GridLayout(1, 2));
    private JPanel p_south = new JPanel(new GridLayout(1, 4));
    private CardLayout card = new CardLayout();
    private JPanel p_center = new JPanel(card);
    private JPanel p_north_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel p_north_2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JLabel l_north_1 = new JLabel();
    private JLabel l_north_2 = new JLabel("考试时间：02:00:00");
    private JTextArea ta_question = null;
    private JScrollPane sp_question = new JScrollPane(ta_question);
    private JPanel p_choice = new JPanel();
    private JLabel l_choice = new JLabel("选项");
    private CheckboxGroup group_choice;
    private Checkbox[] cb_choice;
    private List<Checkbox[]> list_cb = new Vector<>();
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
    private String sno;
    private String stage;
    private String[] choice;
    private List<Questions> list;
    private Map<String, String> answer = new HashMap<>();
    private boolean flag = true;
    private boolean isStart = false;
    private Timer timer = new Timer();
    private int cardnum = 1;
    private double score;


    public Exam() {
        p_north_1.add(l_north_1);
        l_north_2.setFont(new Font("YaHei", Font.PLAIN, 14));
        p_north_2.add(l_north_2);
        p_north.add(p_north_1);
        p_north.add(p_north_2);
        frame.add(p_north, "North");
        p_south_1.add(b_start);
        b_start.addActionListener(this);
        p_south.add(p_south_1);
        p_south_2.add(b_pre);
        p_south_2.add(b_next);
        b_pre.addActionListener(this);
        b_next.addActionListener(this);
        b_go.addActionListener(this);
        b_submit.addActionListener(this);


        b_submit.setEnabled(false);   //初始化 交卷按钮
        b_pre.setEnabled(false);
        b_next.setEnabled(false);
        b_go.setEnabled(false);
        p_south.add(p_south_2);
        p_south_3.add(l_num);
        p_south_3.add(tf_num);
        p_south_3.add(b_go);
        p_south.add(p_south_3);
        p_south_4.add(b_submit);
        p_south.add(p_south_4);


        frame.add(p_south, "South");
        frame.setVisible(true);
        frame.setSize(700, 500);
        frame.setLocation(400, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showConfirmSubmitDialog();
            }
        });

    }

    /**
     * 构造带有学生姓名，考试阶段，和学号的考试界面
     * @param name 姓名
     * @param stage 阶段好
     * @param sno 学号
     */
    public Exam(String name, String stage, String sno) {
        this();
        this.name = name;
        this.sno = sno;
        this.stage = stage;
        l_north_1.setText(name + "同学，第" + stage + "阶段考试");
        list = Questions.getQusetionsListByStageFromXml(stage);
        p_card = new JPanel[list.size()];

    }

    /**
     * 监听方法
     * @param e 事件
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b_start) {
            isStart = true;
            b_start.setEnabled(false);
            b_submit.setEnabled(true);
            b_go.setEnabled(true);

            long start = System.currentTimeMillis();
            long end =start+ 2*60*60*1000;
           //long end = start + 1*60*1000;
            timer.schedule(new TimerTask() {
                StringBuffer buffer = new StringBuffer();

                @Override
                public void run() {
                    buffer.delete(0, buffer.length());
                    long show = end - System.currentTimeMillis();
                    long h = show / 1000 / 60 / 60;
                    long m = show / 1000 / 60 % 60;
                    long s = show / 1000 % 60;
                    buffer = buffer.append("倒计时：").append(h < 10 ? "0" : "").append(h).append(":").append(m < 10 ? "0" : "").append(m).append(":").append(s < 10 ? "0" : "").append(s);
                    l_north_2.setText(buffer.toString());
                }
            }, 0, 1000);
            l_north_2.setForeground(new Color(255, 0, 0));

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(frame, "距离考试结束还有5分钟", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }, new Date(end - 5 * 60 * 1000));

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timer.cancel();
                    submit();
                    JOptionPane.showMessageDialog(frame,"考试时间结束","提示",JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(frame,showScore(),"提示",JOptionPane.INFORMATION_MESSAGE);
                    new Main(sno, name);
                    frame.dispose();
                }
            }, new Date(end));


            JPanel[] panel = new JPanel[list.size()];
            for (int i = 0; i < panel.length; i++) {

                frame.remove(sp_question);
                JPanel p_que = new JPanel(new BorderLayout());
                JLabel l_quetitle = new JLabel();
                panel[i] = new JPanel(new GridLayout(2, 1));
                ta_question = new JTextArea(10, 45);
                ta_question.setText(list.get(i).getDetail().trim());
                ta_question.setEditable(false);
                sp_question = new JScrollPane(ta_question);
                String note = list.get(i).getNote();

                choice = list.get(i).getChoice().trim().split("\n");
                p_choice = new JPanel(new GridLayout(choice.length + 1, 1));
                cb_choice = new Checkbox[choice.length];
                list_cb.add(cb_choice);
                group_choice = new CheckboxGroup();
                l_choice = new JLabel("选项");
                p_choice.add(l_choice);
                for (int j = 0; j < choice.length; j++) {
                    if (!"multiple selection".equals(note)) {
                        cb_choice[j] = new Checkbox(String.valueOf((char) (j + 'A')) + ") " + choice[j], false, group_choice);
                    } else {
                        cb_choice[j] = new Checkbox(String.valueOf((char) (j + 'A')) + ") " + choice[j]);

                    }
                    cb_choice[j].addItemListener(this);
                    p_choice.add((cb_choice[j]), JPanel.LEFT_ALIGNMENT);
                }
                String type = "multiple selection".equals(note) ? "多选题" : "单选题";
                l_quetitle.setText("第" + (i + 1) + "题     " + type);
                p_que.add(l_quetitle, "North");
                p_que.add(sp_question);
                panel[i].add(p_que);
                panel[i].add(p_choice);
                p_center.add(panel[i], String.valueOf(i + 1));

            }

            frame.add(p_center);
            frame.validate();

        }

        if (e.getSource() == b_pre) {
            cardnum--;
            card.previous(p_center);
        }
        if (e.getSource() == b_next) {
            cardnum++;
            card.next(p_center);
        }
        if (e.getSource() == b_go) {
            int i = 0;
            try{
                i = Integer.parseInt(tf_num.getText().trim());
                if (i>list.size()||i<=0){
                    JOptionPane.showMessageDialog(frame,"题号超出范围","提示",JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e1){
                JOptionPane.showMessageDialog(frame,"请输入正确的题号","提示",JOptionPane.INFORMATION_MESSAGE);
            }
            card.show(p_center, tf_num.getText().trim());
            if (i!=0){
                cardnum = i;
            }
        }

        if (e.getSource() == b_submit) {
            showConfirmSubmitDialog();
        }

        if (cardnum<=1){
            b_pre.setEnabled(false);
        }
        if (cardnum > 1 ) {
            b_pre.setEnabled(true);
        }
        if (cardnum < list.size()) {
            b_next.setEnabled(true);
        }
        if (cardnum>=list.size()){
            b_next.setEnabled(false);
        }
    }

    /**
     * 提交分数到数据库
     */
    private void submit() {
        b_submit.setEnabled(false);
        score = Scores.countScore(answer, list);
        Scores.saveScore(sno, stage, score);
    }

    private void showConfirmSubmitDialog(){
        int i = JOptionPane.showConfirmDialog(frame, "考试还没结束，确认关闭吗？", "提示", JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
            if (isStart) {
                timer.cancel();
                submit();
                JOptionPane.showMessageDialog(frame,showScore(),"提示",JOptionPane.INFORMATION_MESSAGE);

                new Main(sno, name);
                frame.dispose();

            } else {
                new Main(sno, name);
                frame.dispose();
            }
        } else {
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
    /**
     * 显示分数
     * @return 提示分数信息
     */
    private String showScore() {
        return new StringBuffer().append(name).append("同学你好，\n你的第").append(stage).append("阶段考试成绩为：").append(score).toString();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        StringBuffer buffer = new StringBuffer();
        for (int j = 0; j < list_cb.size(); j++) {
            cb_choice = (Checkbox[]) list_cb.get(j);
            buffer.delete(0, buffer.length());
            for (Checkbox c : cb_choice) {
                buffer.append(c.getState() ? "1" : "0");
            }
            answer.put(String.valueOf(j + 1), buffer.toString());
        }

    }
}

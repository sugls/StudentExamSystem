package com.guigu.ses.Util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 管理员操作，生成试题，试题入库
 * Created by Lsc on 2017/1/8.
 */
public class Admin {
    /**
     * 生成考试试题xml文件
     */
    public static void buildExamFile(){
        String path = "questions/";
        File que = new File(path);
        String[] q = que.list();
        for (String s:q){
            new File(path+s).delete();
        }
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
        buildExamXml.createXmlFile(path+ date +"_stage1.xml","1");
        buildExamXml.createXmlFile(path+ date +"_stage2.xml","2");
        buildExamXml.createXmlFile(path+ date +"_stage3.xml","3");
    }

    public static void main(String[] args) {
        // 将试题文件存入数据库
        //System.out.println(Questions.addQuestions(parseExamXml.parseExamXml("data.xml")));
        // 生成试题文件
        buildExamFile();
    }
}

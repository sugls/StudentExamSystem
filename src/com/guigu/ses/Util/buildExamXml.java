package com.guigu.ses.Util;

import com.guigu.ses.DTO.Questions;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Lsc on 2016/12/27.
 */
public class buildExamXml {

    public static void createXmlFile(String filename, String stage) {
        //创建文件工厂对象
        DocumentFactory factory = new DocumentFactory();
        //创建文档对象
        Document document = factory.createDocument();
        //创建根元素
        Element root = factory.createElement("questions");
        //将根元素放入文档对象
        document.add(root);
        //通过阶段编号获取试题列表
        List<Questions> list = Questions.getQuestionsByStage(stage);
        for (Questions q :
                list) {
            Element question = factory.createElement("question");
            root.add(question);
            Attribute id = factory.createAttribute(question,"id",String.valueOf(q.getQueno()));
            question.add(id);
            Element note = factory.createElement("note");
            question.add(note);
            note.add(factory.createText(q.getNote()));

            Element detail = factory.createElement("detail");
            question.add(detail);
            detail.add(factory.createText(q.getDetail()));

            Element choice = factory.createElement("choice");
            question.add(choice);
            choice.add(factory.createText(q.getChoice()));

            Element answer = factory.createElement("answer");
            question.add(answer);
            answer.add(factory.createText(q.getAnswer()));
        }

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setTrimText(false);
        format.setIndent(true);
        format.setNewlines(true);
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new FileWriter(filename), format);
            writer.write(document);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

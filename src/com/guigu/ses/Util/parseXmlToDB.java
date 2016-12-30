package com.guigu.ses.Util;

import com.guigu.ses.DTO.Questions;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;
import java.util.Vector;

/**
 * Created by Lsc on 2016/12/29.
 */
public class parseXmlToDB {


    public List<Questions> parseFile(String filename) {
        SAXReader reader = new SAXReader();
        List<Questions> question = new Vector<>();
        try {
            //获取文档对象
            Document document = reader.read(filename);
            //获取根元素
            Element root = document.getRootElement();
            //获取子元素集合
            List<Element> elements = root.elements();
            for (Element e :
                    elements
                    ) {
                Questions q = new Questions();
                Attribute id = e.attribute("id");
                q.setQueno(Integer.parseInt(id.getText()));
                Attribute stage = e.attribute("stage");
                q.setStage(stage.getText());
                Element note = e.element("note");
                q.setNote(note.getText());
                q.setDetail(e.element("detail").getText());
                q.setChoice(e.element("choice").getText());
                q.setAnswer(e.element("answer").getText());
                question.add(q);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return question;
    }

    public static void main(String[] args) {
        parseXmlToDB p = new parseXmlToDB();
        System.out.println(Questions.addQuestions(p.parseFile("data.xml")));
    }


}

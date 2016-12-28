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
 * Created by Lsc on 2016/12/28.
 */
public class parseExamXml {


    public static List<Questions> parseExamXml(String filename){
        SAXReader saxReader = new SAXReader();
        List<Questions> questionsList = new Vector<>();
        try {
            Document document = saxReader.read(filename);
            Element root = document.getRootElement();
            List<Element> list= root.elements("question");
            for (Element e:list
                 ) {
                Questions q = new Questions();
                Attribute id = e.attribute("id");
                q.setQueno(Integer.parseInt(id.getValue()));
                Element note = e.element("note");
                q.setNote(note.getText());
                q.setDetail(e.element("detail").getText());
                q.setChoice(e.element("choice").getText());
                q.setAnswer(e.element("answer").getText());
                questionsList.add(q);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return questionsList;
    }


}

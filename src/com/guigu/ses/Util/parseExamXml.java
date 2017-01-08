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
 * 解析试题xml文件
 * Created by Lsc on 2016/12/28.
 */
public class parseExamXml {

    /**
     * 解析试题xml文件
     * @param filename 文件路径
     * @return 试题列表
     */
    public static List<Questions> parseExamXml(String filename){
        SAXReader saxReader = new SAXReader();
        List<Questions> questionsList = new Vector<>();
        try {
            // 获取文档对象
            Document document = saxReader.read(filename);
            // 获取根元素
            Element root = document.getRootElement();
            // 获取子元素集合
            List<Element> list= root.elements("question");
            for (Element e:list
                 ) {
                Questions q = new Questions();
                Attribute id = e.attribute("id");
                q.setQueno(Integer.parseInt(id.getValue()));
                q.setStage(e.attributeValue("stage"));
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

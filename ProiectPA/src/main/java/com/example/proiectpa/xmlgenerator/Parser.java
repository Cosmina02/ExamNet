package com.example.proiectpa.xmlgenerator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Parser {

public static StringBuilder ReadXML(){
    String fileName="test1.xml";
    StringBuilder Test=new StringBuilder();
    try{
        SAXBuilder builder=new SAXBuilder();
        File xmlFile=new File(fileName);
        Document doc=(Document) builder.build(xmlFile);
        Element root=doc.getRootElement();
        List<Element> listOfQuestions=root.getChildren("Question");
        Test.append("<form>");
        for(Element question:listOfQuestions){
            Test.append("<p>Question no:").append(question.getAttributeValue("id")).append("<p>\n");
            String id=question.getAttributeValue("id");
            Test.append("<p>Question text: ").append(question.getChildText("QuestionText")).append("<p>\n");
            for(int i=1;i<=10;i++){
                String ans="Answer"+i;
                Test.append("<input type=\"checkbox\" id=\"Q" + id+ "\" name=\"question"+id+"\" value=\"A"+i+"\">");
                Test.append("<label for=\"question"+id+"\">").append(question.getChildText(ans)).append("</label><br>\n");
            }
        }
        Test.append("<input type=\"submit\" value=\"Submit\" onclick=\"submit()\">").append("</form>");

    } catch (IOException | JDOMException e) {
        e.printStackTrace();
    }
    return Test;
}


}


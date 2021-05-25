package com.example.proiectpa.xmlgenerator;

import com.example.proiectpa.DBInteraction.DBConnection;
import com.example.proiectpa.DBInteraction.Queries;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateXMLFile {
    public static void generateXML(){
        try{
            DBConnection dbconn=DBConnection.getInstance("./DataBase/database.db");
            Queries query=new Queries();
            Document doc=new Document();
            doc.setRootElement(new Element("Test"));
            doc.getRootElement().addContent(createQuestionXMLElement("1",query.GetQuestion(dbconn,"Q1"), query.GetAnswers(dbconn,"Q1")));
            doc.getRootElement().addContent(createQuestionXMLElement("2", query.GetQuestion(dbconn,"Q5"), query.GetAnswers(dbconn,"Q5")));
            doc.getRootElement().addContent(createQuestionXMLElement("3", query.GetQuestion(dbconn,"Q9"),query.GetAnswers(dbconn,"Q9")));
            doc.getRootElement().addContent(createQuestionXMLElement("4", query.GetQuestion(dbconn,"Q23"),query.GetAnswers(dbconn,"Q23") ));
            XMLOutputter xmlOutput= new XMLOutputter();

            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc,new FileWriter("test1.txt"));
            System.out.println("File Saved!");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
    private static Element createQuestionXMLElement(String id, ResultSet questionResult, ResultSet answerResult) throws SQLException {
        Element question = new Element("Question");
        question.setAttribute(new Attribute("id", id));
        question.addContent(new Element("QuestionText").setText(questionResult.getString("TEXT_INTREBARE")));
        int Aid=0;
        while(answerResult.next()){
            Aid++;
            question.addContent(new Element("Answer"+Aid).setText(answerResult.getString("TEXT_RASPUNS")));
        }
        return question;
    }
}

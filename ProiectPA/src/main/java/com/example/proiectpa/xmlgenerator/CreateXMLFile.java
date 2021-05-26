package com.example.proiectpa.xmlgenerator;

import com.example.proiectpa.DBInteraction.DBConnection;
import com.example.proiectpa.DBInteraction.Queries;
import com.example.proiectpa.controllers.Answears;
import com.example.proiectpa.controllers.Questions;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateXMLFile {
    public static void generateTestXML(ArrayList<Questions> QuestionsInTest){
        try{

            Document doc=new Document();
            doc.setRootElement(new Element("Test"));
            for(Questions question: QuestionsInTest){
                doc.getRootElement().addContent(createQuestionXMLElement(question.getId(), question.getQuestion_text(),question.getAnswears()));
            }
            XMLOutputter xmlOutput= new XMLOutputter();

            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc,new FileWriter("test.xml"));
            System.out.println("File Saved!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static Element createQuestionXMLElement(String id, String question_text, ArrayList<Answears> answears){
        Element question = new Element("Question");
        question.setAttribute(new Attribute("id", id));
        question.addContent(new Element("QuestionText").setText(question_text));
        for( Answears answear:answears){
            question.addContent(new Element("Answer"+answear.id).setText(answear.answear_text));
        }
        return question;
    }


}

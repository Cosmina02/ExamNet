package com.example.proiectpa.xmlgenerator;

import TestModel.Answears;
import TestModel.Problems;
import TestModel.Questions;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateXMLFile {
    public static void generateTestXML(ArrayList<Questions> QuestionsInTest) {
        try {

            Document doc = new Document();
            doc.setRootElement(new Element("Test"));
            for (Questions question : QuestionsInTest) {
                if(question.getId()!=null){
                    if (question.getId().equals("Problem")) {
                        //System.out.println("am gasit o problema");
                        ArrayList<Problems> problems = question.getProblems();
                        for (Problems problems1 : problems) {
                            doc.getRootElement().addContent(createProblemXMLElement(problems1));
                        }
                    } else {
                        doc.getRootElement().addContent(createQuestionXMLElement(question.getId(), question.getQuestion_text(), question.getAnswears()));
                    }
                }
            }
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("src/main/resources/templates/generate/xml/test.xml"));
            System.out.println("File Saved!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Element createProblemXMLElement(Problems problems) {
        Element problem = new Element("Problem");
        problem.setAttribute(new Attribute("id", problems.id));
        problem.addContent(new Element("ProblemText").setText(problems.text_problema));
        return problem;

    }

    private static Element createQuestionXMLElement(String id, String question_text, ArrayList<Answears> answears) {
        Element question = new Element("Question");
        question.setAttribute(new Attribute("id", id));
        question.addContent(new Element("QuestionText").setText(question_text));
        for (Answears answear : answears) {
            question.addContent(new Element("Answer" + answear.id).setText(answear.answear_text));
        }
        return question;
    }

    public static void generateAnswearsXML(ArrayList<Questions> questions, List<String> answear) {
        try {
            Document doc = new Document();
            doc.setRootElement(new Element("TestAnswears"));
            ArrayList<Answears> answears;
            for (Questions question : questions) {
                if(question.getId()!=null){
                    if(question.getId().equals("Problem")){
                        System.out.println("problem");
                        ArrayList<Problems> problems=question.getProblems();
                        int p=1;
                        for(Problems problems1:problems){
                            doc.getRootElement().addContent( createProblemAnswearXMLElement(problems1.getId(), problems1.getText_problema(),answear,p ));
                            p++;
                        }
                    }
                    else{
                        doc.getRootElement().addContent(createAnswearXMLElement(question.getId(), question.getQuestion_text(), question.getAnswears(), answear));
                    }
                }


            }
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());

            xmlOutput.output(doc, new FileWriter("src/main/resources/templates/generate/xml/answears.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File Saved!");
    }

    private static Element createAnswearXMLElement(String id, String question_text, ArrayList<Answears> allanswears, List<String> givenAnswears) {
        Element question = new Element("Question");
        question.setAttribute(new Attribute("id", id));
        question.addContent(new Element("QuestionText").setText(question_text));
        for (Answears answear : allanswears) {
            for (String givenans : givenAnswears) {
                if (answear.id.equals(givenans)) {
                    question.addContent(new Element("Answer" + answear.id).setText(answear.answear_text));
                }
            }
        }
        return question;
    }

    private static Element createProblemAnswearXMLElement(String id,String problem_text,List<String>givenans,int p){
        Element problem_ans=new Element("Problem");
        problem_ans.setAttribute(new Attribute("id",id));
        problem_ans.addContent(new Element("ProblemText").setText(problem_text));
        if(p==1){
            problem_ans.addContent(new Element("Answear").setText(givenans.get(givenans.size()-2)));
        }
        else{
            problem_ans.addContent(new Element("Answear").setText(givenans.get(givenans.size()-1)));
        }

        return problem_ans;
    }


}

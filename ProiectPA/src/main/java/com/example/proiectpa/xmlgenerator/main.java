package com.example.proiectpa.xmlgenerator;

import com.example.proiectpa.DBInteraction.DBConnection;
import com.example.proiectpa.DBInteraction.Queries;
import TestModel.Answears;
import TestModel.Questions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class main {
    public static void main(String[] args) throws SQLException {
        Random rand=new Random();
        ArrayList<Questions> QuestionsInTest=new ArrayList<>();
        List<String> answears=new ArrayList<>();
        DBConnection dbconn=DBConnection.getInstance("./DataBase/database.db");
        Queries query=new Queries();
        answears.add("1Q1");
        answears.add("3Q1");
        answears.add("2Q2");
        answears.add("3Q2");
        answears.add("1Q3");
        for(int i=1;i<=3;i++){
            int r=rand.nextInt(3);
            if(r==0){
                r++;
            }
            Questions questions=new Questions();
            ResultSet question=query.GetQuestion(dbconn,"Q"+r);
            if(question.next()){
                questions.setQuestion_text(question.getString("TEXT_INTREBARE"));
                String dom=question.getString("DOMENIU");
                questions.setId(question.getString("ID"));
            }
            ArrayList<Answears> ans=new ArrayList<>();

            ResultSet answers= query.GetAnswers(dbconn,"Q"+r);
            int id=1;
            while(answers.next()){
                Answears currentAnswear=new Answears();
                currentAnswear.setAnswear_text(answers.getString("TEXT_RASPUNS"));
                currentAnswear.setId(String.valueOf(id)+"Q"+r);
                id++;
                ans.add(currentAnswear);
            }
            questions.setAnswears(ans);
            QuestionsInTest.add(questions);

        }
        CreateXMLFile.generateAnswearsXML(QuestionsInTest,answears);
    }
}

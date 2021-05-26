package com.example.proiectpa.controllers;

import com.example.proiectpa.DBInteraction.DBConnection;
import com.example.proiectpa.DBInteraction.Queries;
import org.jdom.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

@Controller
@RequestMapping("generate")
public class TestController {
 static int nrOfQuestions;
    DBConnection dbconn=DBConnection.getInstance("./DataBase/database.db");
    Queries query=new Queries();
    @RequestMapping(value="",method= RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("title","Nr of test Questions");
        return "generate/index";
    }

@RequestMapping(value="",method=RequestMethod.POST)
    public ModelAndView processNrOfQuestionsForm( ModelAndView model) throws SQLException {
       // nrOfQuestions=Integer.parseInt(NrOfQuestions);
        Random rand=new Random();
         model.setViewName("/generate/test");
        for(int i=1;i<=10;i++){
            int r=rand.nextInt(23);
            if(r==0){
                r++;
            }
            Questions questions=new Questions();
            ResultSet question=query.GetQuestion(dbconn,"Q"+r);
                if(question.next()){
                    questions.setQuestion_text(question.getString("TEXT_INTREBARE"));
                    String dom=question.getString("DOMENIU");
                    String id=question.getString("ID");

                }
                System.out.println(questions.getQuestion_text());
                ArrayList<String>ans=new ArrayList<>();
                ResultSet answers= query.GetAnswers(dbconn,"Q"+r);
                while(answers.next()){
                    ans.add(answers.getString("TEXT_RASPUNS"));
                }
                questions.setAnswears(ans);

            model.addObject("questions"+i,questions);
       }
        for(int i=1;i<=2;i++){
            int r=rand.nextInt(6);
            if(r==0){
                r++;
            }
            String text = null;
            ResultSet problema=query.GetProblem(dbconn,"P"+r);
            if(problema.next()){
                text=problema.getString("TEXT_PROBLEMA");
                String dom=problema.getString("DOMENIU");
                String id=problema.getString("ID");

            }
            model.addObject("problema"+i,text);
        }

       // System.out.println(nrOfQuestions);
        return model;
    }
}

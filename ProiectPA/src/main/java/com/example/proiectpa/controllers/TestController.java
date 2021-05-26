package com.example.proiectpa.controllers;

import TestModel.Answears;
import TestModel.Problems;
import TestModel.Questions;
import com.example.proiectpa.DBInteraction.DBConnection;
import com.example.proiectpa.DBInteraction.Queries;
import com.example.proiectpa.xmlgenerator.CreateXMLFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/generate")
public class TestController {
    ArrayList<Questions> QuestionsInTest = new ArrayList<>();
    DBConnection dbconn = DBConnection.getInstance("./DataBase/database.db");
    Queries query = new Queries();
    List<Integer> questionNumber = new ArrayList<>();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Nr of test Questions");
        return "generate/index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processNrOfQuestionsForm(ModelAndView model) throws SQLException {

        return "redirect:/generate/test";
    }


    @RequestMapping(value="/test",method=RequestMethod.GET)
    public ModelAndView  processTestAnswears(ModelAndView model) throws SQLException {
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

            model.addObject("questions"+i,questions);
        }
        ArrayList<Problems> problems=new ArrayList<>();
        for(int i=1;i<=2;i++){
            int r=rand.nextInt(6);
            if(r==0){
                r++;
            }
            String text = null;
            Problems problem=new Problems();
            ResultSet problema=query.GetProblem(dbconn,"P"+r);
            if(problema.next()){
                text=problema.getString("TEXT_PROBLEMA");
                problem.setText_problema(text);
                String dom=problema.getString("DOMENIU");
                problem.id=problema.getString("ID");

            }
            problems.add(problem);
            model.addObject("problema"+i,text);
        }
        Questions quest=new Questions();
        quest.setId("Problem");
        quest.setProblems(problems);
        QuestionsInTest.add(quest);
        // System.out.println(nrOfQuestions);
        CreateXMLFile.generateTestXML(QuestionsInTest);
        System.out.println(QuestionsInTest);
        return model;
    }



    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String processAnswears(@RequestParam List<String> question) {
        List<String> answears = question;
        CreateXMLFile.generateAnswearsXML(QuestionsInTest, answears);
        System.out.println(answears);
        return "redirect:/generate/succes";
    }

    @RequestMapping(value = "/succes")
    public String getSucces() {
        return "generate/succes";
    }

    private boolean check_rand_number(int nr) {

        if (nr == 0) {
            nr++;
        }
        if (questionNumber.isEmpty()) {
            questionNumber.add(nr);
            return false;
        } else {
            if (questionNumber.contains(nr))
                return true;
            else
                return false;
        }
    }

}

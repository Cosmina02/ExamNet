package com.example.proiectpa.controllers;

import java.util.ArrayList;

public class Questions {
    String question_text;
    ArrayList<String> answears;

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public ArrayList<String> getAnswears() {
        return answears;
    }

    public void setAnswears(ArrayList<String> answears) {
        this.answears = answears;
    }
}

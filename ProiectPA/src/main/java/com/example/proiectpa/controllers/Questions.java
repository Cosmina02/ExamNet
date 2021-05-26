package com.example.proiectpa.controllers;

import java.util.ArrayList;

public class Questions {
    String question_text;
    String id;
    ArrayList<Answears> answears;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public ArrayList<Answears> getAnswears() {
        return answears;
    }

    public void setAnswears(ArrayList<Answears> answears) {
        this.answears = answears;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "question_text='" + question_text + '\'' +
                ", id='" + id + '\'' +
                ", answears=" + answears +
                "}\n";
    }
}

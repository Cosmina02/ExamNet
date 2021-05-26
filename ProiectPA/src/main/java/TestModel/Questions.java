package TestModel;

import TestModel.Answears;

import java.util.ArrayList;

public class Questions {
    public String question_text;
    public String id;
    public ArrayList<Answears> answears;
    public ArrayList<Problems> problems;

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

    public ArrayList<Problems> getProblems() {
        return problems;
    }

    public void setProblems(ArrayList<Problems> problems) {
        this.problems = problems;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "question_text='" + question_text + '\'' +
                ", id='" + id + '\'' +
                ", answears=" + answears +
                ", problems=" + problems +
                '}';
    }
}

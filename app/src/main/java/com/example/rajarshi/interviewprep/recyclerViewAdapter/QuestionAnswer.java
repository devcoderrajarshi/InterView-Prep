package com.example.rajarshi.interviewprep.recyclerViewAdapter;

public class QuestionAnswer {
    private String questions;
    private String answers;

    public QuestionAnswer(String questions, String answers) {
        this.questions = questions;
        this.answers = answers;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }
}

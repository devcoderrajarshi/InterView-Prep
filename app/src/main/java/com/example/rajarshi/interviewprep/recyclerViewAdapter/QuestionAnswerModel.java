package com.example.rajarshi.interviewprep.recyclerViewAdapter;

public class QuestionAnswerModel {
    private String questions;
    private String answers;

    public QuestionAnswerModel(String questions, String answers) {
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

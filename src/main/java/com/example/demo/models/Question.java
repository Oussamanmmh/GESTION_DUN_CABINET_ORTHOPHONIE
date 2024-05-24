package com.example.demo.models;

public abstract class Question {


    protected String enonce;
    protected int score; //score de la question

    public int getScore() {
        return score;
    }

    public String getEnonce(){

        return this.enonce;
    }

    public Question(String enonce){this.enonce=enonce;}

    public abstract int getNote();

}

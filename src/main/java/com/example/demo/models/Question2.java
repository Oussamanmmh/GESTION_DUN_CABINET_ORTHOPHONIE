package com.example.demo.models;

public abstract class Question2 {

    protected String enonce;
    protected int score; //score de la question

    public int getScore() {
        return score;
    }
    public Question2(String enonce) {
        this.enonce = enonce;
    }
    public String getEnonce() {
        return enonce;
    }

}

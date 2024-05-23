package com.example.demo.models;

public class QuestionAnamAdult extends Question{
    private String enonce;

    private CathegorieAdult cathegorie;


    public QuestionAnamAdult(String enonce, CathegorieAdult cathegorieAdult) {
        this.enonce = enonce;

        this.cathegorie = cathegorieAdult;
    }

    public String getEnonce() {
        return this.enonce;
    }

    public CathegorieAdult getCathegorie(){
        return this.cathegorie ;
    }



}

package com.example.demo.models;

public class QesrepLibre extends Question2{
    private String reponse ;
    private final String type = "QUESTION A REPONSE LIBRE" ;

    public String getType() {
        return type;
    }

    public QesrepLibre(String enonce, String reponse) {
        super(enonce);
        this.reponse = reponse;

    }

public String getReponse() {
        return reponse;
    }

    @Override
    public String getEnonce() {
        return enonce;
    }
}

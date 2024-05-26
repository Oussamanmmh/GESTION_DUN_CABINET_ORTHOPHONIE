package com.example.demo.models;

public class QesrepLibre extends Question2{
    private String reponse ;
    private String reponsePatient ;
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

    public void setReponsePatient(String userAnswer) {
        this.reponsePatient = userAnswer;
    }
    public String getReponsePatient() {
        return reponsePatient;
    }
    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }
}

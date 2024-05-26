package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class Qcu extends  Question2{
    private final String type = "QCU" ;
    //une seule reponse possible
    public String getType() {
        return type;
    }

    private List<String> propositions ;//les propositions de la question
    private String reponse ; // la reponse correcte
    private String reponsePatient ; // la reponse du patient

    public Qcu(String enonce , List<String> propositions , String reponse){
        super(enonce);
        this.propositions= new ArrayList<>(propositions);
        this.reponse = reponse;


    }

    public String getReponse(){
        return this.reponse ;
    }
    public List<String> getPropositions(){
        return  this.propositions ;
    }
    public void setReponse(String reponse){
        this.reponse = reponse ;

    }


    public String getEnonce() {
        return enonce;
    }
    public void setReponsePatient(String reponsePatient){
        this.reponsePatient = reponsePatient ;
    }
}

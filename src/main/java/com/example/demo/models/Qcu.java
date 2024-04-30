package com.example.demo.models;

import java.util.List;

public class Qcu extends  Question{
    //une seule reponse possible
    private List<String> propositions ;//les propositions de la question
    private String reponse ; // la reponse correcte
    private String reponsePatient ; // la reponse du patient
}

package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

public class AnamneseAdult extends Anamnese {

    Set<QuestionAnamAdult> questionsAdult; // liste des questions anamnese adulte des questions a reponse libre


    public AnamneseAdult(){
        this.questionsAdult = new HashSet<>();
    }


}

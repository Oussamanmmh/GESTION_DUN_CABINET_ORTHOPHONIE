package com.example.demo.models;

import java.util.List;

public class TestExercice extends Test {
    //test exercice contient list des exercices
    private List<Exercice> exercices;

    public double calculerMoyenneExercice(Exercice exo)
    {
        int i = 0 ;
        int somme = 0 ;
        for (Exercice e : exercices)
        {
            if(exo.equals(e)) {
                somme += e.getScore();
                i++;
            }

        }
        return somme/i ;
    }

    public double calculerScore (){
        int score = 0 ;
        for (Exercice exo : exercices)
        {
            score += calculerMoyenneExercice(exo);
        }
        return score ;

    }








}

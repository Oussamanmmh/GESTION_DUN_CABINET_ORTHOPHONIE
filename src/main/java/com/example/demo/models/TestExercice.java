package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestExercice extends Test {
    //test exercice contient list des exercices
    private List<Exercice> exercices;


    public TestExercice(String nom, List<Exercice> exercices) {
        super(nom);
        this.exercices = new ArrayList<>(exercices);
    }

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

    public String getNom() {
        return nom;
    }

    @Override
    public Set<Question2> getQuestions() {
        return null;
    }

    @Override
    public void ajouterQuestion(Question2 question) {
   return;
    }
    public List<Exercice> getExercices(){
        return this.exercices ;
    }
 public void ajouterExercice(Exercice exercice)
 {
     this.exercices.add(exercice);
 }

}

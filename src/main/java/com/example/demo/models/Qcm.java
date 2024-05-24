package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class Qcm extends Question{
    // plusieurs reponses possible
    private List<String> propositions =new ArrayList<>();//les propositions de la question
    private List<String> reponses= new ArrayList<>();// les reponses correctes
    private List<String> reponsesPatient=new ArrayList<>() ;// les reponses du patient

public Qcm(String enonce,List<String> propositions,List<String> reponses){

    super(enonce);
    this.propositions.addAll(propositions);
    this.reponses.addAll(reponses);
}
public List<String> getPropositions(){return this.propositions;}

public void setReponsesPatient(List<String> reponsesPatient){

    this.reponsesPatient.addAll(reponsesPatient);
}

    public int getNote() {
        for (String rp : reponsesPatient) {
            boolean trueChoice = false;
            for (String reponse : reponses) {
                if (rp.equals(reponse)) {
                    trueChoice = true;
                    break; // Une fois la réponse trouvée, on peut sortir de la boucle interne
                }
            }
            if (!trueChoice) {
                return 0; // Si une réponse de l'utilisateur n'est pas correcte, retourner 0
            }
        }
        return score; // Si toutes les réponses de l'utilisateur sont correctes, retourner le score
    }

}

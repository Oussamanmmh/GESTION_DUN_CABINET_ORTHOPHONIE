package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;


public class AgendaIntegre {
    private List<RendezVous> rendezVous;

    public List<RendezVous> getRendezVous() {
        return rendezVous;
    }

    //Cette methode est destine pour ajouter des rendez-vous
    public void addRendezVous(RendezVous rendezVous, String personne) throws IllegalArgumentException {
         try {
             System.out.println("helloe we have : " + rendezVous.getHeure().getHours() + "and" + rendezVous.getDate());
             if (this.rendezVous.isEmpty()) this.rendezVous.add(rendezVous);
             else {
                 for (RendezVous r : this.rendezVous) {

                     if (r.getDate().equals(rendezVous.getDate()) && r.getHeure().equals(rendezVous.getHeure())) {
                         throw new IllegalArgumentException("Rendez-vous déjà pris");
                     } else {
                         if (r.getDate().equals(rendezVous.getDate())) {

                             if (rendezVous instanceof Consultation) {
                                 System.out.println("vous etes dans une consultation");
                                 switch (personne) {
                                     case "Adult":
                                         if (r.getTempsEnMinutes() - rendezVous.getTempsEnMinutes() <=90 && rendezVous.getTempsEnMinutes() - r.getTempsEnMinutes() <= 90) {
                                             throw new IllegalArgumentException("Rendez-vous déjà pris****");}

                                         break;
                                     case "Enfant":
                                         if ((r.getTempsEnMinutes() - rendezVous.getTempsEnMinutes() <= 180  )&& r.getTempsEnMinutes() - rendezVous.getTempsEnMinutes() <= 180) {

                                             throw new IllegalArgumentException("Rendez-vous déjà pris");
                                         }
                                         break;
                                 }
                             } else {
                                 if (r.getTempsEnMinutes() - rendezVous.getTempsEnMinutes() <= 60 && r.getTempsEnMinutes() - rendezVous.getTempsEnMinutes() <=60) {
                                     throw new IllegalArgumentException("Rendez-vous déjà pris");
                                 }
                             }


                         }
                     }

                 }
             }

             this.displayRDV();
             this.rendezVous.add(rendezVous);
             System.out.println("rendez-vous ajouter avec succes");
         }
         catch (IllegalArgumentException e)
         {
             System.out.println("big error");
             throw new IllegalArgumentException(e.getMessage()) ;
         }
    }

    public AgendaIntegre() {
        rendezVous = new ArrayList<>();

    }

    public void displayRDV() {
        //methode afficer tout les rdv
        for (RendezVous r : this.rendezVous) {
            System.out.println(r.getDate() + " in " + r.getHeure());
        }

    }

}

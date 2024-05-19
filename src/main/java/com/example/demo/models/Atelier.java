package com.example.demo.models;
import java.sql.Time;
import java.util.*;

public class Atelier extends RendezVous {
    private String thematique;
   private ArrayList <Integer> listeNumeroDossierPatient ;//maybe {{ map  }}

    public Atelier(String date, Time time, String thematique) {
        super(date, time);
        this.thematique = thematique;
    }


}

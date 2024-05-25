package com.example.demo.models;
import java.sql.Time;
import java.util.*;

public class Atelier extends RendezVous {
    private String thematique;
   private ArrayList <Integer> listeNumeroDossierPatient ;//maybe {{ map  }}
    private final String type = "Atelier" ;

public Atelier(Date date, Time heure){
    super(date,heure);
}

    @Override
    public String getType() {
        return "Atelier";
    }


}

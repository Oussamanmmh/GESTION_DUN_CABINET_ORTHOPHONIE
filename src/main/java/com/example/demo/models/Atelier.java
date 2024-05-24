package com.example.demo.models;
import java.sql.Time;
import java.util.*;

public class Atelier extends RendezVous {
    private String thematique;
   private ArrayList <Integer> listeNumeroDossierPatient ;//maybe {{ map  }}
    private final String type = "Atelier" ;

    public Atelier(String date, Time time, String thematique , List<Integer> listeNumeroDossierPatient) {
        super(date, time);
        this.thematique = thematique;
        this.listeNumeroDossierPatient = new ArrayList<>(listeNumeroDossierPatient);
    }
    public String getType()
    {
        return this.type;
    }

    public String getThematique()
    {
        return this.thematique;
    }
    public ArrayList<Integer> getListeNumeroDossierPatient()
    {
        return this.listeNumeroDossierPatient;
    }

}

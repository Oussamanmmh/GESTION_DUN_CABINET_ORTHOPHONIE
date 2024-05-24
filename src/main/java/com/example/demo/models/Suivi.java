package com.example.demo.models;
import java.sql.Time;
import java.time.Duration;
import java.util.Date;

public class Suivi extends RendezVous{
    private DeroulementSeance typeSeance;
    private int numeroDossierDePatient;

    public  Suivi(DeroulementSeance deroulementSeance , int numeroDossierDePatient, Date date, Time heure)
    {
        super(date,heure);
        this.typeSeance = deroulementSeance;
        this.numeroDossierDePatient = numeroDossierDePatient;

    }

    // Getters and Setters
    public DeroulementSeance getTypeSeance() {
        return typeSeance;
    }

    public void setTypeSeance(DeroulementSeance typeSeance) {
        this.typeSeance = typeSeance;
    }

    public int getNumeroDossierDePatient() {
        return numeroDossierDePatient;
    }

    public void setNumeroDossierDePatient(int numeroDossierDePatient) {
        this.numeroDossierDePatient = numeroDossierDePatient;
    }
    public String getType(){
        return "Suivi";
    }
  }

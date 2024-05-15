package com.example.demo.models;

public class Suivi extends RendezVous{
    private DeroulementSeance typeSeance;
    private int numeroDossierDePatient;

    public  Suivi(DeroulementSeance deroulementSeance , int numeroDossierDePatient)
    {
        this.typeSeance = deroulementSeance;
        this.numeroDossierDePatient = numeroDossierDePatient;

    }



  }

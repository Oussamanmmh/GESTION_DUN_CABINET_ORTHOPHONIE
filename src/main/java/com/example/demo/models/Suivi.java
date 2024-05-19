package com.example.demo.models;

import java.sql.Time;

public class Suivi extends RendezVous{
    private DeroulementSeance typeSeance;
    private int numeroDossierDePatient;

    public  Suivi(DeroulementSeance deroulementSeance , int numeroDossierDePatient , String date , Time time)
    {
        super(date , time);
        this.typeSeance = deroulementSeance;
        this.numeroDossierDePatient = numeroDossierDePatient;

    }



  }

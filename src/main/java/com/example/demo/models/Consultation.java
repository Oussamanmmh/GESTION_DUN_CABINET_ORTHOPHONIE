package com.example.demo.models;

import java.sql.Time;
import java.time.Duration;
import java.time.Duration;
import java.util.Date;

public class Consultation extends RendezVous{

    //on a pas encore creer le patient
    private String nomPatient ;
    private String prenomPatient ;
    private int agePatient ;
    public  Consultation (int agePatient , String nomPatient , String prenomPatient,Date date, Time heure)
    {
        super(date,heure);
        this.nomPatient = nomPatient ;
        this.prenomPatient = prenomPatient ;

        if(agePatient >= 18 ) //en fonction de l'age du patient on definit la duree de la consultation
        {
            super.duree =  Duration.ofMinutes(90);
        }
        else
        {
            super.duree = Duration.ofMinutes(150);
        }
        {

        }

    }

    // Getters and Setters
    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public String getPrenomPatient() {
        return prenomPatient;
    }

    public void setPrenomPatient(String prenomPatient) {
        this.prenomPatient = prenomPatient;
    }

    public int getAgePatient() {
        return agePatient;
    }

    public void setAgePatient(int agePatient) {
        this.agePatient = agePatient;
    }
    public String getType(){
        return "Consultaion";
    }


}

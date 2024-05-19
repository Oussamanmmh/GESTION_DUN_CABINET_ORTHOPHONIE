package com.example.demo.models;

import java.sql.Time;
import java.time.Duration;

public class Consultation extends RendezVous{

    //on a pas encore creer le patient
    private String nomPatient ;
    private String prenomPatient ;
    private int agePatient ;
    public  Consultation (int agePatient , String nomPatient , String prenomPatient , String date , Time time)
    {
        super(date , time);
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

}

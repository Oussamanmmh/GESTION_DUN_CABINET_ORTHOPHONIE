package com.example.demo.models;

import java.time.Duration;

public class Consultation extends RendezVous{

    public  Consultation (TypePatient typePatient)
    {
        if(typePatient == TypePatient.ADULTE)
        {
            super.duree =  Duration.ofMinutes(90);
        }
        else
        {
            super.duree = Duration.ofMinutes(120);
        }
        {

        }

    }

}

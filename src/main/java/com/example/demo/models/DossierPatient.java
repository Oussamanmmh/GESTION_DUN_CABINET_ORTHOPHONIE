package com.example.demo.models;

import java.util.List;

public class DossierPatient  {
    private int nemeroDossier;
    private Patient patient ;

    private List<BilanOrt> bilanOrt ;//le dossier de patient contient une liste de bilans orthophoniques

    //le dossier de patient contient une liste de rendez-vous
    private List<RendezVous> rendezVous ;

    private List<Fichesuivi> fichesuivis  ; //le dossier de patient contient une liste de fiches de suivi



}

package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class DossierPatient {
    private int numeroDossier;
    private Patient patient;
    private static int nbDossier = 0;
    private List<BilanOrt> bilanOrt=new ArrayList<>();; // le dossier de patient contient une liste de bilans orthophoniques

    // le dossier de patient contient une liste de rendez-vous
    private List<RendezVous> rendezVous=new ArrayList<>();;

    private List<Fichesuivi> fichesSuivi=new ArrayList<>();; // le dossier de patient contient une liste de fiches de suivi

    public DossierPatient(Patient patient) {
        nbDossier++;
        this.numeroDossier = 1;
        this.patient = patient;

    }

    public boolean ajouterBilan(BilanOrt bilan) {
        return bilanOrt.add(bilan);
    }

    public boolean ajouterRendezVous(RendezVous rdv) {
        return rendezVous.add(rdv);
    }

    public boolean ajouterFiche(Fichesuivi fiche) {
        return fichesSuivi.add(fiche);
    }

    public Patient getPatient() {
        return this.patient;
    }

    public int getNumeroDossier() {
        return this.numeroDossier;
    }

    public List<RendezVous> getRendezVous() {
        return this.rendezVous;
    }

    public List<Fichesuivi> getFichesSuivi() {
        return this.fichesSuivi;
    }

    public List<BilanOrt> getBilanOrt() {
        return this.bilanOrt;
    }
}

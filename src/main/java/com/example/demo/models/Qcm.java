package com.example.demo.models;

import java.util.List;

public class Qcm extends Question{
    // plusieurs reponses possible
    private List<String> propositions ;//les propositions de la question
    private List<String> reponses ;// les reponses correctes
    private List<String> reponsesPatient ;// les reponses du patient

}

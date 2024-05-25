package com.example.demo.models;
import java.util.ArrayList;
import java.util.List;

public class EpreuveClinique {
    private  String observation;
    private List<Test> tests ;

    public EpreuveClinique() {
        this.tests = new ArrayList<>();
    }

    public void ajouterTest(Test test){
        tests.add(test);
    }
    public List<Test> getTests() {
        return tests;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

}

package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

public class TestQCU extends TestQuestionnaire{

    public TestQCU( Set<Qcu> questions) {
        //affecter les questions a la liste des questions
        super.questions = new HashSet<Qcu>(questions);
    }

}

package com.example.demo.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestQCU extends TestQuestionnaire{

    public TestQCU( Set<Qcu> questions) {
        super("","", List.of());
        //affecter les questions a la liste des questions
        super.questions = new HashSet<Qcu>(questions);
    }

}

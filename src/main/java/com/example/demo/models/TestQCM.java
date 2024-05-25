package com.example.demo.models;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class TestQCM extends TestQuestionnaire{

    public TestQCM( Set<Qcm> questions) {
        super("","", List.of() );
        super.questions = new HashSet<Qcm>(questions);


    }
}

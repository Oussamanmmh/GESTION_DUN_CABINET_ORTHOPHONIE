package com.example.demo.models;

import java.util.Set;
import java.util.HashSet;

public class QuestTest extends TestQuestionnaire{ //question a reponse libre contient une list des questions

    public QuestTest( Set<Question> questions) {
        super.questions = new HashSet<Question>(questions) ;
    }



}

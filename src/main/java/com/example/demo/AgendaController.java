package com.example.demo;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AgendaController implements Initializable {
  private   ComboBox<Integer> hours = new ComboBox<>();
   private ComboBox<Integer> minutes = new ComboBox<>();
   private ComboBox<String> amPm = new ComboBox<>();
    AnchorPane pan ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 1; i <= 12; i++) {
            hours.getItems().add(i);
        }

        // Populate minutes
        for (int i = 0; i < 60; i++) {
            minutes.getItems().add(i);
        }

        // Populate AM/PM
        amPm.getItems().addAll("AM", "PM");

        // Set default values
        hours.setValue(1);
        minutes.setValue(0);
        amPm.setValue("AM");

        pan.getChildren().addAll(hours ,minutes , amPm) ;

    }
}

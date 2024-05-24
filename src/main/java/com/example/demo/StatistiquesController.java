package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class StatistiquesController implements Initializable {
    @FXML
    BorderPane borderPan ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        CategoryAxis xAxis = new CategoryAxis();
//        NumberAxis yAxis = new NumberAxis();
//        xAxis.setLabel("Troubles");
//        yAxis.setLabel("Nombre de patients");
//
//        BarChart barChart = new BarChart(xAxis, yAxis);
//        barChart.setTitle("Statistiques des troubles");
//        XYChart.Series data= new XYChart.Series();
//        data.getData().add(new XYChart.Data("Trouble de la parole", 10));
//        data.getData().add(new XYChart.Data("Trouble de la voix", 5));
//        data.getData().add(new XYChart.Data("Trouble de la déglutition", 3));
//        data.getData().add(new XYChart.Data("Trouble de la communication", 7));
//        barChart.getData().add(data);
//        borderPan.setCenter(barChart);
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Pourcentage des troubles");

        PieChart.Data slice1 = new PieChart.Data("Trouble de la parole", 10);
        PieChart.Data slice2 = new PieChart.Data("Trouble de la voix", 5);
        PieChart.Data slice3 = new PieChart.Data("Trouble de la déglutition", 3);
        PieChart.Data slice4 = new PieChart.Data("Trouble de la communication", 7);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);
        pieChart.getData().add(slice4);


        // Calculer le total pour le pourcentage
        final double total = slice1.getPieValue() + slice2.getPieValue() + slice3.getPieValue() + slice4.getPieValue();

        // Ajouter un Tooltip à chaque tranche pour afficher le pourcentage
        pieChart.getData().forEach(data -> {
            String percentage = String.format("%.1f%%", (data.getPieValue() / total) * 100);
            Tooltip tooltip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), tooltip);
        });

        borderPan.setCenter(pieChart);

    }
}

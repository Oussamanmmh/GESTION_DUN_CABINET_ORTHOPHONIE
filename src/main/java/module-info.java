module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires com.calendarfx.view;
    requires ical4j.core;


    opens com.example.demo to javafx.fxml;
    opens com.example.demo.models to javafx.base;
    exports com.example.demo;
}
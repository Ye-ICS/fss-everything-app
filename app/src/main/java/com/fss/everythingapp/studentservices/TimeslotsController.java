package com.fss.everythingapp.studentservices;

import java.util.List;

import org.checkerframework.checker.units.qual.C;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class TimeslotsController {
    private static Date date;
    private static Timeslot timeslot;
    private static ChoiceBox<Date> dateBox = new ChoiceBox<>();
    private static ChoiceBox<Timeslot> timeBox = new ChoiceBox<>();

    @FXML
    void initialize() {
        //dateBox.getItems().addAll(SSCalendar.dates);


        
        //timeBox.getItems().addAll(Date.getTimes());
    }

    @FXML
    private void enterBtnPressed() {
        date = dateBox.getValue();
        timeslot = timeBox.getValue();

        // send updated info to student services
    }
}

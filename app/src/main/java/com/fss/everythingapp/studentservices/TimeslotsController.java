package com.fss.everythingapp.studentservices;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class TimeslotsController {
    private static int date;
    private static Timeslot timeslot;
    private static ChoiceBox<Integer> dateBox = new ChoiceBox<>();
    private static ChoiceBox<Timeslot> timeBox = new ChoiceBox<>();
    private static List<Date> dates = new ArrayList<>();
    private List<Timeslot> times = new ArrayList<>();

    static void timeslotController(List<Date> date) {
        dates = date;
    }

    @FXML
    void initialize() {
        dateBox.getItems().addAll(dateNums());
        date = dateBox.getValue();

        timeBox.getItems().addAll(times);
        timeslot = timeBox.getValue();
    }

    private static ObservableList<Integer> dateNums() {
        ObservableList<Integer> dateNums = FXCollections.observableArrayList();
        for (int i = 0; i < 30; i++) {
            dateNums.add(i + 1);
        }
        return dateNums;
    }

    @FXML
    private void enterBtnPressed() {
        // send updated info to student services
    }

}

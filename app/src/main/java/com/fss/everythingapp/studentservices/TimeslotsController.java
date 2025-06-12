package com.fss.everythingapp.studentservices;

import java.util.ArrayList;
import java.util.List;

import com.fss.everythingapp.app.App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class TimeslotsController {
    private static int date;
    private static String timeslot;
    private static ChoiceBox<Integer> dateBox = new ChoiceBox<>();
    private static ChoiceBox<String> timeBox = new ChoiceBox<>();
    private static List<Date> dates = new ArrayList<>();
    private List<Timeslot> times = new ArrayList<>();

    static void timeslotController(List<Date> date) {
        dates = date;
    }

    @FXML
    void initialize() {
        dateBox.setItems(dateNums());
    }

    private ObservableList<Integer> dateNums() {
        ObservableList<Integer> dateNums = FXCollections.observableArrayList();
        for (int i = 0; i < 30; i++) {
            dateNums.add(i + 1);
        }
        return dateNums;
    }

    private ObservableList<String> timeFiller() {
        ObservableList<String> timeFiller = FXCollections.observableArrayList();
        for (int i = 0; i < 4; i++) {
            timeFiller.add(((2 * i) + 1) + ". " + (i + 9) + ":00 - " + (i + 9) + ":30");
            timeFiller.add(((2 * i) + 2) + ". " + (i + 9) + ":30 - " + (i + 10) + ":00");
        }
        return timeFiller;
    }

    @FXML
    private void enterBtnPressed1() {
        date = dateBox.getValue();
        times = dates.get(date).getTimes();
        if (times == null) {
            // say something like "date unavailable"
        } else {
            timeBox.setItems(timeFiller());
        }
    }

    @FXML
    private void enterBtnPressed2() {
        timeslot = timeBox.getValue();
        String selection = timeslot.substring(0, 1);
        int choice = Integer.parseInt(selection);
        times.get(choice).setAvailable();
        dates.get(date).updateTimes(times);
    }

    @FXML
    private void onExitBtnPressed() {
        App.backToMainMenu();
    }
}

package com.fss.everythingapp.calendar;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreateDateController {

    @FXML
    private VBox rootContainer;

    @FXML
    void createEvent(ActionEvent event) throws IOException {
        Parent createEvent = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/CreateEvent.fxml"));
        rootContainer.getScene().setRoot(createEvent);
    }

    @FXML
    void createTask(ActionEvent event) throws IOException {
        Parent createTask = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/CreateTask.fxml"));
        rootContainer.getScene().setRoot(createTask);
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Parent home = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/MonthView.fxml"));
        rootContainer.getScene().setRoot(home);
    }
}
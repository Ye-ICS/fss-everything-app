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
    private VBox rootContainer;

    @FXML
    private Button createEventButton;

    @FXML
    private Button createTaskButton;

    @FXML
    void createEvent(ActionEvent event) throws IOException {
        Parent createEvent = (Parent) FXMLLoader.load(getClass().getResource("CreateEvent.fxlm"));
        rootContainer.getScene().setRoot(createEvent);
    }

    @FXML
    void createTask(ActionEvent event) throws IOException { // NOT FUNCTIONAL
        Parent createTask = (Parent) FXMLLoader.load(getClass().getResource("CreateTask.fxlm"));
        rootContainer.getScene().setRoot(createTask);
    }
}
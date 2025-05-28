package com.fss.everythingapp.calendar;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MonthViewController {

    @FXML
    private Button createDateButton;

    @FXML
    private BorderPane rootContainer;

    @FXML
    void createDate(ActionEvent event) throws IOException {
        Parent createDate = (Parent) FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/calendar/CreateDate.fxlm"));
        rootContainer.getScene().setRoot(createDate);
    }
}
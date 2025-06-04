package com.fss.everythingapp.app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainMenuController {

    @FXML
    BorderPane rootContainer;

    @FXML
    private void openStudentServices(ActionEvent actionEvent) {

    }

    @FXML
    private void openBuses(ActionEvent actionEvent) throws IOException {
        Parent mainListLayout = (Parent) FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/businfo/List.fxml"));
        rootContainer.getScene().setRoot(mainListLayout);
    }

    @FXML
    private void openCalendar(ActionEvent actionEvent) throws IOException {
        Parent monthView = (Parent) FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/MonthView.fxml"));
        rootContainer.getScene().setRoot(monthView);
    }
}
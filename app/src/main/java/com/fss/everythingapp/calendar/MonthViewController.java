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
    private BorderPane rootContainer;

    @FXML
    void createDate(ActionEvent event) throws IOException {
        Parent monthView = (Parent) FXMLLoader.load(getClass().getResource("UserMenu.fxlm"));
        rootContainer.getScene().setRoot(monthView);
    }
}
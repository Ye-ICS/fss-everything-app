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
        try {
            Parent mainListLayout = (Parent) FXMLLoader.load(getClass().getResource("../businfo/List.fxml"));
        rootContainer.getScene().setRoot(mainListLayout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

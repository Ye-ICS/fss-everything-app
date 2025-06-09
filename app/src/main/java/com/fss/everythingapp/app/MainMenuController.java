package com.fss.everythingapp.app;

import java.io.IOException;

import com.fss.everythingapp.businfo.ListController;

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
            FXMLLoader mainListLoader = new FXMLLoader(getClass().getResource("../businfo/List.fxml"));
            ListController listController = new ListController();
            mainListLoader.setController(listController);
            Parent mainListLayout = mainListLoader.load();
            rootContainer.getScene().setRoot(mainListLayout);
            listController.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

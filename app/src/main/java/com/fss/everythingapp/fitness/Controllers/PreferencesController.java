package com.fss.everythingapp.fitness.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class PreferencesController {

    @FXML
    private ToggleGroup physiqueGroup;

    @FXML
    void submitPref(ActionEvent event) {

    }

    @FXML
    void backToMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(MMSignedInController.class.getResource("/com/fss/everythingapp/app/fxml/MainMenu-Signedin.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML: " + e.getMessage());
        }
    }

}


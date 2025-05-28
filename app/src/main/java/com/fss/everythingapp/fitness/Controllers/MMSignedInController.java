package com.fss.everythingapp.fitness.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MMSignedInController {

    @FXML
    void openLogMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LogMenu.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openLogRecords(ActionEvent event) {
        
    }

}

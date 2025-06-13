package com.fss.everythingapp.fitness.Controllers;

import java.io.IOException;

import com.fss.everythingapp.fitness.GeneralInfo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NotSignedInController {

    @FXML
    private Button signInButton;

    @FXML
    private void signIn (ActionEvent actionEvent) {
        //readFromFile();
        if (GeneralInfo.name.isEmpty()) {
            try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/Fitness/fxml/SigningIn.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error loading FXML: " + e.getMessage());
            }
        } else {
            try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/Fitness/fxml/MainMenuFitness.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML: " + e.getMessage());
        }
        }
    }


}
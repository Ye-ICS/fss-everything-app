package com.fss.everythingapp.fitness.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoggingController {

    @FXML
    private TextField caloriesEaten;

    @FXML
    private TextField carbsEaten;

    @FXML
    private TextField cholesterolEaten;

    @FXML
    private TextField coloriesBurnt;

    @FXML
    private TextField fatsEaten;

    @FXML
    private TextField proteinEaten;

    @FXML
    private TextField timeWentSleep;

    @FXML
    private TextField timeWokeUp;

    @FXML
    void submitExcersizeLog(ActionEvent event) {
        
    }

    @FXML
    void submitFoodLog(ActionEvent event) {

    }

    @FXML
    void submitSleepLog(ActionEvent event) {

    }

    @FXML

    void backToMM(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(MMSigninginController.class.getResource("/com/fss/everythingapp/app/fxml/MainMenu-SignedIn.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
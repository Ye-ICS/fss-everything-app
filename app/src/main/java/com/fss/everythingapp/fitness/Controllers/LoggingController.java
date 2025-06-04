package com.fss.everythingapp.fitness.Controllers;

import java.io.IOException;

import com.fss.everythingapp.fitness.Diet;
import com.fss.everythingapp.fitness.Workout;

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
    public void initialize() {
        restrictToNumbers(proteinEaten, true);
        restrictToNumbers(caloriesEaten, true);
        restrictToNumbers(carbsEaten, true);
        restrictToNumbers(fatsEaten, true);
        restrictToNumbers(coloriesBurnt, true);
        restrictToNumbers(timeWentSleep, false);
        restrictToNumbers(timeWokeUp, false);
    }

    @FXML
    void submitExcersizeLog(ActionEvent event) {
        Workout.caloriesBurned += Integer.parseInt(coloriesBurnt.getText());
    }

    @FXML
    void submitFoodLog(ActionEvent event) {
        Diet.caloriesEaten += Integer.parseInt(caloriesEaten.getText());
        Diet.carbsEaten += Integer.parseInt(carbsEaten.getText());
        Diet.proteinEaten += Integer.parseInt(proteinEaten.getText());
        Diet.fatsEaten += Integer.parseInt(fatsEaten.getText());
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

        private void restrictToNumbers(TextField field, boolean allowDecimal) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(allowDecimal ? "\\d*(\\.\\d*)?" : "\\d*")) {
                field.setText(oldValue);
            }
        });
    }

}
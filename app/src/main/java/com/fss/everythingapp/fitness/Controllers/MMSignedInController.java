package com.fss.everythingapp.fitness.Controllers;

import java.io.IOException;

import com.fss.everythingapp.fitness.Workout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MMSignedInController {

    @FXML
    void openDietMenu(ActionEvent event) {
    if (checkIfPreferencesSelected() == false) {
        showValidationAlert();
    }
    else {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/app/fxml/DietMenu.fxml"));
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

    @FXML
    void openLogMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/app/fxml/LogMenu.fxml"));
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

    @FXML
    void openSleepMenu(ActionEvent event) {
        if (checkIfPreferencesSelected() == false) {
            showValidationAlert();
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/app/fxml/SleepMenu.fxml"));
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

    @FXML
    void openWorkoutMenu(ActionEvent event) {
        if (checkIfPreferencesSelected() == false) {
            showValidationAlert();
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/app/fxml/WorkoutMenu.fxml"));
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

    @FXML
    void openPreferenceMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/app/fxml/Preferences.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML: " + e.getMessage());
        }
    }

    private void showValidationAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Please select preferences first");
        alert.showAndWait();
    }

    private boolean checkIfPreferencesSelected() {
        if (Workout.desiredPhysique == null || Workout.desiredPhysique.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }
}
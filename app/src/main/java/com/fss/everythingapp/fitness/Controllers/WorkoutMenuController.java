package com.fss.everythingapp.fitness.Controllers;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fss.everythingapp.fitness.DietManager;
import com.fss.everythingapp.fitness.Workout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WorkoutMenuController {

    @FXML
    public void initialize() {
        DecimalFormat df = new DecimalFormat("#.##");
        DietManager.newDiet();
        calorieBurntBar.setProgress(Workout.caloriesBurned / 500.0);
        calorieBurntGoal.setText("Calories Burned: " + df.format(Workout.caloriesBurned) + " / 500");
    }

    @FXML
    private ProgressBar calorieBurntBar;

    @FXML
    private Text calorieBurntGoal;

    @FXML
    void backToMM(ActionEvent event) {
                try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/app/fxml/MainMenu.fxml"));
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
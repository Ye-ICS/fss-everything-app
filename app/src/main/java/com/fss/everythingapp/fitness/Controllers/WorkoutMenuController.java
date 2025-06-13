package com.fss.everythingapp.fitness.Controllers;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fss.everythingapp.fitness.Workout;
import com.fss.everythingapp.fitness.WorkoutManager;

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
    private ProgressBar calorieBurntBar;

    @FXML
    private Text calorieBurntGoal;
    
    @FXML
    private ProgressBar compoundExBar;

    @FXML
    private Text compundExText;

    @FXML
    private ProgressBar fullBodyExBar;

    @FXML
    private Text fullBodyExText;

    @FXML
    public void initialize() {
        WorkoutManager.calculateCaloriesBurntTarget();
        WorkoutManager.calculateExcersizeGoals();
        DecimalFormat df = new DecimalFormat("#.##");
        calorieBurntBar.setProgress(Workout.caloriesBurned / Workout.caloriesBurntTarget);
        calorieBurntGoal.setText("Calories Burned: " + df.format(Workout.caloriesBurned) + " / " + df.format(Workout.caloriesBurntTarget));
        compoundExBar.setProgress(Workout.compoundExDone / Workout.compoundExGoal);
        compundExText.setText("Compound Excersizes Goal (In minutes): " + Workout.compoundExDone + " / " + Workout.compoundExGoal);
        fullBodyExBar.setProgress(Workout.fullbodyExDone / Workout.fullbodyExTarget);
        fullBodyExText.setText("Full Body Excersizes Goal (In minutes): " + Workout.fullbodyExDone + " / " + Workout.fullbodyExTarget);

    }


    @FXML
    void backToMM(ActionEvent event) {
                try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/Fitness/fxml/MainMenuFitness.fxml"));
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
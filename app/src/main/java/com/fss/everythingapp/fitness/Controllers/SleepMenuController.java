package com.fss.everythingapp.fitness.Controllers;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fss.everythingapp.fitness.DietManager;
import com.fss.everythingapp.fitness.SleepManager;
import com.fss.everythingapp.fitness.SleepSchedule;
import com.fss.everythingapp.fitness.SleepTimer;
import com.fss.everythingapp.fitness.Workout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SleepMenuController {

    @FXML
    private Text date;

    @FXML
    private Text sleepGoal;

    @FXML
    private ProgressBar sleepGoalBar;
    
    @FXML
    private Button dateButton;

    @FXML
    public void setDate(ActionEvent event) throws IOException, InterruptedException {
    SleepTimer.getDate(null);
    date.setText(SleepTimer.formattedDate);
    }

    @FXML
     public void initialize(ProgressIndicator sleepGoalBar) {
        SleepManager.newSleepRoutine();
        sleepGoalBar.setProgress(SleepSchedule.hoursSlept / SleepSchedule.hoursSleptTarget);
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

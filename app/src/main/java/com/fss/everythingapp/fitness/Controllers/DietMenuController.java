package com.fss.everythingapp.fitness.Controllers;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fss.everythingapp.fitness.Diet;
import com.fss.everythingapp.fitness.DietManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DietMenuController {

    @FXML
    private ProgressBar calorieBar;

    @FXML
    private Text calorieGoal;

    @FXML
    private ProgressBar carbBar;

    @FXML
    private Text carbsGoal;

    @FXML
    private ProgressBar fatsBar;

    @FXML
    private Text fatsGoal;

    @FXML
    private ProgressBar proteinBar;

    @FXML
    private Text protienGoal;

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

    @FXML
    public void initialize() {
        DecimalFormat df = new DecimalFormat("#.##");
        DietManager.newDiet();
        calorieBar.setProgress(Diet.caloriesEaten / Diet.calorieTarget);
        calorieGoal.setText("Calories: " + df.format(Diet.caloriesEaten) + " / " + df.format(Diet.calorieTarget));
        carbBar.setProgress(Diet.carbsEaten / Diet.carbsTarget);
        carbsGoal.setText("Carbs: " + df.format(Diet.carbsEaten) + " / " + df.format(Diet.carbsTarget));
        fatsBar.setProgress(Diet.fatsEaten / Diet.fatsTarget);
        fatsGoal.setText("Fats: " + df.format(Diet.fatsEaten) + " / " + df.format(Diet.fatsTarget));
        proteinBar.setProgress(Diet.proteinEaten / Diet.proteinTarget);
        protienGoal.setText("Protein: " + df.format(Diet.proteinEaten) + " / " + df.format(Diet.proteinTarget));
    }

}

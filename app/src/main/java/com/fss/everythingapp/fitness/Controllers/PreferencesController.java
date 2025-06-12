package com.fss.everythingapp.fitness.Controllers;

import java.io.IOException;

import com.fss.everythingapp.fitness.GeneralInfo;
import com.fss.everythingapp.fitness.Workout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PreferencesController {

    @FXML
    private ToggleGroup physiqueGroup;

    @FXML
    private RadioButton notActiveRadio;
    
    @FXML
    private ToggleGroup activityGroup;

    @FXML
    private RadioButton activeRadio;

    @FXML
    private RadioButton wlRadio;

    @FXML
    private RadioButton muscularRadio;

    @FXML
    private RadioButton leanRadio;

    @FXML
    private Text submitErrorPrompt;


    @FXML
    void submitActivity(ActionEvent event) {
        if (activeRadio.isSelected()) {
            GeneralInfo.isPhysicallyActive = true;
        }
        if (notActiveRadio.isSelected()) {
            GeneralInfo.isPhysicallyActive = false;
        }
        if (wlRadio.isSelected()) {
            Workout.desiredPhysique = "skinny";
        }
        if (muscularRadio.isSelected()) {
            Workout.desiredPhysique = "bulk";
        }
        if (leanRadio.isSelected()) {
            Workout.desiredPhysique = "lean";
        }
        if ((activeRadio.isSelected() || notActiveRadio.isSelected()) && (wlRadio.isSelected() || muscularRadio.isSelected() || leanRadio.isSelected())) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/app/fxml/MainMenuFitness.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error loading FXML: " + e.getMessage());
            } 
        } else {
            submitErrorPrompt.setVisible(true);
        }
    }



    }


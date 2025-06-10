package com.fss.everythingapp.fitness.Controllers;

import java.io.IOException;

import com.fss.everythingapp.fitness.SleepTimer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
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
    public void setDate(ActionEvent event) throws IOException {
    SleepTimer.getDate(null);
    while (true) {
    date.setText(SleepTimer.formattedDate);
    }
    }

    
    @FXML
    boolean sleepMenuOpen(Parent root) throws IOException {
        if (root == FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/app/fxml/SleepMenu.fxml"))) {
            return true;
        } else {
            return false;
        }
    }

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

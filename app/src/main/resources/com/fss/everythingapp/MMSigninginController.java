package com.fss.everythingapp;

import java.io.IOException;

import com.fss.everythingapp.fitness.GeneralInfo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class MMSigninginController {

  @FXML
    private ToggleGroup ActivityChoice;

    @FXML
    private RadioButton activeRadio;

    @FXML
    private TextField ageField;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private ToggleGroup genderToggle;

    @FXML
    private TextField heightField;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private TextField nameField;

    @FXML
    private RadioButton notActiveRadio;

    @FXML
    private Button submitButton;

    @FXML
    private RadioButton veryActiveRadio;

    @FXML
    private TextField weightField;

    @FXML
    private TextField bodyFatField;



    @FXML
    private void handleGenderChoice(ActionEvent event) {
        RadioButton selectedRadio = (RadioButton) genderToggle.getSelectedToggle();
    }

    @FXML  
    private void handleTextFieldAction(ActionEvent event) {
        GeneralInfo.name = nameField.getText();
        GeneralInfo.age = Integer.parseInt(ageField.getText());
        GeneralInfo.height = Integer.parseInt(heightField.getText());
        GeneralInfo.weight = Integer.parseInt(weightField.getText());
        GeneralInfo.isMale = maleRadio.isSelected();
        GeneralInfo.isFemale = femaleRadio.isSelected();
        GeneralInfo.bodyfat =  Integer.parseInt(bodyFatField.getText());
       
    }

    @FXML
    void submitInfo(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainMenu-SignedIn.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

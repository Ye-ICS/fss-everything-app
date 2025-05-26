package com.fss.everythingapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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
    private void handleActivityChoice(ActionEvent event) {
        RadioButton selectedRadio = (RadioButton) ActivityChoice.getSelectedToggle();
        String activityLevel = selectedRadio.getText();
        System.out.println("Selected activity level: " + activityLevel);
    }
    @FXML
    private void handleGenderChoice(ActionEvent event) {
        RadioButton selectedRadio = (RadioButton) genderToggle.getSelectedToggle();
    }
    @FXML  
    private void handleTextFieldAction(ActionEvent event) {
        String name = nameField.getText();
        String age = ageField.getText();
        String height = heightField.getText();
        String weight = weightField.getText();
        boolean isMale = maleRadio.isSelected();
        boolean isFemale = femaleRadio.isSelected();
        double bodyFat =  bodyFatField.getText();
        String activityLevel = ((RadioButton) ActivityChoice.getSelectedToggle()).getText();
    }

}

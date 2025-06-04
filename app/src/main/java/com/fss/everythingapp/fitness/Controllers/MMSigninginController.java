package com.fss.everythingapp.fitness.Controllers;

import java.io.IOException;

import com.fss.everythingapp.fitness.GeneralInfo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    public void initialize() {
        restrictToNumbers(ageField, false);
        restrictToNumbers(heightField, false);
        restrictToNumbers(weightField, false);
        restrictToNumbers(bodyFatField, true);
    }

    private void restrictToNumbers(TextField field, boolean allowDecimal) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(allowDecimal ? "\\d*(\\.\\d*)?" : "\\d*")) {
                field.setText(oldValue);
            }
        });
    }


    @FXML
    void submitInfo(ActionEvent event) {
        if (!isInputValid()) {
            showValidationAlert();
            return;
        }

        GeneralInfo.name = nameField.getText();
        GeneralInfo.age = Integer.parseInt(ageField.getText());
        GeneralInfo.height = Integer.parseInt(heightField.getText());
        GeneralInfo.weight = Integer.parseInt(weightField.getText());
        GeneralInfo.bodyfat = Double.parseDouble(bodyFatField.getText());
        GeneralInfo.isMale = maleRadio.isSelected();
        GeneralInfo.isFemale = femaleRadio.isSelected();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/app/fxml/MainMenu-SignedIn.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean isInputValid() {
    try {
        int age = Integer.parseInt(ageField.getText());
        int height = Integer.parseInt(heightField.getText());
        int weight = Integer.parseInt(weightField.getText());
        double bodyFat = Double.parseDouble(bodyFatField.getText());

        if (genderToggle.getSelectedToggle() == null) return false;
        if (age < 1 || age > 119) return false;
        if (height < 50 || height > 300) return false;      // Height in cm
        if (weight < 10 || weight > 500) return false;      // Weight in kg
        if (bodyFat < 0 || bodyFat > 100) return false;     // Body fat % range


        return true;
    } catch (NumberFormatException e) {
        return false;
    }

    }
    private void showValidationAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText("Please enter valid numeric values");
        alert.setContentText("Make sure that:\n- Age is 1-119\n- Height is 50-300 cm\n- Weight is 10-500 kg\n- Body Fat is 0-100% \n- Gender is selected");
        alert.showAndWait();
    }
}

package com.fss.everythingapp.studentservices;

import java.io.IOException;

import com.fss.everythingapp.app.App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AppointmentController {
    static String name;
    static int grade;
    @FXML
    BorderPane rootContainer;
    private TextField nameField;
    @FXML
    private ChoiceBox<Integer> gradeBox;
    StudentServices studentServices = new StudentServices();

    public void init() {
        nameField = new TextField();
        ObservableList<Integer> grades = FXCollections.observableArrayList(9, 10, 11, 12);
        gradeBox.setItems(grades);
    }

    @FXML
    private void enterBtnPressed(ActionEvent actionEvent) throws IOException {
        // aquire name and grade
        name = getName();
        grade = getGrade();

        studentServices.programRunner();

        // load to the next layout
        try {
            FXMLLoader timeslotsLayout = new FXMLLoader(getClass().getResource("/com/fss/everythingapp/studentservicesappointment/fxml/TimeslotsLayout.fxml"));
            TimeslotsController timeslotsController = new TimeslotsController();
            timeslotsLayout.setController(timeslotsController);
            Parent show = timeslotsLayout.load();
            timeslotsController.initialize();
            rootContainer.getScene().setRoot(show);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private String getName() {
        name = nameField.getText();
        return name;
    }

    @FXML
    private int getGrade() {
        grade = gradeBox.getValue();
        return grade;
    }

    @FXML
    private void onExitBtnPressed() {
        App.backToMainMenu();
    }
}
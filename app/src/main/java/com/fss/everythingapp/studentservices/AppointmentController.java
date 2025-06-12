package com.fss.everythingapp.studentservices;

import java.io.IOException;

import com.fss.everythingapp.app.App;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AppointmentController {
    @FXML
    static String name;
    static int grade;
    private BorderPane rootcontainer;
    private TextField nameField;
    private ChoiceBox<Integer> gradeBox = new ChoiceBox<>();
    StudentServices studentServices = new StudentServices();

    @FXML
    public void init() {
        nameField = new TextField();
        gradeBox.getItems().addAll(9, 10, 11, 12);
    }

    @FXML
    private void enterBtnPressed() throws IOException {
        // aquire name and grade
        name = getName();
        grade = getGrade();

        studentServices.programRunner();

        // load to the next layout
        FXMLLoader timeslotsLayout = new FXMLLoader(
                getClass().getResource("/com/fss/everthingapp/studentservicesappointment/fxml/TimeslotsLayout.fxml"));
        TimeslotsController timeslotsController = new TimeslotsController();
        timeslotsLayout.setController(timeslotsController);
        Parent show = timeslotsLayout.load();
        rootcontainer.getScene().setRoot(show);
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
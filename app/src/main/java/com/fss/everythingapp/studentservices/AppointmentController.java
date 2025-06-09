package com.fss.everythingapp.studentservices;

import java.io.IOException;
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

    @FXML
    private void enterBtnPressed() throws IOException {
        // aquire name and grade
        name = getName();
        grade = getGrade();

        StudentServices studentServices = new StudentServices();
        studentServices.programRunner();


        // load to the next layout
        Parent datesLayout = FXMLLoader.load(getClass().getResource("../studentservicesappointment/fxml/DatesLayout.fxml"));
        rootcontainer.getScene().setRoot(datesLayout);
    }

    @FXML
    private String getName() {
        TextField nameField = new TextField();
        name = nameField.getText();
        return name;
    }

    @FXML
    private int getGrade() {
        ChoiceBox<Integer> gradeBox = new ChoiceBox<>();
        gradeBox.getItems().addAll(9, 10, 11, 12);
        grade = gradeBox.getValue();
        return grade;
    }

    @FXML
    private void onExitBtnPressed() throws IOException {
        Parent returnLayout = FXMLLoader.load(getClass().getResource("../app/MainMenuLayout.fxml"));
        rootcontainer.getScene().setRoot(returnLayout);
    }

}
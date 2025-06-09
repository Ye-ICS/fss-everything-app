package com.fss.everythingapp.studentservices;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        //TODO get name from textfield
        return name;
    }

    @FXML
    private int getGrade() {
        //TODO get grade from choicebox
        return grade;
    }

    @FXML
    private void onExitBtnPressed() throws IOException {
        Parent returnLayout = FXMLLoader.load(getClass().getResource("../app/MainMenuLayout.fxml"));
        rootcontainer.getScene().setRoot(returnLayout);
    }

}
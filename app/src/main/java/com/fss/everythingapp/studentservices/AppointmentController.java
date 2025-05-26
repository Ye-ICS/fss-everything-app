package com.fss.everythingapp.studentservices;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class AppointmentController {
    static String time;
    static String name;
    static int grade;
    static int date;
    private void enterBtn(){

    }
}

    private BorderPane rootcontainer;

    @FXML
    private void enterBtnPressed() throws IOException {
        // aquire name and grade


        // load to the next layout
        Parent timeslotsLayout = FXMLLoader.load(getClass().getResource("Timeslots.fxml"));
        rootcontainer.getScene().setRoot(timeslotsLayout);
    }

}
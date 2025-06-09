package com.fss.everythingapp.studentservices;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class DateController {
    private BorderPane rootcontainer;
    
    @FXML
    private void blank() throws IOException{
        Parent timeslotsLayout = FXMLLoader.load(getClass().getResource("../studentservicesappointment/fxml/Timeslots.fxml"));
        rootcontainer.getScene().setRoot(timeslotsLayout);
    }
}

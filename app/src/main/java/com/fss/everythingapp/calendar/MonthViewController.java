package com.fss.everythingapp.calendar;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MonthViewController {

    @FXML
    private Button exitButton;

    @FXML
    private Button createDateButton;

    @FXML
    private VBox rootContainer;

    @FXML
    void createDate(ActionEvent event) throws IOException {
        Parent createDate = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/CreateDate.fxml"));
        rootContainer.getScene().setRoot(createDate);
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        Parent mainMenu = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/app/fxml/Example.fxml"));

        rootContainer.getScene().setRoot(mainMenu);
    }

    @FXML
    void setDayView(ActionEvent event) throws IOException {
        Parent dayView = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/DayView.fxml"));
        rootContainer.getScene().setRoot(dayView);
    }

    @FXML
    void setWeekView(ActionEvent event) throws IOException {
        Parent weekView = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/WeekView.fxml"));
        rootContainer.getScene().setRoot(weekView);
    }

    void showTasks(){
        
    }
}
package com.fss.everythingapp.calendar;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fss.everythingapp.app.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;

public class DayViewController {

    @FXML
    private Button exitButton;

    @FXML
    private Button loadButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button createDateButton;

    @FXML
    private VBox rootContainer;

    public void initialize() {
        loadButton.fire();
    }

    @FXML
    void createDate(ActionEvent event) throws IOException {
        Parent createDate = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/CreateDate.fxml"));
        rootContainer.getScene().setRoot(createDate);
    }

    @FXML
    private void back(ActionEvent actionEvent) throws IOException {
        App.backToMainMenu();
    }

    @FXML
    void loadDates(ActionEvent event) {
        // LocalDate paramDate;
        // try {
        //     paramDate = LocalDate.parse(datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     paramDate = LocalDate.now();
        // }
        // Date dateLoader = new Date('D', paramDate);

        // for (int i = 0; i < dateLoader.getDateList().size(); i++) {
        //     Date loadedDate = dateLoader.getDateList().get(i);

        //     // To Do:
        //     // Populate GridPane with dates recieved

        // }
        
        // ^ uncomment this when method is complete
    }

    @FXML
    void setMonthView(ActionEvent event) throws IOException {
        Parent monthView = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/MonthView.fxml"));
        rootContainer.getScene().setRoot(monthView);
    }

    @FXML
    void setWeekView(ActionEvent event) throws IOException {
        Parent weekView = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/WeekView.fxml"));
        rootContainer.getScene().setRoot(weekView);
    }
}
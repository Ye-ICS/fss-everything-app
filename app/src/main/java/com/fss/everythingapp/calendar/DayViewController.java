package com.fss.everythingapp.calendar;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class DayViewController {

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

    void showDates(ActionEvent event) throws IOException, ParseException {

        // String taskName = taskNameField.getText();

        // String dueDateString =
        // dueDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // dueDateString = dueDateString.concat("T" + dueHourField.getText() + ":" +
        // dueMinField.getText());
        // LocalDateTime dueDate = LocalDateTime.parse(dueDateString);
        // System.out.println(dueDate);

        // TaskManager taskMan = new TaskManager(taskName, dueDate);

        // Parent openCalendar = (Parent) FXMLLoader
        // .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/MonthView.fxml"));
        // rootContainer.getScene().setRoot(openCalendar);
    }
}
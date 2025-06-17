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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreateTaskController {

    @FXML
    private VBox rootContainer;

    @FXML
    private Button createTaskButton;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private TextField dueHourField;

    @FXML
    private TextField dueMinField;

    @FXML
    private TextField taskNameField;

    @FXML
    void createTask(ActionEvent event) throws IOException, ParseException {
        String taskName = taskNameField.getText();

        String dueDateString = dueDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (dueHourField.getText().length() != 2 || dueMinField.getText().length() != 2) {
            dueDateString = dueDateString.concat("T");

            if (dueHourField.getText().length() < 2) {
                dueDateString = dueDateString.concat("0" + dueHourField.getText());
            } else if (dueHourField.getText().length() > 2) {
                dueDateString = dueDateString.concat(dueHourField.getText().substring(0, 2));
            } else {
                dueDateString = dueDateString.concat(dueHourField.getText());
            }

            dueDateString = dueDateString.concat(":");

            if (dueMinField.getText().length() < 2) {
                dueDateString = dueDateString.concat(dueMinField.getText() + "0");
            } else if (dueMinField.getText().length() > 2) {
                dueDateString = dueDateString.concat(dueMinField.getText().substring(0, 2));
            } else {
                dueDateString = dueDateString.concat(dueMinField.getText());
            }
        } else {
            dueDateString = dueDateString.concat("T" + dueHourField.getText() + ":" + dueMinField.getText());
        }

        LocalDateTime dueDate = LocalDateTime.parse(dueDateString);

        @SuppressWarnings("unused")
        Task saveTask = new Task(taskName, dueDate);

        Parent openCalendar = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/MonthView.fxml"));
        rootContainer.getScene().setRoot(openCalendar);
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Parent home = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/CreateDate.fxml"));
        rootContainer.getScene().setRoot(home);
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Parent home = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/MonthView.fxml"));
        rootContainer.getScene().setRoot(home);
    }

    @FXML
    void createTaskLabel(ActionEvent event) throws IOException {
        Label taskLabel = new Label();
        taskLabel.setText(taskNameField.getText());
    }
}
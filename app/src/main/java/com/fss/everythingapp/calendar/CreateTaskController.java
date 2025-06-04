package com.fss.everythingapp.calendar;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
    void createTask(ActionEvent event) throws IOException {
        String taskName = taskNameField.getText();

        LocalDate localDueDate = dueDatePicker.getValue();
        String dueDate = localDueDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        dueDate = dueDate.concat("/" + dueHourField.getText() + dueMinField.getText());

        TaskManager taskMan = new TaskManager(taskName, dueDate);

        Parent openCalendar = (Parent) FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/MonthView.fxml"));
        rootContainer.getScene().setRoot(openCalendar);
    }

}
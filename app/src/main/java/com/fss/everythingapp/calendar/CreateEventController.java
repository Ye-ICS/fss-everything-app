package com.fss.everythingapp.calendar;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreateEventController {

    private VBox rootContainer;

    @FXML
    private Button createEventButton;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField endHourField;

    @FXML
    private TextField endMinField;

    @FXML
    private TextField eventNameField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextField startHourField;

    @FXML
    private TextField startMinField;

    @FXML
    void createEvent(ActionEvent event) throws FileNotFoundException {
        String eventTitle = eventNameField.getText();

        LocalDate localStartDate = startDatePicker.getValue();
        String startDate = localStartDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        startDate.concat("/" + startHourField.getText() + startMinField.getText());

        LocalDate localEndDate = startDatePicker.getValue();
        String endDate = localEndDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        endDate.concat("/" + endHourField.getText() + endMinField.getText());
    }
}
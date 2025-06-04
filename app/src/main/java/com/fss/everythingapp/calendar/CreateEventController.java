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

public class CreateEventController {

    @FXML
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
    void createEvent(ActionEvent event) throws IOException {
        String eventName = eventNameField.getText();

        LocalDate localStartDate = startDatePicker.getValue();
        String startDate = localStartDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        startDate = startDate.concat("/" + startHourField.getText() + ":" + startMinField.getText());

        LocalDate localEndDate = startDatePicker.getValue();
        String endDate = localEndDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        endDate = endDate.concat("/" + endHourField.getText() + ":" + endMinField.getText());

        EventManager eventMan = new EventManager(eventName, startDate, endDate);

        Parent openCalendar = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/MonthView.fxml"));
        rootContainer.getScene().setRoot(openCalendar);
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Parent home = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/MonthView.fxml"));
        rootContainer.getScene().setRoot(home);
    }
}
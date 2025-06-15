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
    void createEvent(ActionEvent event) throws IOException, ParseException {
        String eventName = eventNameField.getText();

        String startDateString = startDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        startDateString = startDateString.concat("T" + startHourField.getText() + ":" + startMinField.getText());
        LocalDateTime startDate = LocalDateTime.parse(startDateString);

        String endDateString = endDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        endDateString = endDateString.concat("T" + endHourField.getText() + ":" + endMinField.getText());
        LocalDateTime endDate = LocalDateTime.parse(endDateString);

        @SuppressWarnings("unused")
        EventManager eventMan = new EventManager(eventName, startDate, endDate);

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
}
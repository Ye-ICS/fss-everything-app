package com.fss.everythingapp.calendar;

import java.io.IOException;
import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class WeekViewController {

    @FXML
    private Button createDateButton;

    @FXML
    private ScrollPane dateListPane;

    @FXML
    private Button exitButton;

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
    void loadDates(ActionEvent event) {
        System.out.println("started");
        DateManager dateLoader = new DateManager();
        System.out.println("loaded");

        Circle icon = new Circle();
        icon.setRadius(10);

        for (int i = 0; i < dateLoader.getDateList().size(); i++) {

            DateManager loadedDate = dateLoader.getDateList().get(i);

            HBox dateBox = new HBox();
            dateListPane.setContent(dateBox);

            Label dateNameLabel = new Label();
            dateNameLabel.setPrefWidth(170);
            dateNameLabel.setText(loadedDate.getDateName());

            if (loadedDate.getDateType() == 'T') {
                icon.setFill(Color.RED);

                LocalDateTime dueDate = loadedDate.getDueDate();
                Label dueDateLabel = new Label();
                dueDateLabel.setText(dueDate.toString());
                dateBox.getChildren().addAll(dateNameLabel, dueDateLabel);
            } else {
                icon.setFill(Color.BLUE);

                LocalDateTime startDate = loadedDate.getStartDate();
                Label startDateLabel = new Label();
                startDateLabel.setText(startDate.toString());

                dateBox.getChildren().addAll(dateNameLabel, startDateLabel);
            }
        }
        System.out.println("done");
    }

    @FXML
    void setDayView(ActionEvent event) throws IOException {
        Parent dayView = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/DayView.fxml"));
        rootContainer.getScene().setRoot(dayView);
    }

    @FXML
    void setMonthView(ActionEvent event) throws IOException {
        Parent monthView = (Parent) FXMLLoader
                .load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/MonthView.fxml"));
        rootContainer.getScene().setRoot(monthView);
    }
}
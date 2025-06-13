package com.fss.everythingapp.calendar;

import java.io.IOException;
import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MonthViewController {

    @FXML
    private VBox rootContainer;

    @FXML
    private ScrollPane dateListPane;

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
        DateManager dateLoader = new DateManager();

        Circle icon = new Circle();
        icon.setRadius(10);
        icon.setStrokeWidth(2.0);

        for (int i = 0; i < dateLoader.getDateList().size(); i++) {

            DateManager loadedDate = dateLoader.getDateList().get(i);

            HBox dateBox = new HBox();
            dateListPane.setContent(dateBox);
            dateBox.setAlignment(Pos.CENTER_LEFT);

            Label dateNameLabel = new Label();
            dateNameLabel.setPrefWidth(175);
            dateNameLabel.setText(loadedDate.getDateName());

            if (loadedDate.getDateType() == 'T') {
                icon.setFill(Color.RED);

                LocalDateTime dueDate = loadedDate.getDueDate();
                Label dueDateLabel = new Label();
                dueDateLabel.setText(dueDate.toString());

                dateBox.getChildren().addAll(icon, dateNameLabel, dueDateLabel);
                HBox.setMargin(dateNameLabel, new Insets(0, 10, 0, 10));
            } else {
                icon.setFill(Color.CORNFLOWERBLUE);

                LocalDateTime startDate = loadedDate.getStartDate();
                Label startDateLabel = new Label();
                startDateLabel.setText(startDate.toString());

                dateBox.getChildren().addAll(icon, dateNameLabel, startDateLabel);
                HBox.setMargin(dateNameLabel, new Insets(0, 10, 0, 10));
            }
        }
        HBox.setMargin(icon, new Insets(2, 5, 0, 5));
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
}
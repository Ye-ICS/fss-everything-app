package com.fss.everythingapp.calendar;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fss.everythingapp.app.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class WeekViewController {

    @FXML
    private Button loadButton;

    @FXML
    private ScrollPane dateListPane;

    @FXML
    private DatePicker datePicker;

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

    private void fillDatePane() {
        DateManager dateLoader = new DateManager(dateListPane);

        VBox dateListBox = new VBox();
        dateListPane.setContent(dateListBox);

        for (int i = 0; i < dateLoader.getDateList().size(); i++) {

            HBox dateBox = new HBox();
            dateBox.setAlignment(Pos.CENTER_LEFT);
            dateBox.setPrefWidth(335);
            dateListBox.getChildren().addAll(dateBox);
            VBox.setMargin(dateBox, new Insets(1, 0, 1, 0));

            Circle icon = new Circle();
            icon.setRadius(10);
            icon.setStrokeWidth(2.0);
            HBox.setMargin(icon, new Insets(1, 5, 1, 5));

            DateManager loadedDate = dateLoader.getDateList().get(i);

            Label dateNameLabel = new Label();
            dateNameLabel.setPrefWidth(175);
            dateNameLabel.setText(loadedDate.getDateName());
            HBox.setMargin(dateNameLabel, new Insets(0, 10, 0, 10));

            if (loadedDate.getDateType() == 'T') {
                icon.setFill(Color.RED);

                LocalDateTime dueDate = loadedDate.getDueDate();
                Label dueDateLabel = new Label();
                dueDateLabel.setText(dueDate.toString());

                dateBox.getChildren().addAll(icon, dateNameLabel, dueDateLabel);
            } else {
                icon.setFill(Color.CORNFLOWERBLUE);

                LocalDateTime startDate = loadedDate.getStartDate();
                Label startDateLabel = new Label();
                startDateLabel.setText(startDate.toString());

                dateBox.getChildren().addAll(icon, dateNameLabel, startDateLabel);
            }

            if (i % 2 != 0) {
                dateBox.setBackground(
                        new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

    private void fillCalendar() {
        LocalDate paramDate;
        try {
            paramDate = LocalDate.parse(datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } catch (Exception e) {
            e.printStackTrace();
            paramDate = LocalDate.now();
        }
        Date dateLoader = new Date('W', paramDate);

        for (int i = 0; i < dateLoader.getDateList().size(); i++) {
            Date loadedDate = dateLoader.getDateList().get(i);

            // To Do:
            // Populate GridPane with dates recieved

        }
    }

    @FXML
    void loadDates(ActionEvent event) {
        fillDatePane();
        // fillCalendar();
        // ^ uncomment when method is complete
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
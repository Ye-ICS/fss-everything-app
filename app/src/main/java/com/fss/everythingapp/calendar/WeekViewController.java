package com.fss.everythingapp.calendar;

import java.io.IOException;
import java.time.DayOfWeek;
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
import javafx.scene.layout.GridPane;
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

    @FXML
    private GridPane CalendarGridPane;

    // public void initialize() {
    //     loadButton.fire();
    // }

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

            Date loadedDate = dateLoader.getDateList().get(i);

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
        DateManager dateLoader = new DateManager('W', paramDate);

        for (int i = 0; i < dateLoader.getDateList().size(); i++) {
            Date loadedDate = dateLoader.getDateList().get(i);
            Label label = new Label();

            label.setText(loadedDate.dateName);
            CalendarGridPane.getChildren().add(label);
            // To Do:
            // Populate GridPane with dates recieved
        }
    }

    @FXML
    void loadDateLabels() {
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current Date: " + currentDate);

        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        int dayOfWeekNum = dayOfWeek.getValue(); // monday is 1, sunday is 7

        // dont know if this works
        String dateString = currentDate + "";

        int date = Integer.parseInt(dateString);

        // could use switch case statements
        // what if its first of the month
        // have to add dateVBox to gridpane
        Label[] dateLabel = new Label[7];
        VBox[] dateVBox = new VBox[7];

        if (dayOfWeekNum == 1) {
            // new Label for date and add it to gridpane
            dateLabel[0].setText(date + "");
            dateLabel[1].setText(date + 1 + "");
            dateLabel[2].setText(date + 2 + "");
            dateLabel[3].setText(date + 3 + "");
            dateLabel[4].setText(date + 4 + "");
            dateLabel[5].setText(date + 5 + "");
            dateLabel[6].setText(date + 6 + "");
            for (int i = 0; i < 7; i++) {
                dateVBox[i].getChildren().add(dateLabel[i]);
            }
        } else if (dayOfWeekNum == 2) {
            dateLabel[0].setText(date - 1 + "");
            dateLabel[1].setText(date + "");
            dateLabel[2].setText(date + 1 + "");
            dateLabel[3].setText(date + 2 + "");
            dateLabel[4].setText(date + 3 + "");
            dateLabel[5].setText(date + 4 + "");
            dateLabel[6].setText(date + 5 + "");
            for (int i = 0; i < 7; i++) {
                dateVBox[i].getChildren().add(dateLabel[i]);
            }
        } else if (dayOfWeekNum == 3) {
            dateLabel[0].setText(date - 2 + "");
            dateLabel[1].setText(date - 1 + "");
            dateLabel[2].setText(date + "");
            dateLabel[3].setText(date + 1 + "");
            dateLabel[4].setText(date + 2 + "");
            dateLabel[5].setText(date + 3 + "");
            dateLabel[6].setText(date + 4 + "");
            for (int i = 0; i < 7; i++) {
                dateVBox[i].getChildren().add(dateLabel[i]);
            }
        } else if (dayOfWeekNum == 4) {
            dateLabel[0].setText(date - 3 + "");
            dateLabel[1].setText(date - 2 + "");
            dateLabel[2].setText(date - 1 + "");
            dateLabel[3].setText(date + "");
            dateLabel[4].setText(date + 1 + "");
            dateLabel[5].setText(date + 2 + "");
            dateLabel[6].setText(date + 3 + "");
            for (int i = 0; i < 7; i++) {
                dateVBox[i].getChildren().add(dateLabel[i]);
            }
        } else if (dayOfWeekNum == 5) {
            dateLabel[0].setText(date - 4 + "");
            dateLabel[1].setText(date - 3 + "");
            dateLabel[2].setText(date - 2 + "");
            dateLabel[3].setText(date - 1 + "");
            dateLabel[4].setText(date + "");
            dateLabel[5].setText(date + 1 + "");
            dateLabel[6].setText(date + 2 + "");
            for (int i = 0; i < 7; i++) {
                dateVBox[i].getChildren().add(dateLabel[i]);
            }
        } else if (dayOfWeekNum == 6) {
            dateLabel[0].setText(date - 5 + "");
            dateLabel[1].setText(date - 4 + "");
            dateLabel[2].setText(date - 3 + "");
            dateLabel[3].setText(date - 2 + "");
            dateLabel[4].setText(date - 1 + "");
            dateLabel[5].setText(date + "");
            dateLabel[6].setText(date + 1 + "");
            for (int i = 0; i < 7; i++) {
                dateVBox[i].getChildren().add(dateLabel[i]);
            }
        } else {
            dateLabel[0].setText(date - 6 + "");
            dateLabel[1].setText(date - 5 + "");
            dateLabel[2].setText(date - 4 + "");
            dateLabel[3].setText(date - 3 + "");
            dateLabel[4].setText(date - 2 + "");
            dateLabel[5].setText(date - 1 + "");
            dateLabel[6].setText(date + "");
            for (int i = 0; i < 7; i++) {
                dateVBox[i].getChildren().add(dateLabel[i]);
            }
        }
        CalendarGridPane.getChildren().addAll(dateVBox);
    }

    @FXML
    void loadDates(ActionEvent event) {
        fillDatePane();
        fillCalendar();
        loadDateLabels();
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
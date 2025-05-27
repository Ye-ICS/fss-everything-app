package com.fss.everythingapp.calendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Calendar extends Application {
    ArrayList<DateManager> dateList = new ArrayList<DateManager>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MonthView.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("FSS Calendar");
        stage.setScene(scene);
        stage.show();
    }

    public ArrayList<DateManager> getDateList() {
        return dateList;
    }

    public void createDate() {
    }

    public void editDate() {
    }
}
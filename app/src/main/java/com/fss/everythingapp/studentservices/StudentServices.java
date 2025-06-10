package com.fss.everythingapp.studentservices;

import java.applet.Applet;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

class StudentServices {
    ArrayList<Counsellor> counsellors = new ArrayList<>(4);
    SSCalendar calendar;

    private ArrayList<Counsellor> fillCounsellors() {
        for (int i = 0; i < counsellors.size(); i++) {
            Counsellor counsellor = new Counsellor(i);
            counsellors.add(counsellor);
        }
        return counsellors;
    }

    // placeholder method for data being used by the app
    void Data() {
        String name = AppointmentController.name;
    }

    public void programRunner() {
        counsellors = fillCounsellors();
        int id = AppointmentController.grade - 9;
        calendar = counsellors.get(id).getCalendar();
        TimeslotsController.TimeslotsController(calendar.getAvailableDates());

        // happens later in the program
        counsellors.get(id).updateCalendar(calendar);
    }
}

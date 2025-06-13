package com.fss.everythingapp.app;

import java.io.IOException;

import com.fss.everythingapp.studentServices.AppointmentController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainMenuController {

    @FXML
    BorderPane rootContainer;

    @FXML
    private void openStudentServices(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader appointmentLayout = new FXMLLoader(getClass().getResource("/com/fss/everythingapp/studentservicesappointment/fxml/AppointmentLayout.fxml"));
            AppointmentController appointmentController = new AppointmentController();
            appointmentLayout.setController(appointmentController);
            Parent show = appointmentLayout.load();
            rootContainer.getScene().setRoot(show);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openBuses(ActionEvent actionEvent) throws IOException {
        try {
            Parent mainListLayout = (Parent) FXMLLoader.load(getClass().getResource("../businfo/List.fxml"));
            rootContainer.getScene().setRoot(mainListLayout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openSimulations(ActionEvent actionEvent) throws IOException {
            Parent simulationsLayout = (Parent) FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/simulations/SimulationsMenu.fxml"));
            rootContainer.getScene().setRoot(simulationsLayout);

    }

    @FXML
    private void openCalendar(ActionEvent actionEvent) throws IOException {
        Parent monthView = (Parent) FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/MonthView.fxml"));
        rootContainer.getScene().setRoot(monthView);
    }
}
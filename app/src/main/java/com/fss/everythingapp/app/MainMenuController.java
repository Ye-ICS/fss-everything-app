package com.fss.everythingapp.app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainMenuController {



    @FXML
    BorderPane rootContainer;


    @FXML
    private void openStudentServices(ActionEvent actionEvent) {

    }

    @FXML
    private void openFitnessApp (ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/Fitness/fxml/NotSignedin.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML: " + e.getMessage());
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
    private void openCalendar(ActionEvent actionEvent) throws IOException {
        Parent monthView = (Parent) FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/calendar/fxml/MonthView.fxml"));
        rootContainer.getScene().setRoot(monthView);
    }
}

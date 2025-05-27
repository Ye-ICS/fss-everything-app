package com.fss.everythingapp;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MMnsiController {

    @FXML
    private Button signInButton;

    @FXML
    void signIn(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fss/everythingapp/app/fxml/MainMenu-Signedin.fxml"));
        Parent signInScreen = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(signInScreen));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
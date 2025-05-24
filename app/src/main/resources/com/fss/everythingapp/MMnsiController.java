package com.fss.everythingapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MMnsiController {

    @FXML
    private Button signInButton;

    @FXML
    void signIn(ActionEvent event) {
        System.out.println("Sign In button clicked");
    }

}
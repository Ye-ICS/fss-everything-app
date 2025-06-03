package com.fss.everythingapp.businfo;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class ListController {

    @FXML
    BorderPane rootContainer;

    @FXML
    private void quit(ActionEvent actionEvent) throws IOException, InterruptedException {
        OdkApiService.get();
    }
}

package com.fss.everythingapp.businfo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class MainListController {

    @FXML
    BorderPane rootContainer;

    @FXML
    private void quit(ActionEvent actionEvent) throws Exception {
        OdkApiService.get();
        System.out.println("hi");
        new GtfsReaderExampleMain();
    }
}

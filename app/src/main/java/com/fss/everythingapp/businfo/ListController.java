package com.fss.everythingapp.businfo;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ListController {

    @FXML
    BorderPane rootContainer;

    @FXML
    VBox listVBox;

    @FXML
    private void quit(ActionEvent actionEvent) throws IOException, InterruptedException {
        OdkApiService.get();
    }

    public void initialize() {
        String[] routeIds = OdkInfoUtils.getRouteIds();
        for (int i = 0; i < routeIds.length; i++) {
            String routeId = routeIds[i];
            try {
                String routeName = OdkInfoUtils.getRouteName(routeId);
                listVBox.getChildren().add(new Label(routeName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

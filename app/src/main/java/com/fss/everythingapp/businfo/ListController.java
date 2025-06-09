package com.fss.everythingapp.businfo;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Sphere;

public class ListController {

    @FXML
    BorderPane rootContainer;

    @FXML
    VBox listVBox;

    @FXML
    private void quit(ActionEvent actionEvent) {
        try {
            OdkApiService.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        System.out.println("ListController initializing...");
        OdkInfoUtils busInfo = new OdkInfoUtils();
        ArrayList<String> routeIds = busInfo.getRouteIds();
        System.out.println(routeIds.size() + " routes found.");
        for (String routeId : routeIds) {
            ToolBar toolBar = new ToolBar();
            Label label = new Label(busInfo.getRouteById(routeId).getLongName());
            toolBar.getItems().add(label);
            
            listVBox.getChildren().add(label);
            busInfo.getColour(routeId);
        }
    }
}

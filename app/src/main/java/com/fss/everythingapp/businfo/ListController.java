package com.fss.everythingapp.businfo;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ListController {

    @FXML
    BorderPane rootContainer;

    @FXML
    VBox listVBox;

    ArrayList<ToolBar> toolBars = new ArrayList<>();

    @FXML
    private void quit(ActionEvent actionEvent) {
        try {

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
            toolBars.add(toolBar);
            Button button = new Button(busInfo.getRouteById(routeId).getLongName() + " -- "
                    + busInfo.getRouteById(routeId).getId().getId());
            toolBar.getItems().add(button);
            listVBox.getChildren().add(toolBar);
            busInfo.getColour(routeId);
        }
    }
}

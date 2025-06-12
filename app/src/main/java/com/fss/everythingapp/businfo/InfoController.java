package com.fss.everythingapp.businfo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class InfoController {

    @FXML
    StackPane rootContainer;

    @FXML
    BorderPane borderPane;

    @FXML
    ListView<Button> listView;

    @FXML
    ToolBar busInfo1;

    @FXML
    ToolBar busInfo2;

    @FXML
    ProgressIndicator progressIndicator;

    ListController listController;

    String routeId;

    @FXML
    private void quit(ActionEvent actionEvent) {
        try {
            FXMLLoader mainListLoader = new FXMLLoader(getClass().getResource("List.fxml"));
            mainListLoader.setController(listController);
            Parent listLayout = mainListLoader.load();
            rootContainer.getScene().setRoot(listLayout);
            listController.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {

        progressIndicator.setVisible(true);

        Thread initThread = new Thread(() -> {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        initThread.start();

    }
}

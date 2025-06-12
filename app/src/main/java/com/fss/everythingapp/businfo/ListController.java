package com.fss.everythingapp.businfo;

import java.util.ArrayList;

import com.fss.everythingapp.app.App;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class ListController {

    @FXML
    StackPane rootContainer;

    @FXML
    BorderPane borderPane;

    @FXML
    ProgressIndicator progressIndicator;

    @FXML
    TextField searchBox;

    @FXML
    ListView<Button> listView;

    private ArrayList<Button> buttons;

    private ArrayList<String> routeIds;

    private OdkInfoUtils busInfo;

    @FXML
    private void quit(ActionEvent actionEvent) {
        App.backToMainMenu();
    }

    @FXML
    private void updateSearch(ActionEvent actionEvent) {
        listView.setVisible(false);
        progressIndicator.setProgress(-1);
        progressIndicator.setVisible(true);

        Thread search = new Thread(() -> {
            String searchText = searchBox.getText().toLowerCase();
            ArrayList<Button> searchResults = new ArrayList<>();
            for (Button button : buttons) {
                String buttonText = button.getText().toLowerCase();
                if (buttonText.contains(searchText)) {
                    searchResults.add(button);
                }
            }

            Platform.runLater(() -> {
                listView.getItems().clear();
                listView.getItems().addAll(searchResults);
                progressIndicator.setVisible(false);
                listView.setVisible(true);
            });
        });

        search.start();
    }

    @FXML
    private void debug(ActionEvent actionEvent){

    } 

    public void launchInfo(String routeId) {
        try {
            FXMLLoader infoLoader = new FXMLLoader(getClass().getResource("Info.fxml"));
            InfoController infoController = new InfoController();
            infoController.listController = this;
            infoController.routeId = routeId;
            infoController.busInfo = busInfo;
            infoLoader.setController(infoController);
            Parent infoLayout = infoLoader.load();
            rootContainer.getScene().setRoot(infoLayout);
            infoController.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        Thread initThread = new Thread(() -> {
            try {
                if (routeIds == null) {
                    busInfo = new OdkInfoUtils();
                    routeIds = busInfo.getRouteIds();
                    buttons = new ArrayList<>();
                    for (String routeId : routeIds) {
                        Button button = new Button(busInfo.getRouteById(routeId).getId().getId() + " - "
                                + busInfo.getRouteById(routeId).getLongName());
                        button.setOnAction(event -> {
                            try {
                                launchInfo(routeId);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        buttons.add(button);
                    }
                }
                Platform.runLater(() -> updateSearch(null));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        initThread.start();
    }
}

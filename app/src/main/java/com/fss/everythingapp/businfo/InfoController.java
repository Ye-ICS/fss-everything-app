package com.fss.everythingapp.businfo;

import java.util.ArrayList;

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

public class InfoController {

    @FXML
    StackPane rootContainer;

    @FXML
    BorderPane borderPane;

    @FXML
    ListView<Button> listView;

    @FXML
    ProgressIndicator progressIndicator;

    @FXML
    TextField searchBox;

    ListController listController;

    String routeId;

    ArrayList<Button> buttons;

    OdkInfoUtils busInfo;

    ArrayList<RealStop> stops;

    GtfsReaderExampleMain reader;

    Thread updateThread;

    @FXML
    private void quit(ActionEvent actionEvent) {
        try {
            FXMLLoader mainListLoader = new FXMLLoader(getClass().getResource("List.fxml"));
            mainListLoader.setController(listController);
            Parent listLayout = mainListLoader.load();
            rootContainer.getScene().setRoot(listLayout);
            listController.init();
            updateThread.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateSearch(ActionEvent actionEvent) {
        listView.setVisible(false);
        progressIndicator.setProgress(-1);
        progressIndicator.setVisible(true);

        updateSearchBackground();
    }

    private void updateSearchBackground() {
        Platform.runLater(() -> progressIndicator.setVisible(true));

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

    public void startUpdates() {
        updateThread = new Thread(() -> {
            try {
                while (true) {
                    stops = reader.getUpdate(routeId);
                    buttons.clear();
                    for (RealStop stop : stops) {
                        Button button = new Button();
                        String text = busInfo.getStopById(stop.id).getName() + " - Arrival: "
                                + timeToString(busInfo.getStopTimeById(stop.id) + stop.delay);
                        if (stop.delay != 0) {
                            button.setText("! " + text);
                        } else {
                            button.setText(text);
                        }
                        buttons.add(button);
                    }
                    updateSearchBackground();
                    Thread.sleep(60000);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        updateThread.start();
    }

    public void init() {
        listView.setVisible(false);
        progressIndicator.setProgress(-1);
        progressIndicator.setVisible(true);

        Thread initThread = new Thread(() -> {
            try {
                reader = new GtfsReaderExampleMain();
                stops = reader.getUpdate(routeId);
                buttons = new ArrayList<Button>();
                startUpdates();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        initThread.start();

    }

    public static String timeToString(int time) {
        int hours = time / 3600;
        int minutes = (time % 3600) / 60;
        return String.format("%02d:%02d", hours, minutes);
    }

}

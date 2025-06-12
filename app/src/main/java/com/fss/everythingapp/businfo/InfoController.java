package com.fss.everythingapp.businfo;

import java.util.ArrayList;

import java.text.SimpleDateFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
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

    ListController listController;

    String routeId;

    ArrayList<Button> buttons;

    OdkInfoUtils busInfo;

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
                GtfsReaderExampleMain reader = new GtfsReaderExampleMain();
                ArrayList<RealStop> stops = reader.getUpdate(routeId);
                buttons = new ArrayList<Button>();
                for(RealStop stop : stops){
                    Button button = new Button(busInfo.getStopById(stop.id).getName());
                    buttons.add(button);
                    listView.getItems().add(button);
                }
                progressIndicator.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        initThread.start();

    }
    public static String unixToString(long unix){
    SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
    String date = sdf.format(unix);
        return date;
    }

}

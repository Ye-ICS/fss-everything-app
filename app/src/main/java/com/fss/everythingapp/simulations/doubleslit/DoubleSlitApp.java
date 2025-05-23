package com.fss.everythingapp.simulations.doubleslit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DoubleSlitApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DoubleSlitSimulation.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root, 800, 800);
        
        primaryStage.setTitle("Double-Slit Interference Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
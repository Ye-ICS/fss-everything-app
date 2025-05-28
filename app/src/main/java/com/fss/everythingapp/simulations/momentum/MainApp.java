package com.fss.everythingapp.simulations.momentum;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Simulation simulation = new Simulation(600,600);

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Left: Simulation Pane
        Pane simPane = new Pane();
        simPane.setStyle("-fx-background-color: lightgray;");
        simPane.setMinSize(600, 600);
        root.setCenter(simPane);

        // Right: Controls (placeholder for now)
        ControlPanel controls = new ControlPanel(simulation);
        root.setRight(controls);

        // Create two pucks
        Puck p1 = new Puck(200, 300, 10);
        Puck p2 = new Puck(400, 300, 50);

        simPane.getChildren().addAll(p1, p2);
        simulation.addPuck(p1);
        simulation.addPuck(p2);
        simulation.setupPuckDrag(p1, p1);
        simulation.setupPuckDrag(p2, p2);

        p1.setVelocity(new Vector2D(230, 100)); // move right at 50 units/second //For testing purposes
        p2.setVelocity(new Vector2D(-370, 200)); // move left at 30 units/second // For testing purposes



        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("2D Momentum Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

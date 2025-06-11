package com.fss.everythingapp.simulations.momentum;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Simulation simulation = new Simulation(600,600);

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Left: Simulation Pane
        Pane simPane = new Pane();
        // Update simulation width and height dynamically
        simPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            simulation.setWidth(newVal.doubleValue());
        });
        simPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            simulation.setHeight(newVal.doubleValue());
        });

        simPane.setStyle("-fx-background-color: aquamarine;");
        simPane.setMinSize(600, 600);
        simPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        root.setCenter(simPane);
        // Create two pucks
        Puck p1 = new Puck(200, 300, 10);
        Puck p2 = new Puck(400, 300, 50);

        // Right: Controls
        ControlPanel controls = new ControlPanel(simulation,p1,p2);
        root.setRight(controls);

        simPane.getChildren().addAll(p1, p2);
        simulation.addPuck(p1);
        simulation.addPuck(p2);
        simulation.setupPuckDrag(p1, p1);
        simulation.setupPuckDrag(p2, p2);

        // for testing purposes
        p1.setVelocity(new Vector2D(230, 100));
        p2.setVelocity(new Vector2D(-370, 200));

        Scene scene = new Scene(root, 1200, 600);
        primaryStage.setTitle("2D Momentum Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

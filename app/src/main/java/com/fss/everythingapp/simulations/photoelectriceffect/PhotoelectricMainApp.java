package com.fss.everythingapp.simulations.photoelectriceffect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.Parent;

/**
 * Main application class for the photoelectric effect simulation.
 * Sets up the UI layout with simulation pane on the left and controls on the right.
 */
public class PhotoelectricMainApp extends Application {

    /**
     * Returns the simulation UI as a Parent node for embedding.
     * @return Parent node containing the simulation UI.
     */
    public static Parent getSimulationRoot() {
        BorderPane root = new BorderPane();

        // Create simulation
        PhotoelectricSimulation simulation = new PhotoelectricSimulation(600, 600);

        // Left: Simulation Pane
        Pane simPane = new Pane();
        simPane.setStyle("-fx-background-color: linear-gradient(to bottom, #87CEEB 0%, #E0F6FF 100%);");
        simPane.setMinSize(600, 600);
        simPane.setPrefSize(600, 600);

        // Add title and instructions
        Text titleText = new Text(20, 30, "Photoelectric Effect Simulation");
        titleText.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleText.setFill(Color.DARKBLUE);

        Text instructionText = new Text(20, 50, "Photons (colored circles) travel from left to right and interact with electrons on the metal surface");
        instructionText.setFont(Font.font("System", 12));
        instructionText.setFill(Color.DARKBLUE);

        simPane.getChildren().addAll(titleText, instructionText, simulation.getMetalSurface());
        root.setCenter(simPane);

        // Right: Control Panel
        PhotoelectricControlPanel controls = new PhotoelectricControlPanel(simulation);
        root.setRight(controls);
        return root;
    }

    /**
     * JavaFX entry point. Sets up and shows the main simulation window.
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(getSimulationRoot(), 900, 600);
        primaryStage.setTitle("Photoelectric Effect Simulation");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    /**
     * Main method to launch the JavaFX application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
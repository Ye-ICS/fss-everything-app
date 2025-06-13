package com.fss.everythingapp.simulations.momentum;

import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.ScrollPane;
import javafx.beans.InvalidationListener;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.Scene;


public class MomentumRoot extends SplitPane {
    private SplitPane splitPane;

    public MomentumRoot() {
        // Set up simulation and controls
        Simulation simulation = new Simulation(600, 600);

                // Use SplitPane to divide the window into two resizable sections:
        // one for simulation display and one for controls
        splitPane = new SplitPane();
        splitPane.setDividerPositions(0.7); // 70% of the window goes to simulation, 30% to controls

        // ----- SIMULATION PANE SETUP -----
        Pane simPane = new Pane();

        // Bind the simulation's dimensions to the pane's dimensions
        // This makes the simulation responsive to window resizing
        simPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            simulation.setWidth(newVal.doubleValue());
        });
        simPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            simulation.setHeight(newVal.doubleValue());
        });

        // Style the simulation pane with a gradient background and border
        simPane.setStyle("-fx-background-color: linear-gradient(to bottom, #e3f8ff, #b3e6ff);");
        simPane.setMinSize(400, 400); // Ensure a minimum size for usability
        simPane.setBorder(new Border(new BorderStroke(
            Color.DARKBLUE, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));

        // ----- PUCK SETUP -----
        // Create two pucks with different masses
        Puck p1 = new Puck(200, 300, 10);  // smaller puck
        Puck p2 = new Puck(400, 300, 50);  // larger puck

        // ----- CONTROL PANEL SETUP -----
        // Create the control panel UI linked to the simulation and both pucks
        ControlPanel controls = new ControlPanel(simulation, p1, p2);

        // Put the control panel inside a scroll pane for better usability on smaller screens
        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane(controls);
        scrollPane.setFitToWidth(true); // Make scroll pane match the width of the control panel
        scrollPane.setStyle("-fx-background-color: #f8f9fa;"); // Light background

        // Add the two panes (simulation + controls) to the SplitPane layout
        splitPane.getItems().addAll(simPane, scrollPane);
        splitPane.setStyle("-fx-background-color: #ffffff;"); // Divider background color

        // ----- SIMULATION INITIALIZATION -----
        // Pass the control panel into the simulation so it can update based on user settings
        simulation.setControlPanel(controls);

        // Add pucks and the vector rendering group to the simulation pane
        simPane.getChildren().addAll(p1, p2);
        simPane.getChildren().add(simulation.getVectorGroup()); // This enables vector arrows to be visible

        // Add the pucks to the simulation engine
        simulation.addPuck(p1);
        simulation.addPuck(p2);

        // Enable mouse dragging features (for position and velocity setting)
        simulation.setupPuckDrag(p1, p1);
        simulation.setupPuckDrag(p2, p2);

        // ----- INITIAL TEST VELOCITIES -----
        // These give the pucks some movement when the simulation starts (used for demo/testing)
        p1.setVelocity(new Vector2D(230, 100));
        p2.setVelocity(new Vector2D(-370, 200));
    }

    public Parent getRootNode() {
        return splitPane;
    }
}

package com.fss.everythingapp.simulations.momentum;

import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.ScrollPane;

public class MomentumRoot extends SplitPane {
    public MomentumRoot() {
        // Set up simulation and controls
        Simulation simulation = new Simulation(600, 600);

        // Simulation pane
        Pane simPane = new Pane();
        simPane.setMinSize(400, 400);
        simPane.setPrefSize(600, 600);

        // Create pucks
        Puck p1 = new Puck(200, 300, 10);
        Puck p2 = new Puck(400, 300, 50);

        // Control panel
        ControlPanel controls = new ControlPanel(simulation, p1, p2);
        ScrollPane scrollPane = new ScrollPane(controls);
        scrollPane.setFitToWidth(true);

        // Add pucks and vectors to simPane
        simPane.getChildren().addAll(p1, p2, simulation.getVectorGroup());
        simulation.addPuck(p1);
        simulation.addPuck(p2);
        simulation.setControlPanel(controls);
        simulation.setupPuckDrag(p1, p1);
        simulation.setupPuckDrag(p2, p2);

        // Set up split pane
        this.getItems().addAll(simPane, scrollPane);
        this.setDividerPositions(0.7);
    }

    public Parent getRootNode() {
        return this;
    }
}

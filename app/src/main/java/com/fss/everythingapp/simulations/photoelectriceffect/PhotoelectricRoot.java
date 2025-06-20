package com.fss.everythingapp.simulations.photoelectriceffect;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.beans.InvalidationListener;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

/**
 * Root node for the photoelectric effect simulation.
 * Contains the simulation pane, control panel, and manages dynamic updates.
 */
public class PhotoelectricRoot extends BorderPane {
    private PhotoelectricSimulation simulation;
    private Pane simPane;
    private Text instructionText;

    /**
     * Constructs the root node for the photoelectric simulation.
     * Sets up the simulation pane, control panel, and listeners.
     */
    public PhotoelectricRoot() {
        // Create simulation
        simulation = new PhotoelectricSimulation(600, 600);

        // Left: Simulation Pane
        simPane = new Pane();
        simPane.setStyle("-fx-background-color: linear-gradient(to bottom, #87CEEB 0%, #E0F6FF 100%);");
        simPane.setMinSize(600, 600);
        simPane.setPrefSize(600, 600);

        // Add title and instructions
        Text titleText = new Text(20, 30, "Photoelectric Effect Simulation");
        titleText.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleText.setFill(Color.DARKBLUE);

        instructionText = new Text(20, 50, "Photons (colored smaller circles) travel from the top of the screen and interact with electrons on the metal surface.");
        instructionText.setFont(Font.font("System", 12));
        instructionText.setFill(Color.DARKBLUE);

        simPane.getChildren().addAll(titleText, instructionText, simulation.getMetalSurface());
        setCenter(simPane);

        // Right: Control Panel
        PhotoelectricControlPanel controls = new PhotoelectricControlPanel(simulation);
        setRight(controls);

        // Set up listeners for resizing and dynamic elements
        setupResizeListeners();
        setupDynamicElements();
    }

    /**
     * Sets up listeners to handle resizing of the simulation pane.
     * Updates simulation dimensions and instruction text position.
     */
    private void setupResizeListeners() {
        InvalidationListener resizeListener = observable -> {
            double newWidth = simPane.getWidth();
            double newHeight = simPane.getHeight();

            if (newWidth > 0 && newHeight > 0) {
                simulation.updateDimensions(newWidth, newHeight);

                double textY = Math.min(50, newHeight - 20);
                if (instructionText != null) {
                    instructionText.setY(textY);
                    if (newWidth < 500) {
                        instructionText.setText("Photons interact with electrons\non the metal surface");
                    } else {
                        instructionText.setText("Photons (colored circles) travel from left to right and interact with electrons on the metal surface");
                    }
                }
            }
        };

        simPane.widthProperty().addListener(resizeListener);
        simPane.heightProperty().addListener(resizeListener);
    }

    /**
     * Sets up a timeline to periodically update the visual elements (photons/electrons).
     */
    private void setupDynamicElements() {
        Timeline elementUpdateTimeline = new Timeline(
            new KeyFrame(
                Duration.millis(50),
                e -> updateVisualElements()
            )
        );
        elementUpdateTimeline.setCycleCount(Timeline.INDEFINITE);
        elementUpdateTimeline.play();
    }

    /**
     * Updates the simulation pane to reflect the current state of photons and electrons.
     * Removes inactive elements and adds new ones as needed.
     */
    private void updateVisualElements() {
        var currentPhotons = simulation.getPhotons();
        var currentElectrons = simulation.getElectrons();

        // Remove photons that are no longer in the simulation
        simPane.getChildren().removeIf(node -> {
            if (node instanceof Photon) {
                Photon photon = (Photon) node;
                return !currentPhotons.contains(photon) || photon.isAbsorbed();
            }
            return false;
        });

        // Remove electrons that are no longer in the simulation
        simPane.getChildren().removeIf(node -> {
            if (node instanceof Electron) {
                Electron electron = (Electron) node;
                return !currentElectrons.contains(electron);
            }
            return false;
        });

        // Add new photons that aren't already in the pane
        for (Photon photon : currentPhotons) {
            if (!simPane.getChildren().contains(photon) && !photon.isAbsorbed()) {
                simPane.getChildren().add(photon);
            }
        }

        // Add new electrons that aren't already in the pane
        for (Electron electron : currentElectrons) {
            if (!simPane.getChildren().contains(electron)) {
                simPane.getChildren().add(electron);
            }
        }
    }

    /**
     * Returns the root node for embedding in a scene.
     * @return This BorderPane instance.
     */
    public Parent getRootNode() {
        return this;
    }
}
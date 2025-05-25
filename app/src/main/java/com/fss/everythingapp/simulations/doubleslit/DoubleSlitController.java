package com.fss.everythingapp.simulations.doubleslit;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

public class DoubleSlitController{
    @FXML private Canvas simulationCanvas;

    @FXML private Slider wavelengthSlider;
    @FXML private Slider separationSlider;
    @FXML private Slider widthSlider;
    
    @FXML private Label wavelengthLabel;
    @FXML private Label separationLabel;
    @FXML private Label widthLabel;
    
    private DoubleSlit simulation;
    private GraphicsContext gc;
    
    @FXML
    private void initialize() {
        simulation = new DoubleSlit();
        gc = simulationCanvas.getGraphicsContext2D();

        setupSliders();
        updateDisplay();
    }
    
    private void setupSliders() {
       
    }
    
    private void updateDisplay() {
        simulation.calculateInterferencePattern();
    }
}
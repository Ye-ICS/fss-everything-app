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
       // Wavelength slider (400-750 nm)
       wavelengthSlider.setMin(400);
       wavelengthSlider.setMax(750);
       wavelengthSlider.setValue(400);
       wavelengthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
           updateWavelengthLabel();
           updateSimulation();
       });
       
       // Slit separation slider (0-5 mm)
       separationSlider.setMin(0);
       separationSlider.setMax(5.0);
       separationSlider.setValue(0);
       separationSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
           updateSeparationLabel();
           updateSimulation();
       });
       
       // Slit width slider (0-1 mm)
       widthSlider.setMin(0);
       widthSlider.setMax(1.0);
       widthSlider.setValue(0);
       widthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
           updateWidthLabel();
           updateSimulation();
       });
    }

    private void updateWavelengthLabel() {
        
    }
    private void updateSeparationLabel() {
        
    }
    private void updateWidthLabel() {
        
    }

    private void updateSimulation() {
       
    }
    
    private void updateDisplay() {
        simulation.calculateInterferencePattern();
    }
}
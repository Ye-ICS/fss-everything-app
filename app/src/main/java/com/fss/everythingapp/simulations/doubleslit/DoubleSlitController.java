package com.fss.everythingapp.simulations.doubleslit;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class DoubleSlitController{
    @FXML private Canvas simulationCanvas;
    @FXML private Canvas explanationCanvas;

    @FXML private Slider wavelengthSlider;
    @FXML private Slider separationSlider;
    @FXML private Slider widthSlider;
    @FXML private Slider screenDistanceSlider;
    
    @FXML private Label wavelengthLabel;
    @FXML private Label separationLabel;
    @FXML private Label widthLabel;
    @FXML private Label screenDistanceLabel;
    
    private DoubleSlit doubleSlitSimulation;
    private LightCanvas lightCanvasDisplay;
    private ExplanationCanvas explanationCanvasDisplay;
    
    @FXML
    private void initialize() {
        doubleSlitSimulation = new DoubleSlit();
        lightCanvasDisplay = new LightCanvas(simulationCanvas);

        explanationCanvasDisplay = new ExplanationCanvas(explanationCanvas);

        setupSliders();
        updateSimulation();
    }

    @FXML
    private void reset() {
        wavelengthSlider.setValue(400);
        separationSlider.setValue(0);
        widthSlider.setValue(0);
        screenDistanceSlider.setValue(0);
        updateSimulation();
    }
    
    void setupSliders() {
       // Wavelength slider (400-750 nm)
       wavelengthSlider.setMin(400);
       wavelengthSlider.setMax(750);
       wavelengthSlider.setValue(400);
       wavelengthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
           updateWavelengthLabel();
           updateSimulation();
       });
       
       // Slit separation slider (0-500 micrometers)
       separationSlider.setMin(0);
       separationSlider.setMax(500.0);
       separationSlider.setValue(0);
       separationSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
           updateSeparationLabel();
           updateSimulation();
       });
       
       // Slit width slider (0-100 micrometers)
       widthSlider.setMin(0);
       widthSlider.setMax(100.0);
       widthSlider.setValue(0);
       widthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
           updateWidthLabel();
           updateSimulation();
       });
       
       // Screen distance slider (0-2 meters)
       screenDistanceSlider.setMin(0);
       screenDistanceSlider.setMax(2.0);
       screenDistanceSlider.setValue(0);
       screenDistanceSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
           updateScreenDistanceLabel();
           updateSimulation();
       });
    }

    void updateWavelengthLabel() {
        wavelengthLabel.setText(String.format("Wavelength: %.0f nm", wavelengthSlider.getValue()));  
    }
    
    void updateSeparationLabel() {
        separationLabel.setText(String.format("Slit Separation: %.0f μm", separationSlider.getValue()));
    }
    
    void updateWidthLabel() {
        widthLabel.setText(String.format("Slit Width: %.0f μm", widthSlider.getValue()));
    }
    
    void updateScreenDistanceLabel() {
        screenDistanceLabel.setText(String.format("Screen Distance: %.2f m", screenDistanceSlider.getValue()));
    }

    void updateSimulation() {
        // Set simulation parameters - convert micrometers to meters
        doubleSlitSimulation.setWavelength(wavelengthSlider.getValue() * 1e-9); // nm to m
        doubleSlitSimulation.setSlitProperties(
            separationSlider.getValue() * 1e-6, // μm to m
            widthSlider.getValue() * 1e-6       // μm to m
        );
        doubleSlitSimulation.setScreenDistance(screenDistanceSlider.getValue()); // already in meters
        
        doubleSlitSimulation.calculateInterferencePattern();
        
        // Get the interference pattern and draw it
        List<Double> pattern = doubleSlitSimulation.getInterferencePattern();
        lightCanvasDisplay.drawInterferencePattern(pattern, wavelengthSlider.getValue(), screenDistanceSlider.getValue(), widthSlider.getValue());
        
        // Draw the explanation canvas
        explanationCanvasDisplay.drawExplanation(wavelengthSlider.getValue(), screenDistanceSlider.getValue(), widthSlider.getValue());
    }
}
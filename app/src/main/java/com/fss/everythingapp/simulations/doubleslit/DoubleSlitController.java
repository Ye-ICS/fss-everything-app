package com.fss.everythingapp.simulations.doubleslit;

import java.util.List;

import javafx.application.Platform;
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

    void updateWavelengthLabel() {
        
    }
    void updateSeparationLabel() {
        
    }
    void updateWidthLabel() {
        
    }

    void updateSimulation() {
        Platform.runLater(() -> {
            // Set simulation parameters directly
            simulation.setWavelength(wavelengthSlider.getValue() * 1e-9);
            simulation.setSlitProperties(
                separationSlider.getValue() * 1e-3,
                widthSlider.getValue() * 1e-3
            );
            
            simulation.calculateInterferencePattern();
            drawInterferencePattern();
        });
    }
    
    void drawInterferencePattern() {
        double canvasWidth = simulationCanvas.getWidth();
        double canvasHeight = simulationCanvas.getHeight();
        
        // Clear the entire canvas with a black background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        
        // Retrieve the calculated interference pattern (intensity values)
        List<Double> pattern = simulation.getInterferencePattern();
        
        // Find the maximum intensity value
        double maxIntensity = 0.0;
        for (double value : pattern) {
            if (value > maxIntensity) {
                maxIntensity = value;
            }
        }
        // Prevent division by zero if all intensities are zero
        if (maxIntensity == 0) { maxIntensity = 1.0; }
        
        // Calculate the width of each vertical bar (pixel) to draw
        double pixelWidth = canvasWidth / pattern.size();
        
        // Loop through each point in the pattern and draw a colored bar
        for (int i = 0; i < pattern.size(); i++) {
            // Normalize intensity to a value between 0 and 1
            double intensity = pattern.get(i) / maxIntensity;

            // Get the color corresponding to the current wavelength and intensity
            Color color = wavelengthToColor(wavelengthSlider.getValue(), intensity);
            gc.setFill(color);
            
            // Calculate the x position for this bar
            double x = i * pixelWidth;

            // Draw a vertical rectangle representing the intensity at this point
            gc.fillRect(x, 0, pixelWidth + 1, canvasHeight);
        }
    }
    
    Color wavelengthToColor(double wl, double intensity) {
        double r = 0;
        double g = 0;
        double b = 0;
        
        if (wl >= 400 && wl < 440) {
            r = -(wl - 440) / (440 - 400);
            b = 1.0;
        } else if (wl >= 440 && wl < 490) {
            g = (wl - 440) / (490 - 440);
            b = 1.0;
        } else if (wl >= 490 && wl < 510) {
            g = 1.0;
            b = -(wl - 510) / (510 - 490);
        } else if (wl >= 510 && wl < 580) {
            r = (wl - 510) / (580 - 510);
            g = 1.0;
        } else if (wl >= 580 && wl < 645) {
            r = 1.0;
            g = -(wl - 645) / (645 - 580);
        } else if (wl >= 645 && wl <= 700) {
            r = 1.0;
        }
        
        // Apply intensity
        r *= intensity;
        g *= intensity;
        b *= intensity;
        
        return Color.color(r,g,b);
    }
}
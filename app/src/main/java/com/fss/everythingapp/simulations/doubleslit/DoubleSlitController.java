package com.fss.everythingapp.simulations.doubleslit;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
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
    
    @FXML private Button backButton;
    @FXML private Button resetButton;
    
    private DoubleSlit doubleSlitSimulation;
    private LightCanvas lightCanvasDisplay;
    private ExplanationCanvas explanationCanvasDisplay;
    
    /**
     * Initializes the DoubleSlitController by setting up the simulation components, configuring the sliders, and updating the simulation display.
     * This method is automatically called after the FXML file has been loaded.
     * It performs the following tasks:
     * - Initializes the DoubleSlit simulation object.
     * - Sets up the LightCanvas and ExplanationCanvas displays.
     * - Configures slider listeners to update the simulation dynamically.
     * - Calls the updateSimulation method to render the initial state.
     */
    @FXML
    private void initialize() {
        doubleSlitSimulation = new DoubleSlit();
        lightCanvasDisplay = new LightCanvas(simulationCanvas);

        explanationCanvasDisplay = new ExplanationCanvas(explanationCanvas);

        setupSliders();
        updateSimulation();
    }

    /**
     * Resets the simulation parameters to their default values and updates the simulation display.
     * This method sets the sliders to predefined values:
     * - Wavelength: 400 nm
     * - Slit separation: 0 μm
     * - Slit width: 0 μm
     * - Screen distance: 0 meters
     * After resetting the sliders, it calls the updateSimulation method to reflect the changes.
     */
    @FXML
    private void reset() {
        wavelengthSlider.setValue(400);
        separationSlider.setValue(0);
        widthSlider.setValue(0);
        screenDistanceSlider.setValue(0);
        updateSimulation();
    }

    /**
     * Handles the back button action to return to the simulations menu.
     * This method attempts to load the SimulationsMenu.fxml file and set it as the new root of the current scene. (Credit to Maxim)
     */
    @FXML
    private void back() {
        try {
            javafx.scene.Scene scene = backButton.getScene();
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                getClass().getResource("/com/fss/everythingapp/simulations/SimulationsMenu.fxml")
            );
            javafx.scene.Parent menuRoot = loader.load();
            scene.setRoot(menuRoot);
        } catch (Exception ex) {}
    }
    
    private void setupSliders() {
       // Wavelength slider (400-750 nm)
       wavelengthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
           updateWavelengthLabel();
           updateSimulation();
       });
       
       // Slit separation slider (0-500 micrometers)
       separationSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
           updateSeparationLabel();
           updateSimulation();
       });
       
       // Slit width slider (0-100 micrometers)
       widthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
           updateWidthLabel();
           updateSimulation();
       });
       
       // Screen distance slider (0-2 meters)
       screenDistanceSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
           updateScreenDistanceLabel();
           updateSimulation();
       });
    }

    private void updateWavelengthLabel() {
        wavelengthLabel.setText(String.format("Wavelength: %.0f nm", wavelengthSlider.getValue()));  
    }
    
    private void updateSeparationLabel() {
        separationLabel.setText(String.format("Slit Separation: %.0f μm", separationSlider.getValue()));
    }
    
    private void updateWidthLabel() {
        widthLabel.setText(String.format("Slit Width: %.0f μm", widthSlider.getValue()));
    }
    
    private void updateScreenDistanceLabel() {
        screenDistanceLabel.setText(String.format("Screen Distance: %.2f m", screenDistanceSlider.getValue()));
    }

    /**
     * Updates the simulation by calculating the interference pattern based on the current
     * values of the sliders and rendering the results on the display canvases.
     * 
     * The method performs the following steps:
     * 1. Calculates the interference pattern using the double slit simulation parameters:
     *    - Separation between the slits (μm).
     *    - Width of the slits (μm).
     *    - Wavelength of the light (nm).
     *    - Distance to the screen (m).
     * 2. Draws the calculated interference pattern on the light canvas display.
     * 3. Updates the explanation canvas with relevant details based on the current slider values.
     */
    private void updateSimulation() {
        // Get the interference pattern and draw it
        List<Double> pattern = doubleSlitSimulation.calculateInterferencePattern(separationSlider.getValue(), widthSlider.getValue(), wavelengthSlider.getValue(), screenDistanceSlider.getValue());

        // Draw the lightCanvasDisplay canvas
        lightCanvasDisplay.drawInterferencePattern(pattern, wavelengthSlider.getValue(), screenDistanceSlider.getValue(), widthSlider.getValue());
        
        // Draw the explanation canvas
        explanationCanvasDisplay.drawExplanation(wavelengthSlider.getValue(), screenDistanceSlider.getValue(), widthSlider.getValue(), separationSlider.getValue());
    }
}
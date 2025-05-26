package com.fss.everythingapp.simulations.photoelectriceffect;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class PhotoelectricController {
    
    @FXML private Canvas simulationCanvas;
    @FXML private Slider wavelengthSlider;
    @FXML private Slider angleSlider;
    @FXML private Slider materialSlider;
    @FXML private Slider velocitySlider;
    @FXML private Label wavelengthLabel;
    @FXML private Label angleLabel;
    @FXML private Label materialLabel;
    @FXML private Label velocityLabel;
    @FXML private Label energyLabel;
    @FXML private Label thresholdLabel;
    
    private List<Electron> electrons;
    private AnimationTimer animationTimer;
    private GraphicsContext gc;
    private Random random;
    private long lastElectronTime;
    
    // Physics constants (simplified for visualization)
    private static final double PLANCK_CONSTANT = 4.136e-15; // eV⋅s
    private static final double LIGHT_SPEED = 3e8; // m/s
    private static final double ELECTRON_EMISSION_RATE = 200_000_000; // nanoseconds
    
    // Material work functions (in eV)
    private static final double[] WORK_FUNCTIONS = {2.3, 4.3, 5.1, 6.35}; // Cs, Cu, Pt, W
    private static final String[] MATERIAL_NAMES = {"Cesium", "Copper", "Platinum", "Tungsten"};
    
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        electrons = new ArrayList<>();
        random = new Random();
        gc = simulationCanvas.getGraphicsContext2D();
        
        setupElectrons();
        setupSliders();
        startAnimation();
    }
    
    private void setupElectrons() {
        // Initialize electrons
        for (int i = 0; i < 20; i++) {
            Electron electron = new Electron(100, 300 + random.nextDouble() * 100);
            electrons.add(electron);
        }
    }
    
    private void setupSliders() {
        // Wavelength slider (200-800 nm)
        wavelengthSlider.setMin(200);
        wavelengthSlider.setMax(800);
        wavelengthSlider.setValue(400);
        wavelengthSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateLabels());
        
        // Angle slider (0-90 degrees)
        angleSlider.setMin(0);
        angleSlider.setMax(90);
        angleSlider.setValue(45);
        angleSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateLabels());
        
        // Material slider (0-3 for different materials)
        materialSlider.setMin(0);
        materialSlider.setMax(3);
        materialSlider.setValue(1);
        materialSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateLabels());
        
        // Velocity slider (velocity factor)
        velocitySlider.setMin(0.1);
        velocitySlider.setMax(3.0);
        velocitySlider.setValue(1.0);
        velocitySlider.valueProperty().addListener((obs, oldVal, newVal) -> updateLabels());
        
        updateLabels();
    }
    
    private void updateLabels() {
        double wavelength = wavelengthSlider.getValue();
        double angle = angleSlider.getValue();
        int materialIndex = (int) materialSlider.getValue();
        double velocity = velocitySlider.getValue();
        
        wavelengthLabel.setText(String.format("Wavelength: %.0f nm", wavelength));
        angleLabel.setText(String.format("Angle: %.0f°", angle));
        materialLabel.setText("Material: " + MATERIAL_NAMES[materialIndex]);
        velocityLabel.setText(String.format("Velocity: %.1f", velocity));
        
        // Calculate photon energy: E = hc/λ
        double photonEnergy = (PLANCK_CONSTANT * LIGHT_SPEED) / (wavelength * 1e-9) * 6.242e18; // Convert to eV
        double workFunction = WORK_FUNCTIONS[materialIndex];
        
        energyLabel.setText(String.format("Photon Energy: %.2f eV", photonEnergy));
        thresholdLabel.setText(String.format("Work Function: %.2f eV", workFunction));
    }
    
    private void drawSimulation() {
        // Clear canvas
        gc.clearRect(0, 0, simulationCanvas.getWidth(), simulationCanvas.getHeight());
        
        // Draw background
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, simulationCanvas.getWidth(), simulationCanvas.getHeight());
        
        // Draw metal surface
        int materialIndex = (int) materialSlider.getValue();
        Color[] materialColors = {Color.GOLD, Color.ORANGE, Color.LIGHTGRAY, Color.DARKGRAY};
        gc.setFill(materialColors[materialIndex]);
        gc.fillRect(50, 200, 100, 300);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(50, 200, 100, 300);
        
        // Draw light rays
        double wavelength = wavelengthSlider.getValue();
        Color lightColor = wavelengthToColor(wavelength);
        gc.setStroke(lightColor);
        gc.setLineWidth(3);
        
        for (int i = 0; i < 5; i++) {
            double startX = 250 + i * 30;
            double startY = 100 + i * 20;
            double endX = 150;
            double endY = 250 + i * 40;
            gc.strokeLine(startX, startY, endX, endY);
        }
        
        // Draw electrons
        for (Electron electron : electrons) {
            if (electron.isActive()) {
                // Color based on kinetic energy
                double intensity = Math.min(1.0, electron.getKineticEnergy() / 20.0);
                gc.setFill(Color.color(0, intensity, 1.0 - intensity));
                gc.fillOval(electron.getX() - 3, electron.getY() - 3, 6, 6);
                gc.setStroke(Color.DARKBLUE);
                gc.strokeOval(electron.getX() - 3, electron.getY() - 3, 6, 6);
            }
        }
        
        // Draw title
        gc.setFill(Color.BLACK);
        gc.fillText("Photoelectric Effect Simulation", 200, 30);
    }
    
    private Color wavelengthToColor(double wavelength) {
        // Convert wavelength to RGB color (simplified)
        if (wavelength < 400) return Color.VIOLET;
        else if (wavelength < 450) return Color.BLUE;
        else if (wavelength < 500) return Color.CYAN;
        else if (wavelength < 570) return Color.GREEN;
        else if (wavelength < 590) return Color.YELLOW;
        else if (wavelength < 620) return Color.ORANGE;
        else return Color.RED;
    }
    
    private void startAnimation() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateSimulation(now);
            }
        };
        animationTimer.start();
    }
    
    private void updateSimulation(long now) {
        double deltaTime = 1.0; // Simplified time step
        
        // Update all electrons
        for (Electron electron : electrons) {
            electron.update(deltaTime);
        }
        
        // Emit new electrons based on light parameters
        if (now - lastElectronTime > ELECTRON_EMISSION_RATE) {
            emitElectron();
            lastElectronTime = now;
        }
        
        // Draw everything
        drawSimulation();
    }
    
    private void emitElectron() {
        double wavelength = wavelengthSlider.getValue();
        double angle = angleSlider.getValue();
        int materialIndex = (int) materialSlider.getValue();
        double velocity = velocitySlider.getValue();
        
        // Calculate photon energy
        double photonEnergy = (PLANCK_CONSTANT * LIGHT_SPEED) / (wavelength * 1e-9) * 6.242e18;
        double workFunction = WORK_FUNCTIONS[materialIndex];
        
        // Only emit if photon energy exceeds work function
        if (photonEnergy > workFunction) {
            // Find an inactive electron to emit
            for (Electron electron : electrons) {
                if (!electron.isActive()) {
                    // Reset position on metal surface
                    electron.setPosition(150, 350 + random.nextDouble() * 100);
                    electron.emit(photonEnergy, workFunction, angle, velocity);
                    break;
                }
            }
        }
    }
    
    public void stopAnimation() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
    }
}

package com.fss.everythingapp.simulations.photoelectriceffect;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    
    private double ballX = 300, ballY = 100;
    private boolean isElectron = false;
    private double electronVX = 0, electronVY = 0;


    @FXML private Button startButton; // Add this field

    
    // Change this to set the surface in the middle of the canvas
    private static final double SURFACE_Y = 300; // Middle of 600px canvas

    @FXML
    public void initialize() {
        electrons = new ArrayList<>();
        GraphicsContext gc = simulationCanvas.getGraphicsContext2D();

        // Setup listeners to update labels dynamically
        wavelengthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double wavelength = newVal.doubleValue();
            wavelengthLabel.setText(String.format("Wavelength: %.0f nm", wavelength));
            double energy = 1240.0 / wavelength; // eV calculation: E = hc/λ
            energyLabel.setText(String.format("Photon Energy: %.2f eV", energy));
        });

        angleSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            angleLabel.setText(String.format("Angle: %.0f°", newVal.doubleValue()));
        });

        materialSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            int material = newVal.intValue();
            String[] names = {"Cesium", "Copper", "Platinum", "Tungsten"};
            double[] workFunctions = {2.3, 4.3, 5.1, 6.35};
            materialLabel.setText("Material: " + names[material]);
            thresholdLabel.setText(String.format("Work Function: %.2f eV", workFunctions[material]));
        });

        velocitySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            velocityLabel.setText(String.format("Velocity: %.1f", newVal.doubleValue()));
        });

        setupAnimation(gc);
    }

    
    private void setupElectrons() {
        // Initialize electrons
        for (int i = 0; i < 20; i++) {
            Electron electron = new Electron(100, 300 + random.nextDouble() * 100);
            electrons.add(electron);
        }
    }
    @FXML
    private void handleStartSimulation() {
        electrons.clear();
        double wavelength = wavelengthSlider.getValue();
        double photonEnergy = 1240.0 / wavelength; // hc/λ (in eV)
        double angle = angleSlider.getValue();
        int material = (int) materialSlider.getValue();
        double[] workFunctions = {2.3, 4.3, 5.1, 6.35};
        double workFunction = workFunctions[material];
        double velocity = velocitySlider.getValue();

        // Simulate a few electrons
        for (int i = 0; i < 10; i++) {
            Electron e = new Electron(150, 300 + i * 5); // Origin point near surface
            e.emit(photonEnergy, workFunction, angle, velocity);
            electrons.add(e);
        }
    }

    private void setupSliders() {
        // Wavelength slider (200-800 nm)
        wavelengthSlider.setMin(200);
        wavelengthSlider.setMax(800);
        wavelengthSlider.setValue(400);
        wavelengthSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateLabels());
        
        // Angle slider (0-90 degrees)
        angleSlider.setMin(0);
        angleSlider.setMax(90);
        angleSlider.setValue(45);
        angleSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateLabels());
        
        // Material slider (0-3 for different materials)
        materialSlider.setMin(0);
        materialSlider.setMax(3);
        materialSlider.setValue(1);
        materialSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
             updateLabels();
            });
        
        // Velocity slider (velocity factor)
        velocitySlider.setMin(0.1);
        velocitySlider.setMax(3.0);
        velocitySlider.setValue(1.0);
        velocitySlider.valueProperty().addListener((observable, oldValue, newValue) -> updateLabels());
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
        
        // Draw metal surface in the middle
        int materialIndex = (int) materialSlider.getValue();
        Color[] materialColors = {Color.GOLD, Color.ORANGE, Color.LIGHTGRAY, Color.DARKGRAY};
        gc.setFill(materialColors[materialIndex]);
        gc.fillRect(50, SURFACE_Y, 100, 300); // Start at SURFACE_Y
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(50, SURFACE_Y, 100, 300);
        
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

        // Draw the ball/photon or electron
        if (!isElectron) {

            gc.setFill(Color.PURPLE); // Photon color
            gc.fillOval(ballX - 10, ballY - 10, 20, 20);
        } else {
            gc.setFill(Color.BLUE); // Electron color
            gc.fillOval(ballX - 6, ballY - 6, 12, 12);
        }
        
        // Draw title
        gc.setFill(Color.BLACK);
        gc.fillText("Photoelectric Effect Simulation", 200, 30);
        
        // Test ball
        gc.setFill(Color.RED);
        gc.fillOval(300, 100, 20, 20); // Test ball
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
    
    private void setupAnimation(GraphicsContext gc) {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, simulationCanvas.getWidth(), simulationCanvas.getHeight());

                // Draw metal surface
                gc.setFill(Color.GOLDENROD);
                gc.fillRect(50, 300, 100, 300);

                // Update and draw electrons
                for (Electron e : electrons) {
                    if (e.isActive()) {
                        e.update();
                        gc.setFill(Color.BLUE);
                        gc.fillOval(e.getX(), e.getY(), 5, 5);
                    }
                }
            }
        };
        animationTimer.start();
    }

    
    private void resetBall() {
        // Start a new photon at the top, with a random or slider-based angle
        ballX = 300;
        ballY = 100;
        isElectron = false;
        electronVX = 0;
        electronVY = 0;
    }

    private void updateSimulation(long now) {
        double deltaTime = 1.0;

        // Ball (photon/electron) movement
        if (!isElectron) {
            // Move photon at an angle (use angleSlider for direction)
            double angle = angleSlider.getValue();
            double speed = 4.0; // You can make this a slider if you want
            ballX += speed * Math.cos(Math.toRadians(angle)) * deltaTime;
            ballY += speed * Math.sin(Math.toRadians(angle)) * deltaTime;

            // Check for collision with surface (middle of canvas)
            if (ballX >= 50 && ballX <= 150 && ballY >= SURFACE_Y) {
                // Calculate photon energy and work function
                double wavelength = wavelengthSlider.getValue();
                int materialIndex = (int) materialSlider.getValue();
                double photonEnergy = (PLANCK_CONSTANT * LIGHT_SPEED) / (wavelength * 1e-9) * 6.242e18;
                double workFunction = WORK_FUNCTIONS[materialIndex];

                if (photonEnergy > workFunction) {
                    // Become electron: bounce off with velocity based on energy
                    isElectron = true;
                    double velocity = velocitySlider.getValue();
                    double kineticEnergy = photonEnergy - workFunction;
                    double electronSpeed = velocity * Math.sqrt(kineticEnergy / 10.0);
                    // Bounce off at the same angle but upward
                    electronVX = electronSpeed * Math.cos(Math.toRadians(angle));
                    electronVY = -electronSpeed * Math.abs(Math.sin(Math.toRadians(angle)));
                    // Move ball to just above the surface to avoid sticking
                    ballY = SURFACE_Y;
                } else {
                    // No emission, just reset
                    resetBall();
                }
            }
        } else {
            // Electron movement
            ballX += electronVX * deltaTime;
            ballY += electronVY * deltaTime;
            electronVY += 0.1 * deltaTime; // gravity

            // Reset if electron leaves screen
            if (ballX > 600 || ballY > 600 || ballX < 0) {
                resetBall();
            }
        }
        
        // Update all electrons
        for (Electron electron : electrons) {
            electron.update();
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

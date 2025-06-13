package com.fss.everythingapp.simulations.photoelectriceffect;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

/**
 * Control panel for the photoelectric effect simulation.
 * Provides controls for photon energy, intensity, work function, and displays statistics.
 */
public class PhotoelectricControlPanel extends VBox {
    private PhotoelectricSimulation sim;
    private Label statsLabel;
    private Label equationLabel;

    /**
     * Constructor for the control panel.
     * Sets up all controls and layout.
     * 
     * @param sim The PhotoelectricSimulation instance to control.
     */
    public PhotoelectricControlPanel(PhotoelectricSimulation sim) {
        this.sim = sim;

        setPadding(new Insets(15));
        setSpacing(15);
        setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #cccccc; -fx-border-width: 1;");
        setPrefWidth(300);

        // Title
        Label titleLabel = new Label("Photoelectric Effect");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.DARKBLUE);

        // Create main controls
        VBox controlsBox = createControlsSection();

        // Create equation display
        VBox equationBox = createEquationSection();

        // Create statistics display
        VBox statsBox = createStatsSection();

        // Control buttons
        HBox buttonBox = createButtonSection();

        getChildren().addAll(titleLabel, new Separator(), controlsBox, 
                           new Separator(), equationBox, new Separator(), 
                           statsBox, new Separator(), buttonBox);

        // Start updating statistics
        startStatsUpdate();
    }

    /**
     * Creates the section with sliders and controls for simulation parameters.
     * @return VBox containing controls.
     */
    private VBox createControlsSection() {
        VBox controlsBox = new VBox(10);

        // Photon Energy Control
        Label energyLabel = new Label("Photon Energy (eV):");
        energyLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

        Slider energySlider = new Slider(0.5, 6.0, sim.getPhotonEnergy());
        energySlider.setShowTickLabels(true);
        energySlider.setShowTickMarks(true);
        energySlider.setMajorTickUnit(1.0);
        energySlider.setMinorTickCount(4);
        energySlider.setBlockIncrement(0.1);

        Label energyValueLabel = new Label(String.format("%.2f eV", sim.getPhotonEnergy()));
        energyValueLabel.setFont(Font.font("System", 11));

        energySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            sim.setPhotonEnergy(newVal.doubleValue());
            energyValueLabel.setText(String.format("%.2f eV", newVal.doubleValue()));
        });

        // Light Intensity Control
        Label intensityLabel = new Label("Light Intensity (photons/sec):");
        intensityLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

        Slider intensitySlider = new Slider(0.1, 5.0, sim.getIntensity());
        intensitySlider.setShowTickLabels(true);
        intensitySlider.setShowTickMarks(true);
        intensitySlider.setMajorTickUnit(1.0);
        intensitySlider.setMinorTickCount(4);
        intensitySlider.setBlockIncrement(0.1);

        Label intensityValueLabel = new Label(String.format("%.1f", sim.getIntensity()));
        intensityValueLabel.setFont(Font.font("System", 11));

        intensitySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            sim.setIntensity(newVal.doubleValue());
            intensityValueLabel.setText(String.format("%.1f", newVal.doubleValue()));
        });

        // Work Function Control
        Label workFunctionLabel = new Label("Work Function (eV):");
        workFunctionLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

        Slider workFunctionSlider = new Slider(1.0, 5.0, sim.getWorkFunction());
        workFunctionSlider.setShowTickLabels(true);
        workFunctionSlider.setShowTickMarks(true);
        workFunctionSlider.setMajorTickUnit(1.0);
        workFunctionSlider.setMinorTickCount(4);
        workFunctionSlider.setBlockIncrement(0.1);

        Label workFunctionValueLabel = new Label(String.format("%.2f eV", sim.getWorkFunction()));
        workFunctionValueLabel.setFont(Font.font("System", 11));

        workFunctionSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            sim.setWorkFunction(newVal.doubleValue());
            workFunctionValueLabel.setText(String.format("%.2f eV", newVal.doubleValue()));
        });

        // Animation Speed Control
        Label speedLabel = new Label("Animation Speed:");
        speedLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

        Slider speedSlider = new Slider(0.1, 3.0, sim.getAnimationSpeed());
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(0.5);
        speedSlider.setMinorTickCount(4);
        speedSlider.setBlockIncrement(0.1);

        Label speedValueLabel = new Label(String.format("%.1fx", sim.getAnimationSpeed()));
        speedValueLabel.setFont(Font.font("System", 11));

        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            sim.setAnimationSpeed(newVal.doubleValue());
            speedValueLabel.setText(String.format("%.1fx", newVal.doubleValue()));
        });

        controlsBox.getChildren().addAll(
            energyLabel, energySlider, energyValueLabel,
            intensityLabel, intensitySlider, intensityValueLabel,
            workFunctionLabel, workFunctionSlider, workFunctionValueLabel,
            speedLabel, speedSlider, speedValueLabel
        );

        return controlsBox;
    }

    /**
     * Creates the section displaying Einstein's equation and explanation.
     * @return VBox containing equation and explanation.
     */
    private VBox createEquationSection() {
        VBox equationBox = new VBox(5);

        Label titleLabel = new Label("Einstein's Photoelectric Equation:");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

        equationLabel = new Label();
        equationLabel.setFont(Font.font("Monospace", 11));
        equationLabel.setTextFill(Color.DARKGREEN);
        updateEquation();

        Label explanationLabel = new Label("KE = Kinetic Energy of ejected electron\n" +
                                         "hf = Photon energy\n" +
                                         "φ = Work function");
        explanationLabel.setFont(Font.font("System", 9));
        explanationLabel.setTextFill(Color.GRAY);

        equationBox.getChildren().addAll(titleLabel, equationLabel, explanationLabel);
        return equationBox;
    }

    /**
     * Creates the section displaying simulation statistics.
     * @return VBox containing statistics.
     */
    private VBox createStatsSection() {
        VBox statsBox = new VBox(5);

        Label titleLabel = new Label("Simulation Statistics:");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

        statsLabel = new Label();
        statsLabel.setFont(Font.font("Monospace", 10));
        updateStats();

        statsBox.getChildren().addAll(titleLabel, statsLabel);
        return statsBox;
    }

    /**
     * Creates the section with control buttons (Start, Stop, Reset, Back).
     * @return HBox containing buttons.
     */
    private HBox createButtonSection() {
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button startBtn = new Button("Start");
        Button stopBtn = new Button("Stop");
        Button resetBtn = new Button("Reset");
        Button backBtn = new Button("Back");

        startBtn.setPrefWidth(70);
        stopBtn.setPrefWidth(70);
        resetBtn.setPrefWidth(70);
        backBtn.setPrefWidth(70);

        startBtn.setOnAction(e -> sim.start());
        stopBtn.setOnAction(e -> sim.stop());
        resetBtn.setOnAction(e -> sim.reset());

        // Back button returns to the simulations menu
        backBtn.setOnAction(e -> {
            try {
                javafx.scene.Scene scene = backBtn.getScene();
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/com/fss/everythingapp/simulations/SimulationsMenu.fxml")
                );
                javafx.scene.Parent menuRoot = loader.load();
                scene.setRoot(menuRoot);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        buttonBox.getChildren().addAll(startBtn, stopBtn, resetBtn, backBtn);
        return buttonBox;
    }

    /**
     * Updates the equation display based on current simulation parameters.
     */
    private void updateEquation() {
        double photonEnergy = sim.getPhotonEnergy();
        double workFunction = sim.getWorkFunction();
        double kineticEnergy = Math.max(0, photonEnergy - workFunction);

        String equation = String.format("KE = hf - φ\n" +
                                       "KE = %.2f - %.2f = %.2f eV\n\n" +
                                       "%s", 
                                       photonEnergy, workFunction, kineticEnergy,
                                       kineticEnergy > 0 ? "✓ Electrons will be ejected" 
                                                         : "✗ No electron ejection");

        equationLabel.setText(equation);
        equationLabel.setTextFill(kineticEnergy > 0 ? Color.DARKGREEN : Color.DARKRED);
    }

    /**
     * Updates the statistics display based on current simulation state.
     */
    private void updateStats() {
        int photonsEmitted = sim.getTotalPhotonsEmitted();
        int electronsEjected = sim.getTotalElectronsEjected();
        double efficiency = sim.getQuantumEfficiency() * 100;

        String stats = String.format("Photons Emitted: %d\n" +
                                   "Electrons Ejected: %d\n" +
                                   "Quantum Efficiency: %.1f%%\n" +
                                   "Active Photons: %d\n" +
                                   "Surface Electrons: %d",
                                   photonsEmitted, electronsEjected, efficiency,
                                   sim.getPhotons().size(),
                                   (int) sim.getElectrons().stream()
                                       .filter(e -> !e.isEjected()).count());

        statsLabel.setText(stats);
    }

    /**
     * Starts a timeline to periodically update statistics and equation display.
     */
    private void startStatsUpdate() {
        javafx.animation.Timeline timeline = new javafx.animation.Timeline(
            new javafx.animation.KeyFrame(
                javafx.util.Duration.millis(100),
                e -> {
                    updateStats();
                    updateEquation();
                }
            )
        );
        timeline.setCycleCount(javafx.animation.Timeline.INDEFINITE);
        timeline.play();
    }
}
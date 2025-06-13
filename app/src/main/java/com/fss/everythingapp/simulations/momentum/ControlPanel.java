package com.fss.everythingapp.simulations.momentum;
import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.animation.AnimationTimer;

/**
 * ControlPanel provides a comprehensive user interface for controlling and monitoring
 * the momentum physics simulation. It includes controls for physics parameters,
 * mass and velocity settings, simulation controls, customization options,
 * and real-time data visualization.
 * 
 * The panel is organized into several functional sections:
 * - Physics Properties (elasticity control)
 * - Mass Control (sliders and text fields for both pucks)
 * - Velocity Control (speed and angle inputs)
 * - Simulation Control (start/stop/reset buttons)
 * - Customization (image upload for pucks)
 * - Visualization (momentum vector display options)
 * - Real-time Data Display (conservation laws and puck statistics)
 */
public class ControlPanel extends VBox {
    
    // ===== VISUALIZATION CONTROLS =====
    @SuppressWarnings("unused")
    private CheckBox showMomentumVectorsCheck;        // Toggle for momentum vector display
    private CheckBox showMomentumComponentsCheck;     // Toggle for momentum component display
    
    // ===== REAL-TIME DATA DISPLAY LABELS =====
    // Conservation law monitoring
    private Label totalMomentumXLabel;                // Displays total X momentum
    private Label totalMomentumYLabel;                // Displays total Y momentum
    private Label totalKineticEnergyLabel;            // Displays total kinetic energy
    
    // Individual puck data monitoring
    private Label puck1SpeedLabel;                    // Displays puck 1 speed
    private Label puck2SpeedLabel;                    // Displays puck 2 speed
    private Label puck1MomentumLabel;                 // Displays puck 1 momentum magnitude
    private Label puck2MomentumLabel;                 // Displays puck 2 momentum magnitude
    
    // Collision statistics
    private Label collisionCountLabel;                // Displays number of collisions
    
    // ===== PRECISION INPUT CONTROLS =====
    // Mass control text fields (complement the sliders)
    private TextField mass1Field;                     // Text input for puck 1 mass
    private TextField mass2Field;                     // Text input for puck 2 mass
    
    // Velocity control fields (speed and angle in polar coordinates)
    private TextField puck1VelocityField;             // Speed input for puck 1
    private TextField puck1AngleField;                // Angle input for puck 1
    private TextField puck2VelocityField;             // Speed input for puck 2
    private TextField puck2AngleField;                // Angle input for puck 2
    
    // ===== SIMULATION REFERENCES =====
    private Simulation simulation;                    // Reference to the main simulation
    private Puck puck1, puck2;                       // References to the two pucks
    
    // ===== STATE TRACKING =====
    private int collisionCount = 0;                  // Tracks total collision events
    private AnimationTimer dataUpdateTimer;          // Timer for real-time data updates

    /**
     * Constructs a new ControlPanel with references to the simulation and pucks.
     * Sets up all UI components, event handlers, and initializes the layout.
     * 
     * @param sim The main Simulation object to control
     * @param p1  The first puck in the simulation
     * @param p2  The second puck in the simulation
     */
    public ControlPanel(Simulation sim, Puck p1, Puck p2) {
        // Store references for later use
        this.simulation = sim;
        this.puck1 = p1;
        this.puck2 = p2;
        
        // ===== MAIN CONTAINER SETUP =====
        setPadding(new Insets(15));                   // Add padding around entire panel
        setSpacing(15);                               // Space between major sections
        setStyle("-fx-background-color: #f5f5f5;");   // Light gray background
        setPrefWidth(350);                            // Preferred width for the panel
        setMinWidth(300);                             // Minimum width to prevent cramping

        // ===== GRID LAYOUT CONFIGURATION =====
        // Using GridPane for organized layout of controls
        GridPane grid = new GridPane();
        grid.setVgap(10);                             // Vertical gap between rows
        grid.setHgap(10);                             // Horizontal gap between columns
        grid.setPadding(new Insets(10));              // Internal padding
        
        // Define column constraints for consistent layout
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);                     // Labels column (25%)
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);                     // Controls column (50%)
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);                     // Input fields column (25%)
        grid.getColumnConstraints().addAll(col1, col2, col3);

        int row = 0; // Track current grid row for dynamic layout

        // ===== PHYSICS PROPERTIES SECTION =====
        Label physicsTitle = createSectionTitle("Physics Properties:");
        grid.add(physicsTitle, 0, row, 3, 1);
        row++;

        // ===== ELASTICITY CONTROL =====
        // Controls the coefficient of restitution (0 = perfectly inelastic, 1 = perfectly elastic)
        Label elasticityLabel = new Label("Elasticity:");
        Slider elasticitySlider = new Slider(0, 1, sim.getElasticity());
        elasticitySlider.setShowTickLabels(true);     // Show numeric labels
        elasticitySlider.setShowTickMarks(true);      // Show tick marks
        elasticitySlider.setMajorTickUnit(0.2);       // Major ticks every 0.2
        elasticitySlider.setBlockIncrement(0.1);      // Keyboard increment
        
        // Update simulation elasticity when slider changes
        elasticitySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            sim.setElasticity(newVal.doubleValue());
        });
        
        grid.add(elasticityLabel, 0, row);
        grid.add(elasticitySlider, 1, row, 2, 1);
        row++;

        // ===== MASS CONTROL SECTION =====
        Label massTitle = createSectionTitle("Mass Control:");
        grid.add(massTitle, 0, row, 3, 1);
        row++;

        // ===== PUCK 1 MASS CONTROLS =====
        setupMassControls(grid, row, "Mass 1:", puck1, 1);
        row++;

        // ===== PUCK 2 MASS CONTROLS =====
        setupMassControls(grid, row, "Mass 2:", puck2, 2);
        row++;

        // ===== VELOCITY CONTROL SECTION =====
        Label velocityTitle = createSectionTitle("Velocity Control:");
        grid.add(velocityTitle, 0, row, 3, 1);
        row++;

        // ===== PUCK 1 VELOCITY CONTROLS =====
        row = setupVelocityControls(grid, row, "Puck 1:", 1);

        // ===== PUCK 2 VELOCITY CONTROLS =====
        row = setupVelocityControls(grid, row, "Puck 2:", 2);

        // ===== VELOCITY INPUT INSTRUCTIONS =====
        Label instructionText = new Label("Enter speed (m/s) and angle (degrees). 0° = right, 90° = up, 180° = left, 270° = down");
        instructionText.setFont(Font.font("System", 9));
        instructionText.setTextFill(Color.DARKGRAY);
        instructionText.setWrapText(true);             // Allow text wrapping for long instructions
        grid.add(instructionText, 0, row, 3, 1);
        row++;

        // ===== SIMULATION CONTROL SECTION =====
        Label simulationTitle = createSectionTitle("Simulation Control:");
        grid.add(simulationTitle, 0, row, 3, 1);
        row++;

        // ===== CONTROL BUTTONS =====
        row = setupControlButtons(grid, row);

        // ===== CUSTOMIZATION SECTION =====
        Label customizationTitle = createSectionTitle("Customization:");
        grid.add(customizationTitle, 0, row, 3, 1);
        row++;

        // ===== IMAGE UPLOAD CONTROLS =====
        setupImageUploadControls(grid, row);
        row++;

        // ===== VISUALIZATION SECTION =====
        Label visualizationTitle = createSectionTitle("Visualization:");
        grid.add(visualizationTitle, 0, row, 3, 1);
        row++;

        // ===== MOMENTUM VECTOR DISPLAY CONTROLS =====
        setupVisualizationControls(grid, row);

        // Add the main grid to the container
        getChildren().add(grid);

        // ===== REAL-TIME DATA DISPLAY SECTION =====
        createDataDisplaySection();
        
        // ===== INITIALIZATION =====
        // Set initial velocity field values based on current puck velocities
        updateVelocityFields();
    }

    /**
     * Creates a styled section title label with consistent formatting.
     * 
     * @param text The title text to display
     * @return A formatted Label for section titles
     */
    private Label createSectionTitle(String text) {
        Label title = new Label(text);
        title.setFont(Font.font("System", FontWeight.BOLD, 12));
        title.setTextFill(Color.DARKBLUE);
        return title;
    }

    /**
     * Sets up mass control elements (slider and text field) for a puck.
     * Creates synchronized controls where the slider and text field update each other.
     * 
     * @param grid      The GridPane to add controls to
     * @param row       The current row in the grid
     * @param labelText The label text for this mass control
     * @param puck      The puck whose mass is being controlled
     * @param puckNum   The puck number (1 or 2) for field assignment
     */
    private void setupMassControls(GridPane grid, int row, String labelText, Puck puck, int puckNum) {
        Label massLabel = new Label(labelText);
        
        // Create mass slider with range 1-300
        Slider massSlider = new Slider(1, 300, puck.getMass());
        massSlider.setShowTickLabels(true);
        massSlider.setShowTickMarks(true);
        massSlider.setBlockIncrement(1);
        
        // Create corresponding text field
        TextField massField = new TextField(String.valueOf((int)puck.getMass()));
        massField.setPrefWidth(60);
        massField.setStyle("-fx-font-size: 11px;");
        
        // Store reference to the text field for later use
        if (puckNum == 1) {
            mass1Field = massField;
        } else {
            mass2Field = massField;
        }
        
        // ===== SLIDER TO TEXT FIELD SYNCHRONIZATION =====
        massSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            puck.setMass(newVal.doubleValue());
            massField.setText(String.valueOf(newVal.intValue()));
        });
        
        // ===== TEXT FIELD TO SLIDER SYNCHRONIZATION =====
        massField.setOnAction(e -> updateMassFromField(massField, massSlider, puck));
        
        // Add controls to grid
        grid.add(massLabel, 0, row);
        grid.add(massSlider, 1, row);
        grid.add(massField, 2, row);
    }

    /**
     * Updates puck mass from text field input with validation and bounds checking.
     * 
     * @param massField The text field containing the mass value
     * @param massSlider The corresponding slider to update
     * @param puck The puck whose mass is being updated
     */
    private void updateMassFromField(TextField massField, Slider massSlider, Puck puck) {
        try {
            double value = Double.parseDouble(massField.getText());
            value = Math.max(1, Math.min(300, value));    // Clamp to valid range
            puck.setMass(value);
            massSlider.setValue(value);
            massField.setText(String.valueOf((int)value));
        } catch (NumberFormatException ex) {
            // Reset to current value on invalid input
            massField.setText(String.valueOf((int)puck.getMass()));
        }
    }

    /**
     * Sets up velocity control elements (speed and angle fields) for a puck.
     * Uses polar coordinates (speed + angle) for intuitive velocity input.
     * 
     * @param grid    The GridPane to add controls to
     * @param row     The current row in the grid
     * @param labelText The label text for this velocity control
     * @param puckNum The puck number (1 or 2) for field assignment
     * @return The next available row number
     */
    private int setupVelocityControls(GridPane grid, int row, String labelText, int puckNum) {
        // Puck label
        Label puckLabel = new Label(labelText);
        puckLabel.setFont(Font.font("System", FontWeight.BOLD, 10));
        grid.add(puckLabel, 0, row, 3, 1);
        row++;

        // Create speed and angle input fields
        Label speedLabel = new Label("Speed:");
        TextField velocityField = new TextField("0.0");
        velocityField.setPrefWidth(80);
        velocityField.setStyle("-fx-font-size: 11px;");
        velocityField.setPromptText("m/s");
        
        Label angleLabel = new Label("Angle:");
        TextField angleField = new TextField("0.0");
        angleField.setPrefWidth(80);
        angleField.setStyle("-fx-font-size: 11px;");
        angleField.setPromptText("degrees");
        
        // Store references for later use
        if (puckNum == 1) {
            puck1VelocityField = velocityField;
            puck1AngleField = angleField;
        } else {
            puck2VelocityField = velocityField;
            puck2AngleField = angleField;
        }
        
        // ===== EVENT HANDLERS FOR VELOCITY UPDATES =====
        // Update velocity when field loses focus
        velocityField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (puckNum == 1) applyPuck1Velocity();
                else applyPuck2Velocity();
            }
        });
        
        // Update velocity when Enter is pressed
        velocityField.setOnAction(e -> {
            if (puckNum == 1) applyPuck1Velocity();
            else applyPuck2Velocity();
        });
        
        // Same handlers for angle field
        angleField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                if (puckNum == 1) applyPuck1Velocity();
                else applyPuck2Velocity();
            }
        });
        
        angleField.setOnAction(e -> {
            if (puckNum == 1) applyPuck1Velocity();
            else applyPuck2Velocity();
        });
        
        // Layout the velocity controls horizontally
        HBox velocityBox = new HBox(5);
        velocityBox.getChildren().addAll(speedLabel, velocityField, angleLabel, angleField);
        velocityBox.setAlignment(Pos.CENTER_LEFT);
        grid.add(velocityBox, 0, row, 3, 1);
        row++;
        
        return row;
    }

    /**
     * Sets up the main simulation control buttons (Start, Stop, Reset).
     * 
     * @param grid The GridPane to add controls to
     * @param row  The current row in the grid
     * @return The next available row number
     */
    private int setupControlButtons(GridPane grid, int row) {
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        
        // ===== START BUTTON =====
        Button startBtn = new Button("Start");
        startBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        startBtn.setOnAction(e -> {
            simulation.start();
            startDataUpdates();               // Begin real-time data monitoring
        });
        
        // ===== STOP BUTTON =====
        Button stopBtn = new Button("Stop");
        stopBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        stopBtn.setOnAction(e -> {
            simulation.stop();
            stopDataUpdates();                // Stop real-time data monitoring
        });
        
        // ===== RESET BUTTON =====
        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-background-color: #ff9800; -fx-text-fill: white;");
        resetButton.setOnAction(e -> {
            simulation.reset(puck1, puck2, collisionCount);          // Reset simulation state
        });

        buttonBox.getChildren().addAll(startBtn, stopBtn, resetButton);
        grid.add(buttonBox, 0, row, 3, 1);
        return row + 1;
    }

    /**
     * Sets up image upload controls for customizing puck appearance.
     * 
     * @param grid The GridPane to add controls to
     * @param row  The current row in the grid
     */
    private void setupImageUploadControls(GridPane grid, int row) {
        // ===== PUCK 1 IMAGE UPLOAD =====
        Button uploadImage1Btn = new Button("Upload Image for Puck 1");
        uploadImage1Btn.setOnAction(e -> uploadImageForPuck(puck1, "Puck 1"));

        // ===== PUCK 2 IMAGE UPLOAD =====
        Button uploadImage2Btn = new Button("Upload Image for Puck 2");
        uploadImage2Btn.setOnAction(e -> uploadImageForPuck(puck2, "Puck 2"));

        VBox imageButtonsBox = new VBox(5, uploadImage1Btn, uploadImage2Btn);
        imageButtonsBox.setAlignment(Pos.CENTER_LEFT);
        grid.add(imageButtonsBox, 0, row, 3, 1);
    }

    /**
     * Handles image upload for a specific puck using a file chooser dialog.
     * 
     * @param puck The puck to apply the image to
     * @param puckName The name of the puck for dialog title
     */
    private void uploadImageForPuck(Puck puck, String puckName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image for " + puckName);
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        
        File selectedFile = fileChooser.showOpenDialog(getScene().getWindow());
        if (selectedFile != null) {
            Image img = new Image(selectedFile.toURI().toString());
            puck.setImage(img);
        }
    }

    /**
     * Sets up visualization controls for momentum vector display.
     * 
     * @param grid The GridPane to add controls to
     * @param row  The current row in the grid
     */
    private void setupVisualizationControls(GridPane grid, int row) {
        // ===== MOMENTUM VECTOR DISPLAY CHECKBOXES =====
        showMomentumVectorsCheck = new CheckBox("Show Momentum Vectors");
        showMomentumComponentsCheck = new CheckBox("Show Momentum Components");
        
        // Components checkbox is only enabled when vectors are shown
        showMomentumComponentsCheck.disableProperty().bind(
            showMomentumVectorsCheck.selectedProperty().not()
        );

        VBox checkboxBox = new VBox(5);
        checkboxBox.getChildren().addAll(showMomentumVectorsCheck, showMomentumComponentsCheck);
        grid.add(checkboxBox, 0, row, 3, 1);
    }
    
    /**
     * Applies velocity from input fields to puck 1.
     * Converts from polar coordinates (speed, angle) to Cartesian velocity vector.
     * Handles coordinate system conversion for JavaFX (Y-axis flipped).
     */
    private void applyPuck1Velocity() {
        try {
            double speed1 = Double.parseDouble(puck1VelocityField.getText());
            double angle1 = Math.toRadians(Double.parseDouble(puck1AngleField.getText()));
            
            // Convert polar to Cartesian coordinates
            // Note: Y component is negated due to JavaFX coordinate system (Y increases downward)
            Vector2D velocity1 = new Vector2D(
                speed1 * Math.cos(angle1), 
                -speed1 * Math.sin(angle1)
            );
            puck1.setVelocity(velocity1);
            
            // Update momentum vector display if enabled
            if (simulation != null) {
                simulation.renderMomentumVectors(isShowMomentumVectors(), isShowMomentumComponents());
            }
            
        } catch (NumberFormatException e) {
            // Reset to current values on invalid input
            updatePuck1VelocityFields();
        }
    }
    
    /**
     * Applies velocity from input fields to puck 2.
     * Converts from polar coordinates (speed, angle) to Cartesian velocity vector.
     * Handles coordinate system conversion for JavaFX (Y-axis flipped).
     */
    private void applyPuck2Velocity() {
        try {
            double speed2 = Double.parseDouble(puck2VelocityField.getText());
            double angle2 = Math.toRadians(Double.parseDouble(puck2AngleField.getText()));
            
            // Convert polar to Cartesian coordinates
            // Note: Y component is negated due to JavaFX coordinate system (Y increases downward)
            Vector2D velocity2 = new Vector2D(
                speed2 * Math.cos(angle2), 
                -speed2 * Math.sin(angle2)
            );
            puck2.setVelocity(velocity2);
            
            // Update momentum vector display if enabled
            if (simulation != null) {
                simulation.renderMomentumVectors(isShowMomentumVectors(), isShowMomentumComponents());
            }
            
        } catch (NumberFormatException e) {
            // Reset to current values on invalid input
            updatePuck2VelocityFields();
        }
    }
    
    /**
     * Updates both pucks' velocity input fields to reflect their current velocities.
     * Useful after programmatic velocity changes or resets.
     */
    void updateVelocityFields() {
        updatePuck1VelocityFields();
        updatePuck2VelocityFields();
    }
    
    /**
     * Updates puck 1's velocity input fields based on its current velocity.
     * Converts from Cartesian velocity to polar coordinates for display.
     */
    private void updatePuck1VelocityFields() {
        Vector2D vel1 = puck1.getVelocity();
        double speed1 = vel1.magnitude();
        
        // Convert Cartesian to polar coordinates
        // Note: Y component is negated when calculating angle due to JavaFX coordinate system
        double angle1 = Math.toDegrees(Math.atan2(-vel1.y, vel1.x));
        
        puck1VelocityField.setText(String.format("%.1f", speed1));
        puck1AngleField.setText(String.format("%.1f", angle1));
    }
    
    /**
     * Updates puck 2's velocity input fields based on its current velocity.
     * Converts from Cartesian velocity to polar coordinates for display.
     */
    private void updatePuck2VelocityFields() {
        Vector2D vel2 = puck2.getVelocity();
        double speed2 = vel2.magnitude();
        
        // Convert Cartesian to polar coordinates
        // Note: Y component is negated when calculating angle due to JavaFX coordinate system
        double angle2 = Math.toDegrees(Math.atan2(-vel2.y, vel2.x));
        
        puck2VelocityField.setText(String.format("%.1f", speed2));
        puck2AngleField.setText(String.format("%.1f", angle2));
    }
    
    /**
     * Creates the real-time data display section showing physics quantities.
     * This section monitors conservation laws and individual puck properties.
     * Displays:
     * - Total momentum (X and Y components)
     * - Total kinetic energy
     * - Individual puck speeds and momenta
     * - Collision count
     */
    private void createDataDisplaySection() {
        // ===== MAIN DATA SECTION CONTAINER =====
        VBox dataSection = new VBox(10);
        dataSection.setPadding(new Insets(15));
        dataSection.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 1; -fx-border-radius: 5;");
        
        // ===== SECTION TITLE =====
        Label dataTitle = new Label("📊 Real-Time Physics Data");
        dataTitle.setFont(Font.font("System", FontWeight.BOLD, 14));
        dataTitle.setTextFill(Color.DARKBLUE);
        
        // ===== CONSERVATION LAWS SUBSECTION =====
        Label conservationTitle = new Label("Conservation Laws:");
        conservationTitle.setFont(Font.font("System", FontWeight.BOLD, 12));
        conservationTitle.setTextFill(Color.DARKGREEN);
        
        // Initialize conservation law display labels
        totalMomentumXLabel = new Label("Total Momentum X: 0.0 kg⋅m/s");
        totalMomentumYLabel = new Label("Total Momentum Y: 0.0 kg⋅m/s");
        totalKineticEnergyLabel = new Label("Total Kinetic Energy: 0.0 J");
        
        // ===== INDIVIDUAL PUCK DATA SUBSECTION =====
        Label puckDataTitle = new Label("Individual Puck Data:");
        puckDataTitle.setFont(Font.font("System", FontWeight.BOLD, 12));
        puckDataTitle.setTextFill(Color.DARKORANGE);
        
        // Initialize individual puck display labels
        puck1SpeedLabel = new Label("Puck 1 Speed: 0.0 m/s");
        puck1MomentumLabel = new Label("Puck 1 Momentum: 0.0 kg⋅m/s");
        puck2SpeedLabel = new Label("Puck 2 Speed: 0.0 m/s");
        puck2MomentumLabel = new Label("Puck 2 Momentum: 0.0 kg⋅m/s");
        
        // ===== COLLISION STATISTICS SUBSECTION =====
        Label collisionTitle = new Label("Collision Statistics:");
        collisionTitle.setFont(Font.font("System", FontWeight.BOLD, 12));
        collisionTitle.setTextFill(Color.DARKRED);
        
        // Initialize collision display label
        collisionCountLabel = new Label("Collisions: 0");
        
        // ===== CONSISTENT LABEL STYLING =====
        // Use monospace font for better alignment of numeric data
        String labelStyle = "-fx-font-family: 'Courier New'; -fx-font-size: 11px;";
        totalMomentumXLabel.setStyle(labelStyle);
        totalMomentumYLabel.setStyle(labelStyle);
        totalKineticEnergyLabel.setStyle(labelStyle);
        puck1SpeedLabel.setStyle(labelStyle);
        puck1MomentumLabel.setStyle(labelStyle);
        puck2SpeedLabel.setStyle(labelStyle);
        puck2MomentumLabel.setStyle(labelStyle);
        collisionCountLabel.setStyle(labelStyle);
        
        // ===== ASSEMBLE DATA SECTION =====
        dataSection.getChildren().addAll(
            dataTitle,
            new Separator(),                    // Visual separator
            conservationTitle,
            totalMomentumXLabel,
            totalMomentumYLabel,
            totalKineticEnergyLabel,
            new Separator(),
            puckDataTitle,
            puck1SpeedLabel,
            puck1MomentumLabel,
            puck2SpeedLabel,
            puck2MomentumLabel,
            new Separator(),
            collisionTitle,
            collisionCountLabel
        );
        
        // Add data section to main control panel
        getChildren().add(dataSection);
    }
    
    /**
     * Starts the real-time data update timer.
     * This timer continuously updates the physics data display during simulation.
     * Stops any existing timer before starting a new one to prevent conflicts.
     */
    private void startDataUpdates() {
        // Stop existing timer if running
        if (dataUpdateTimer != null) {
            dataUpdateTimer.stop();
        }
        
        // Create and start new animation timer
        dataUpdateTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateDataDisplay();
            }
        };
        dataUpdateTimer.start();
    }
    
    /**
     * Stops the real-time data update timer.
     * Called when the simulation is paused or stopped.
     */
    private void stopDataUpdates() {
        if (dataUpdateTimer != null) {
            dataUpdateTimer.stop();
        }
    }
    
    /**
     * Updates all real-time physics data displays.
     * Calculates and displays:
     * - Conservation quantities (momentum, kinetic energy)
     * - Individual puck properties (speed, momentum)
     * - Simulation statistics (collision count)
     */
    private void updateDataDisplay() {
        // Calculate total momentum
        Vector2D momentum1 = puck1.getVelocity().multiply(puck1.getMass());
        Vector2D momentum2 = puck2.getVelocity().multiply(puck2.getMass());
        double totalMomX = momentum1.x + momentum2.x;
        double totalMomY = momentum1.y + momentum2.y;
        
        // Calculate total kinetic energy
        double ke1 = 0.5 * puck1.getMass() * puck1.getVelocity().magnitudeSquared();
        double ke2 = 0.5 * puck2.getMass() * puck2.getVelocity().magnitudeSquared();
        double totalKE = ke1 + ke2;
        
        // Calculate speeds
        double speed1 = puck1.getVelocity().magnitude();
        double speed2 = puck2.getVelocity().magnitude();
        double momentum1Mag = momentum1.magnitude();
        double momentum2Mag = momentum2.magnitude();
        
        // Update labels with formatted values
        totalMomentumXLabel.setText(String.format("Total Momentum X: %.1f kg⋅m/s", totalMomX));
        totalMomentumYLabel.setText(String.format("Total Momentum Y: %.1f kg⋅m/s", totalMomY));
        totalKineticEnergyLabel.setText(String.format("Total Kinetic Energy: %.1f J", totalKE));
        
        puck1SpeedLabel.setText(String.format("Puck 1 Speed: %.1f m/s", speed1));
        puck1MomentumLabel.setText(String.format("Puck 1 Momentum: %.1f kg⋅m/s", momentum1Mag));
        puck2SpeedLabel.setText(String.format("Puck 2 Speed: %.1f m/s", speed2));
        puck2MomentumLabel.setText(String.format("Puck 2 Momentum: %.1f kg⋅m/s", momentum2Mag));
        
        collisionCountLabel.setText(String.format("Collisions: %d", collisionCount));
    }
    
    public void incrementCollisionCount() {
        collisionCount++;
    }
    
    public boolean isShowMomentumVectors() {
        return showMomentumVectorsCheck.isSelected();
    }

    public boolean isShowMomentumComponents() {
        return showMomentumComponentsCheck.isSelected();
    }

    // In your ControlPanel class, you need:
    public void resetCollisionCount() {
        this.collisionCount = 0;  // Reset the actual field
        collisionCountLabel.setText("Collisions: 0"); // Update the label display
        }
}
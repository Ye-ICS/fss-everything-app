package com.fss.everythingapp.simulations.projectilemotion;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectileController implements Initializable {
    @FXML
    private Canvas gridCanvas;
    @FXML private Slider speedSlider;
    @FXML private Label speedLabel;
    @FXML private Slider angleSlider;
    @FXML private Label angleLabel;
    @FXML private Slider heightSlider;
    @FXML private Label heightLabel;
    @FXML private Slider accelerationSlider;
    @FXML private Label accelerationLabel;
    @FXML private Slider vectorSlider;
    @FXML private Label vectorLabel;
    @FXML private Slider animationSpeedSlider;
    @FXML private Label animationSpeedLabel;
    @FXML private CheckBox grid;
    @FXML private CheckBox positionVectors;
    @FXML private CheckBox velocityVectors;
    @FXML private CheckBox velocityComponents;
    @FXML private TextField projectileName;
    @FXML private Button projectileNameButton;
    @FXML private Button launch;

    private ProjectileMotion simulation = new ProjectileMotion();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        simulation.setInitialVelocity(speedSlider.getValue());
        simulation.setInitialAngle(angleSlider.getValue());
        simulation.setHeight(heightSlider.getValue());
        simulation.setAcceleration(accelerationSlider.getValue());
        simulation.calculateTime();
        simulation.calculateRange();

        speedSlider.valueProperty().addListener((abs, oldVal, newVal) -> {
            speedLabel.setText(String.format("%.1f m/s", newVal.doubleValue()));
            simulation.setInitialVelocity(newVal.doubleValue());
            simulation.calculateTime();
            simulation.calculateRange();
            // Update logic later
        });
        angleSlider.valueProperty().addListener((abs, oldVal, newVal) -> {
            angleLabel.setText(String.format("%.1f°", newVal.doubleValue()));
            simulation.setInitialAngle(newVal.doubleValue());
            simulation.calculateTime();
            simulation.calculateRange();
        });
        heightSlider.valueProperty().addListener((abs, oldVal, newVal) -> {
            heightLabel.setText(String.format("%.1f m", newVal.doubleValue()));
            simulation.setHeight(newVal.doubleValue());
            simulation.calculateTime();
            simulation.calculateRange();

            //draw the projectile at height
            drawProjectile(50, 470 - 3.8*heightSlider.getValue()); // Initial drawing of the projectile
            // Update logic later
        });
        accelerationSlider.valueProperty().addListener((abs, oldVal, newVal) -> {
            accelerationLabel.setText(String.format("%.1f m/s²", newVal.doubleValue()));
            simulation.setAcceleration(newVal.doubleValue());
            simulation.calculateTime();
            simulation.calculateRange();
            // Update logic later
        });
        vectorSlider.valueProperty().addListener((abs, oldVal, newVal) -> { //Visual
            vectorLabel.setText(String.format("%.1f ", newVal.doubleValue()));
            // Update logic later
        });
        animationSpeedSlider.valueProperty().addListener((abs, oldVal, newVal) -> //Visual
            animationSpeedLabel.setText(String.format("x %.1f", newVal.doubleValue()))
            // Update logic later
            );

        drawAxes();

        projectileNameButton.setOnAction(e -> {
            if (projectileName != null) {
                nameProjectile();
            }
        });

        launch.setOnAction(e -> {
            shootProjectile();
        });

        if (grid.isSelected()){drawGrid();}
        if (positionVectors.isSelected()){drawPositionVectors();}
        if (velocityVectors.isSelected()){drawVelocityVectors();}
        if (velocityComponents.isSelected()){drawVelocityComponents();}
    }

    public void drawAxes() {
        GraphicsContext gc = gridCanvas.getGraphicsContext2D();
        double width = gridCanvas.getWidth();
        double height = gridCanvas.getHeight();
        
        gc.clearRect(0, 0, width, height);
        
        gc.setStroke(javafx.scene.paint.Color.BLACK);
        gc.setLineWidth(1.5);
        
        //Draw X axis
        gc.strokeLine(50, height - 500, width - 50, height - 500);
        
        //Draw Y axis
        gc.strokeLine(50, height - 500, 50, 50);
        
        double arrowSize = 5;

        // Draw X axis arrow
        double[] xArrowX = {width - 50, width - 50 - arrowSize, width - 50 - arrowSize};
        double[] xArrowY = {height - 50, height - 50 - arrowSize, height - 50 + arrowSize};
        gc.fillPolygon(xArrowX, xArrowY, 3);
        
        // Draw Y axis arrow
        double[] yArrowX = {50 - arrowSize, 50 + arrowSize, 50};
        double[] yArrowY = {50 + arrowSize, 50 + arrowSize, 50};
        gc.fillPolygon(yArrowX, yArrowY, 3);
    }

    public void drawProjectile(double x, double y) {
        GraphicsContext gc = gridCanvas.getGraphicsContext2D();
        drawAxes(); //to clear the canvas before redrawing
        gc.setFill(Color.BLACK);
        gc.fillOval(x, y, 30, 30); //Drawing circle at 
    }

    public void drawGrid(){
        // In progress
    }
    public void drawPositionVectors(){
        // In progress
    }
    public void drawVelocityVectors(){
        // In progress
    }
    public void drawVelocityComponents(){
        // In progress
    }
    public void nameProjectile(){
        System.out.println(projectileName.getText());
        // In progress
    }
    public void shootProjectile() {
        /*
         * Originally we (maxim and masa) were going to use a while loop, then thread.sleep for each thing
         * for some reason though, it didn't work, so alternatively we are using a timeline
         * This SHOULD create a smooth animation of the projec motion
         */

        javafx.animation.Timeline timeline = new javafx.animation.Timeline();
        double totalTime = simulation.calculateTime(); //Get total time from projectile motion simulation
        double dt = 0.1; //How often the projectile is updated
        int frames = (int) (totalTime / dt); //calcs how many frames will be in this animation
        for (int i = 0; i <= frames; i++) { 
            double time = i * dt;
            javafx.animation.KeyFrame keyFrame = new javafx.animation.KeyFrame( //Creates a keyframe for each frame
                javafx.util.Duration.millis(i * (10 / animationSpeedSlider.getValue())), //Duration of each frame, multiplied by the animation speed slider value
                event -> { 
                    double x = simulation.launchProjectile(heightSlider.getValue(), speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time).getX();
                    double y = simulation.launchProjectile(heightSlider.getValue(), speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time).getY();
                        if (y <= 0 && time > 0) {
                            timeline.stop();
                            drawProjectile(50 + x, 470); 
                            return;
                        }
                    drawProjectile(50 + x, 470 - y);
                }
            );
            timeline.getKeyFrames().add(keyFrame); // adds the keyframe to the timeline to thenn be plaued
        }
        timeline.play(); // Starts the animation timeline
    }
}

    
   
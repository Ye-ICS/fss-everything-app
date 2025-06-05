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
    @FXML private Label xVeloLabel;
    @FXML private Label yVeloLabel;
    @FXML private Label xLabel;
    @FXML private Label yLabel;
    @FXML private Label timeLabel;
    @FXML private Label veloLabel;
    @FXML private Label ballSizeLabel;
    @FXML private Slider ballSlider;


    private ProjectileMotion simulation = new ProjectileMotion();
    private double ballSize = 30;
    private double height = 0;

    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        simulation.setInitialVelocity(speedSlider.getValue());
        simulation.setInitialAngle(angleSlider.getValue());
        simulation.setHeight(height);
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
            simulation.setHeight(height);
            simulation.calculateTime();
            simulation.calculateRange();
            height = (9*heightSlider.getValue());
            System.out.println(height);
            

            //draw the projectile at height
            drawProjectile(50,  470 - height); // Initial drawing of the projectile
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

        ballSlider.valueProperty().addListener((abs, oldVal, newVal ) -> {
            ballSizeLabel.setText(String.format("%.1f u", newVal.doubleValue()));
            ballSize = ballSlider.getValue();
            drawProjectile(50, 470 - height);
    });

        //xVeloLabel.setText("Vₓ = " + ProjectileMotion.getVelocity(heightSlider.getValue(), speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), ProjectileMotion.calculateTime()).getX());
       // yVeloLabel.setText("Vᵧ = " + ProjectileMotion.getVelocity(heightSlider.getValue(), speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), ProjectileMotion.calculateTime()).getY());

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

        gc.setFill(Color.LIGHTGREY);
        gc.fillRect(0, 0, width, gridCanvas.getHeight());

        //Draw X axis
        gc.strokeLine(50, height - 100, width - 50, height - 100);
        
        //Draw Y axis
        gc.strokeLine(50, height - 100, 50, 50);
    }

    public void drawProjectile(double x, double y) {
        GraphicsContext gc = gridCanvas.getGraphicsContext2D();
        drawAxes(); //to clear the canvas before redrawing
        gc.setFill(Color.BLACK);
        gc.fillOval(x, y + 483, ballSize, ballSize); //Drawing circle at 
        System.out.println(height);
    }

    public void drawGrid(){
        // In progress
    }
    public void drawPositionVectors(){
        // In progress
    }
    public void drawVelocityVectors(){
        // In progressP

    }
    public void drawVelocityComponents(){
        // In progress
    }
    public void nameProjectile(){
        //System.out.println(projectileName.getText());
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
                    double x = simulation.launchProjectile(height, speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time).getX();
                    double y = simulation.launchProjectile(height, speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time).getY();
                        if (y - ballSize + 30 <= 0 && time > 0) {
                            timeline.stop();
                            drawProjectile(50 + x, 470 - ballSize + 30); 
                            return;
                        }
                    drawProjectile(50 + x, 470 - y);
                    xVeloLabel.setText("Vₓ = " + String.format("%.2f" , ProjectileMotion.getVelocity(height, speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time).getX()));
                    yVeloLabel.setText("Vᵧ = " + String.format("%.2f" , ProjectileMotion.getVelocity(height, speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time).getY()));
                    xLabel.setText("X = " + String.format("%.2f" , x));
                    yLabel.setText("Y = " + String.format("%.2f", y));
                    timeLabel.setText("t = " + String.format("%.1f", time));
                    veloLabel.setText("V = " + String.format("%.2f", Math.sqrt(Math.pow(ProjectileMotion.getVelocity(height, speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time).getX() , 2)) + Math.pow(ProjectileMotion.getVelocity(heightSlider.getValue(), speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time).getY() , 2)));
                }
            );
            timeline.getKeyFrames().add(keyFrame); // adds the keyframe to the timeline to thenn be plaued
        }
        timeline.play(); // Starts the animation timeline
    }
}

    
   
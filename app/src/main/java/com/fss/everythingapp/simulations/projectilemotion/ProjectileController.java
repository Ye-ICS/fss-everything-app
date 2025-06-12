package com.fss.everythingapp.simulations.projectilemotion;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;


public class ProjectileController {
    @FXML
    private Parent rootContainer;
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
    @FXML private Label vectorLabel;
    @FXML private Slider animationSpeedSlider;
    @FXML private Label animationSpeedLabel;
    @FXML private CheckBox grid;
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
    @FXML private Button changeProjectileImage;
    @FXML private Button backToSim;


    private ProjectileMotion simulation = new ProjectileMotion();
    private double ballSize = 30;
    private double height = 30;
    private Image projectileImg = null;
    private double XOffset = 50;
    private double YOffset = 500;
    private double offSet = 485;
    private double tickLength = 5;



    @FXML
    public void initialize() {
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
            drawProjectile(XOffset, YOffset-height);
        });
        angleSlider.valueProperty().addListener((abs, oldVal, newVal) -> {
            angleLabel.setText(String.format("%.1f°", newVal.doubleValue()));
            simulation.setInitialAngle(newVal.doubleValue());
            simulation.calculateTime();
            simulation.calculateRange();
            drawAxes();
            drawProjectile(XOffset, YOffset - height);

        });
        heightSlider.valueProperty().addListener((abs, oldVal, newVal) -> {
            heightLabel.setText(String.format("%.1f m", newVal.doubleValue()));
            simulation.setHeight(height);
            simulation.calculateTime();
            simulation.calculateRange();
            height = (9 * heightSlider.getValue()) + ballSize;

            //draw the projectile at height
            drawProjectile(XOffset,  YOffset - height); // Initial drawing of the projectile
        });
        accelerationSlider.valueProperty().addListener((abs, oldVal, newVal) -> {
            accelerationLabel.setText(String.format("%.1f m/s²", newVal.doubleValue()));
            simulation.setAcceleration(newVal.doubleValue());
            simulation.calculateTime();
            simulation.calculateRange();
            drawProjectile(XOffset, YOffset - height);
        });
        animationSpeedSlider.valueProperty().addListener((abs, oldVal, newVal) -> //Visual
            animationSpeedLabel.setText(String.format("x %.1f", newVal.doubleValue()))

            );

        ballSlider.valueProperty().addListener((abs, oldVal, newVal ) -> {
            ballSizeLabel.setText(String.format("%.1f u", newVal.doubleValue()));
            ballSize = ballSlider.getValue();
            height = (9 * heightSlider.getValue()) + ballSize;
            drawProjectile(XOffset, YOffset - height);
    });

        velocityVectors.setOnAction(e -> {
            drawProjectile(XOffset, YOffset-height);
        });
        velocityComponents.setOnAction(e -> {
            drawProjectile(XOffset, YOffset-height);
        });



        projectileNameButton.setOnAction(e -> {
            if (projectileName != null) {
                nameProjectile();
            }
        });

        launch.setOnAction(e -> {
            shootProjectile();
        });

        backToSim.setOnAction(e -> {

           
            // Go back to the simulations menu
            // Find the scene and set the root to the simulations menu FXML
            try {
                javafx.scene.Scene scene = backToSim.getScene();
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/com/fss/everythingapp/simulations/SimulationsMenu.fxml")
                );
                javafx.scene.Parent menuRoot = loader.load();
                scene.setRoot(menuRoot);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
   
        });



        changeProjectileImage.setOnAction(e -> { // I copied this code from Yamen's "ControlPanel.java"
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image for projectile");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(gridCanvas.getScene().getWindow());
            if (selectedFile != null) {
                projectileImg = new Image(selectedFile.toURI().toString());
                drawProjectile(XOffset, YOffset - height);
            }
        });

                drawProjectile(XOffset, YOffset - height); //initial drawing 
    }

    public void drawAxes() {
        GraphicsContext gc = gridCanvas.getGraphicsContext2D();
        double width = gridCanvas.getWidth();
        double height = gridCanvas.getHeight();

        gc.clearRect(0, 0, width, height); // Clears the canvas
        
        gc.setStroke(javafx.scene.paint.Color.BLACK);
        gc.setLineWidth(1.5);

        gc.setFill(Color.LIGHTGREY);
        gc.fillRect(0, 0, width, gridCanvas.getHeight());

        //Draw X axis
        gc.strokeLine(XOffset, height - XOffset * 2, width - XOffset, height - XOffset * 2);
        
        //Draw Y axis
        gc.strokeLine(XOffset, height - XOffset * 2, XOffset, XOffset);

        for (int i = (int)XOffset; i < width - XOffset * 2; i += XOffset){ //draws x axis ticks
            gc.strokeLine(i, height - XOffset*2 + tickLength, i, height - XOffset*2 - tickLength); //draws ticks, ticklength to left, ticklength to right
        }

        for (int i = (int)XOffset; i < height - XOffset * 2; i += XOffset){  //draws y axis ticks
            gc.strokeLine(XOffset - tickLength, i, XOffset + tickLength, i );
        }
    }

    public void drawProjectile(double x, double y) {
        GraphicsContext gc = gridCanvas.getGraphicsContext2D();
        drawAxes(); //to clear the canvas before redrawing

        if (projectileImg != null) { // Also borrowed this code from Yamen's "Puck.java"
            gc.setFill(new ImagePattern(this.projectileImg, 0, 0, 1, 1, true));
            gc.fillOval(x, y + offSet, ballSize, ballSize);
        } else {
            gc.setFill(Color.BLACK);
            gc.fillOval(x, y + offSet, ballSize, ballSize); //Drawing circle at 
        }
                if(velocityVectors.isSelected() && x == 50){ //if ball is on left wall then draw vectors (if selecgted)
                drawVelocityVectors(0, height - ballSize, 0);

                }
                System.out.println(x);
    }

    public void drawVelocityVectors(double x, double y, double time){
        GraphicsContext gc = gridCanvas.getGraphicsContext2D();
 
        if(velocityVectors.isSelected())
        {
        //OverallVelocity
        gc.setStroke(javafx.scene.paint.Color.RED);
        gc.setLineWidth(3);
        gc.strokeLine(x + XOffset + ballSize/2, offSet + YOffset - y - ballSize/2, XOffset + ballSize/2 + simulation.launchProjectile(height, speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time + 3).getX(), offSet + YOffset - ballSize/2 - simulation.launchProjectile(height, speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time + 3).getY());

        }

        if(velocityComponents.isSelected())
        {
        //YVelocity
        gc.setStroke(javafx.scene.paint.Color.BLUE);
        gc.setLineWidth(3);
        gc.strokeLine(x + XOffset + ballSize/2, offSet + YOffset - y - ballSize/2,x + XOffset + ballSize/2, offSet + YOffset - ballSize/2 - simulation.launchProjectile(height, speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time + 3).getY());

        //XVelocity
        gc.setStroke(javafx.scene.paint.Color.GREEN);
        gc.setLineWidth(3);
        gc.strokeLine(x + XOffset + ballSize/2, offSet + YOffset - y - ballSize/2, 65 + ballSize/2 + simulation.launchProjectile(height, speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time + 3).getX() , offSet + YOffset - y - ballSize/2);
        }
    }

    public void nameProjectile(){
        launch.setText("Launch " + projectileName.getText());
        // Add name that follows the projectile 
    }
    public void shootProjectile() {
        /*
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
                        if (y - ballSize <= 0 && time > 0) {
                            timeline.stop();
                            drawProjectile(XOffset + x, YOffset - ballSize ); 
                            return;
                        }
                    drawProjectile(XOffset + x, YOffset - y);
                            if(velocityVectors.isSelected())
                            {
                                drawVelocityVectors(x, y - ballSize, time);
                            }
                    xVeloLabel.setText("Vₓ = " + String.format("%.2f" , speedSlider.getValue() * Math.cos(Math.toRadians(angleSlider.getValue())))+ " m/s");
                    yVeloLabel.setText("Vᵧ = " + String.format("%.2f" , ProjectileMotion.getVelocity(height, speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time).getY()) + " m/s");
                    xLabel.setText("X = " + String.format("%.2f" , x) + " m");
                    yLabel.setText("Y = " + String.format("%.2f", y - ballSize) + " m");
                    timeLabel.setText("t = " + String.format("%.1f", time) + " s");
                    veloLabel.setText("V = " + String.format("%.2f", Math.sqrt(Math.pow(ProjectileMotion.getVelocity(height, speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time).getX() , 2) + Math.pow(ProjectileMotion.getVelocity(height, speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), time).getY() , 2))) + " m/s");
                }
            );
            timeline.getKeyFrames().add(keyFrame); // adds the keyframe to the timeline to thenn be plaued
        }
        timeline.play(); // Starts the animation timeline
    }
}
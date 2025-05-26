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
import javafx.scene.shape.Circle;

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
        gc.setFill(Color.RED);
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
        for(int i = 0; i < 3; i += 0.1)
        {
            double x = simulation.launchProjectile(heightSlider.getValue(), speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), i).getX();
            double y = simulation.launchProjectile(heightSlider.getValue(), speedSlider.getValue(), accelerationSlider.getValue(), angleSlider.getValue(), i).getY();
            drawProjectile(50 + x, 470 - y); // Adjusted for canvas origin
        }
        }
}

    
   
package com.fss.everythingapp.simulations.projectilemotion;

import com.fss.everythingapp.simulations.CoordinateSystem;
import com.fss.everythingapp.simulations.KinematicObject;
import com.fss.everythingapp.simulations.Vector2D;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

import java.util.List;

public class ProjectileMotionController {
    @FXML private Canvas canvas;
    @FXML private Slider angleSlider;
    @FXML private Slider speedSlider;
    @FXML private Slider gravitySlider;
    @FXML private Label angleValue;
    @FXML private Label speedValue;
    @FXML private Label gravityValue;
    @FXML private Button launchButton;
    @FXML private Button resetButton;

    private ProjectileMotion projectileMotion;
    private CoordinateSystem coordinateSystem;
    private AnimationTimer timer;
    private long lastTime = 0;

    @FXML
    public void initialize() {
        projectileMotion = new ProjectileMotion();
        coordinateSystem = new CoordinateSystem(canvas.getWidth(), canvas.getHeight(), 30); // 30 px/unit
        setupUIBindings();
        setupAnimation();
    }

    private void setupUIBindings() {
        angleSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            angleValue.setText(String.format("%.0f", newVal.doubleValue()));
            projectileMotion.setLaunchAngle(newVal.doubleValue());
        });
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            speedValue.setText(String.format("%.0f", newVal.doubleValue()));
            projectileMotion.setLaunchSpeed(newVal.doubleValue());
        });
        gravitySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            gravityValue.setText(String.format("%.1f", newVal.doubleValue()));
            projectileMotion.setGravity(new Vector2D(0, newVal.doubleValue()));
        });
        launchButton.setOnAction(e -> launchProjectile());
        resetButton.setOnAction(e -> resetSimulation());
        // Set initial values
        projectileMotion.setLaunchAngle(angleSlider.getValue());
        projectileMotion.setLaunchSpeed(speedSlider.getValue());
        projectileMotion.setGravity(new Vector2D(0, gravitySlider.getValue()));
    }

    private void setupAnimation() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastTime > 0) {
                    double dt = (now - lastTime) / 1_000_000_000.0;
                    projectileMotion.update(dt);
                    draw();
                }
                lastTime = now;
            }
        };
        timer.start();
    }

    private void launchProjectile() {
        // Launch from bottom left (simulation coords)
        double startX = -coordinateSystem.getScreenWidth() / (2 * coordinateSystem.getPixelsPerUnit()) + 1;
        double startY = 0.5; // just above ground
        projectileMotion.launchProjectile(new Vector2D(startX, startY));
    }

    private void resetSimulation() {
        projectileMotion.reset();
        draw();
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // Draw ground
        gc.setStroke(Color.DARKGRAY);
        double groundY = coordinateSystem.toScreenY(0);
        gc.strokeLine(0, groundY, canvas.getWidth(), groundY);
        // Draw projectiles
        List<KinematicObject> projectiles = projectileMotion.getProjectiles();
        gc.setFill(Color.RED);
        for (KinematicObject obj : projectiles) {
            double x = coordinateSystem.toScreenX(obj.position.x);
            double y = coordinateSystem.toScreenY(obj.position.y);
            double r = obj.radius * coordinateSystem.getPixelsPerUnit();
            gc.fillOval(x - r, y - r, r * 2, r * 2);
        }
    }
}

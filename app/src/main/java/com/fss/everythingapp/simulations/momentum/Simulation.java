package com.fss.everythingapp.simulations.momentum;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * The Simulation class manages the physics and rendering of pucks,
 * including collision detection, response, and visual momentum vectors.
 */
public class Simulation {
    private final List<Puck> pucks = new ArrayList<>();
    private double elasticity = 1.0;
    private boolean isRunning = false;
    private AnimationTimer timer;

    public double width;
    public double height;

    private final Group vectorGroup = new Group();
    private ControlPanel controlPanel; // Optional UI controller

    public Simulation(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // --- Public Methods ---

    public void addPuck(Puck puck) {
        pucks.add(puck);
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Group getVectorGroup() {
        return vectorGroup;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public void setElasticity(double e) {
        this.elasticity = e;
    }

    public double getElasticity() {
        return elasticity;
    }

    public Puck getPuck(int index) {
        if (index >= 0 && index < pucks.size())
            return pucks.get(index);
        return null;
    }

/**
 * Starts the simulation by initializing and running an AnimationTimer.
 * This timer continuously calls the update method with the time delta (dt)
 * between frames, allowing for smooth, frame-rate-independent animation.
 */
public void start() {
    // If the simulation is already running, do nothing
    if (isRunning)
        return;

    // Create a new AnimationTimer instance
    timer = new AnimationTimer() {
        // Stores the timestamp of the last frame in nanoseconds
        private long lastUpdate = -1;

        /**
         * This method is called approximately 60 times per second.
         * 'now' is the timestamp of the current frame in nanoseconds.
         */
        @Override
        public void handle(long now) {
            // Only run update logic if we have a previous timestamp
            if (lastUpdate > 0) {
                // Calculate the time difference in seconds between frames
                double dt = (now - lastUpdate) / 1e9;
                // Update the simulation using the time delta
                update(dt);
            }
            // Store the current time for the next frame
            lastUpdate = now;
        }
    };

    // Start the timer to begin calling handle() every frame
    timer.start();

    // Mark the simulation as running
    isRunning = true;
}


    public void stop() {
        if (timer != null)
            timer.stop();
        isRunning = false;
    }

    public void reset() {
        stop();
    }

    /**
     * Updates the positions of pucks, handles wall and puck-to-puck collisions,
     * and redraws momentum vectors if enabled.
     */
    public void update(double dt) {
        // Update puck positions and handle wall collisions
        for (Puck puck : pucks) {
            puck.updatePosition(dt);

            double radius = puck.getRadius();
            Vector2D pos = puck.getPosition();
            Vector2D vel = puck.getVelocity();

            // X wall collisions
            if (pos.x - radius <= 0) {
                pos = new Vector2D(radius, pos.y);
                vel = new Vector2D(Math.abs(vel.x) * elasticity, vel.y);
            } else if (pos.x + radius >= width) {
                pos = new Vector2D(width - radius, pos.y);
                vel = new Vector2D(-Math.abs(vel.x) * elasticity, vel.y);
            }

            // Y wall collisions
            if (pos.y - radius <= 0) {
                pos = new Vector2D(pos.x, radius);
                vel = new Vector2D(vel.x, Math.abs(vel.y) * elasticity);
            } else if (pos.y + radius >= height) {
                pos = new Vector2D(pos.x, height - radius);
                vel = new Vector2D(vel.x, -Math.abs(vel.y) * elasticity);
            }

            puck.setPosition(pos);
            puck.setVelocity(vel);
        }

        // Puck-to-puck collisions
        for (int i = 0; i < pucks.size(); i++) {
            for (int j = i + 1; j < pucks.size(); j++) {
                boolean collided = checkAndResolveCollision(pucks.get(i), pucks.get(j));
                if (collided && controlPanel != null) {
                    controlPanel.incrementCollisionCount();
                }
            }
        }

        // Update momentum vectors
        if (controlPanel != null) {
            renderMomentumVectors(controlPanel.isShowMomentumVectors(), controlPanel.isShowMomentumComponents());
        }
    }

    /**
     * Renders momentum vectors and their components.
     * Green = total momentum, Red = x-component, Blue = y-component
     */
    public void renderMomentumVectors(boolean showVectors, boolean showComponents) {
        vectorGroup.getChildren().clear();
        if (!showVectors)
            return;

        for (Puck puck : pucks) {
            Vector2D pos = puck.getPosition();
            Vector2D momentum = puck.getVelocity().multiply(puck.getMass());
            double scale = Math.min(0.15, 25.0 / puck.getMass());

            // Main momentum vector
            Line vectorLine = new Line(pos.x, pos.y,
                    pos.x + momentum.x * scale, pos.y + momentum.y * scale);
            vectorLine.setStroke(Color.GREEN);
            vectorLine.setStrokeWidth(2);
            vectorGroup.getChildren().add(vectorLine);

            if (showComponents) {
                // X component
                Line xComp = new Line(pos.x, pos.y, pos.x + momentum.x * scale, pos.y);
                xComp.setStroke(Color.RED);
                xComp.getStrokeDashArray().addAll(5.0, 5.0);

                // Y component
                Line yComp = new Line(pos.x, pos.y, pos.x, pos.y + momentum.y * scale);
                yComp.setStroke(Color.BLUE);
                yComp.getStrokeDashArray().addAll(5.0, 5.0);

                vectorGroup.getChildren().addAll(xComp, yComp);
            }
        }
    }

    /**
     * Handles elastic collisions between two pucks, updating their velocities and
     * correcting overlap.
     */
    private boolean checkAndResolveCollision(Puck p1, Puck p2) {
        Vector2D pos1 = p1.getPosition();
        Vector2D pos2 = p2.getPosition();
        Vector2D delta = Vector2D.subtract(pos2, pos1);
        double distance = delta.magnitude();
        double minDist = p1.getRadius() + p2.getRadius();

        if (distance < minDist && distance > 0) {
            Vector2D normal = delta.multiply(1.0 / distance);
            Vector2D relVel = Vector2D.subtract(p2.getVelocity(), p1.getVelocity());
            double velAlongNormal = relVel.dot(normal);

            if (velAlongNormal > 0)
                return false;

            double m1 = p1.getMass(), m2 = p2.getMass();
            double impulseMag = -(1 + elasticity) * velAlongNormal / (1.0 / m1 + 1.0 / m2);
            Vector2D impulse = normal.multiply(impulseMag);

            p1.setVelocity(Vector2D.subtract(p1.getVelocity(), impulse.multiply(1.0 / m1)));
            p2.setVelocity(p2.getVelocity().add(impulse.multiply(1.0 / m2)));

            // Positional correction to resolve overlap
            double overlap = minDist - distance + 1e-6;
            double totalInvMass = 1.0 / m1 + 1.0 / m2;
            Vector2D correction = normal.multiply(overlap / totalInvMass);

            p1.setPosition(Vector2D.subtract(pos1, correction.multiply(1.0 / m1)));
            p2.setPosition(pos2.add(correction.multiply(1.0 / m2)));

            return true;
        }

        return false;
    }

    /**
     * Enables dragging of a puck's position (left click) or velocity (right click).
     */
    @SuppressWarnings("unused")
    public void setupPuckDrag(Circle circle, Puck puck) {
        final double[] startMouseX = new double[1];
        final double[] startMouseY = new double[1];
        final boolean[] isDraggingVelocity = new boolean[1];

        circle.setOnMousePressed(event -> {
            startMouseX[0] = event.getX();
            startMouseY[0] = event.getY();
            isDraggingVelocity[0] = event.isSecondaryButtonDown();

            if (!isRunning) {
                circle.setOpacity(isDraggingVelocity[0] ? 0.9 : 0.7);
            }
        });

        circle.setOnMouseDragged(event -> {
            if (isDraggingVelocity[0]) {
                // Right-click: set velocity vector
                double dx = event.getX() - startMouseX[0];
                double dy = event.getY() - startMouseY[0];
                double velocityScale = 3.0;
                puck.setVelocity(new Vector2D(dx * velocityScale, dy * velocityScale));
            } else if (!isRunning) {
                // Left-click: move puck
                double x = Math.max(puck.getRadius(), Math.min(width - puck.getRadius(), event.getX()));
                double y = Math.max(puck.getRadius(), Math.min(height - puck.getRadius(), event.getY()));
                puck.setPosition(new Vector2D(x, y));
                circle.setCenterX(x);
                circle.setCenterY(y);
            }

            if (controlPanel != null) {
                renderMomentumVectors(controlPanel.isShowMomentumVectors(), controlPanel.isShowMomentumComponents());
            }
        });

        circle.setOnMouseReleased(event -> {
            isDraggingVelocity[0] = false;
            if (!isRunning)
                circle.setOpacity(1.0);
        });
    }
}

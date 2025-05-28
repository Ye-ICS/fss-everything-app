package com.fss.everythingapp.simulations.momentum;

import javafx.animation.AnimationTimer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;


public class Simulation{
    private final List<Puck> pucks = new ArrayList<>();
    private double elasticity = 1.0;
    private boolean isRunning = false;
    private AnimationTimer timer;
    private double width;
    private double height;

    public Simulation(double width, double height) {
    this.width = width;
    this.height = height;
}

    public void addPuck(Puck puck) {
        pucks.add(puck);
    }

public void start() {
    if (isRunning) {
        System.out.println("Simulation is already running");
        return;
    }
    // Create a new AnimationTimer which runs code on every frame (~60 FPS)
    timer = new AnimationTimer() {
        private long lastUpdate = -1;// Stores the time of the last frame update (in nanoseconds)
        public void handle(long now) {
            // Only run update logic if this is not the first frame
            if (lastUpdate > 0) {
                double dt = (now - lastUpdate) / 1e9;// Calculate time elapsed since last frame (in seconds)
                update(dt);// Call the simulation update method with the time delta
            }
            // Update lastUpdate with the current time for the next frame
            lastUpdate = now;
        }
    };
    timer.start();// Start the animation loop
    isRunning = true;
    // Print time delta for
    double dt = 1.0 / 60.0; 
    System.out.println("FPS: " + 1.0 / dt);
    System.out.println(dt);
    System.out.println("Simulation started");
}


    public void stop() {
        if (timer != null) timer.stop();
        System.out.println("Simulation stopped");
        isRunning = false;
    }

    public void update(double dt) {
        for (int i = 0; i < pucks.size(); i++) {
            Puck puck = pucks.get(i);
            puck.updatePosition(dt);

            double radius = puck.getRadius();
            Vector2D pos = puck.getPosition();
            Vector2D vel = puck.getVelocity();

            // Check wall collisions (bounds: 0 to 600)
            if (pos.x - radius <= 0 || pos.x + radius >= 600) {
                vel = new Vector2D(-vel.x * elasticity, vel.y); // bounce on X wall
                puck.setVelocity(vel);
            }

            if (pos.y - radius <= 0 || pos.y + radius >= 600) {
                vel = new Vector2D(vel.x, -vel.y * elasticity); // bounce on Y wall
                puck.setVelocity(vel);
            }
        }

        // Puck-puck collisions
        for (int i = 0; i < pucks.size(); i++) {
            for (int j = i + 1; j < pucks.size(); j++) {
                checkAndResolveCollision(pucks.get(i), pucks.get(j));
            }
        }
        // Print positions for debugging
        for (Puck puck : pucks) {
            Vector2D pos = puck.getPosition();
            System.out.println("Puck at: " + pos.x + ", " + pos.y);
        }
    }

    // Check collision between two pucks and resolve if they are colliding
private void checkAndResolveCollision(Puck p1, Puck p2) {
    Vector2D pos1 = p1.getPosition();
    Vector2D pos2 = p2.getPosition();

    Vector2D delta = Vector2D.subtract(pos2, pos1);
    double distance = delta.magnitude();
    double minDist = p1.getRadius() + p2.getRadius();

    // Only process if they are overlapping
    if (distance < minDist && distance > 0) {
        // 1. Normalize the direction vector
        Vector2D normal = delta.normalize();

        // 2. Relative velocity
        Vector2D v1 = p1.getVelocity();
        Vector2D v2 = p2.getVelocity();
        Vector2D relVel = Vector2D.subtract(v2, v1);

        // 3. Velocity along normal
        double velAlongNormal = relVel.dot(normal);

        // If velocities are separating, ignore
        if (velAlongNormal <= 0) {
            // 4. Calculate impulse scalar
            double m1 = p1.getMass();
            double m2 = p2.getMass();
            double e = elasticity;

            double impulse = -(1 + e) * velAlongNormal / (1/m1 + 1/m2);

            // 5. Apply impulse
            Vector2D impulseVec = normal.multiply(impulse);
            p1.setVelocity(Vector2D.subtract(v1, impulseVec.multiply(1 / m1)));
            p2.setVelocity(Vector2D.add(v2, impulseVec.multiply(1 / m2)));

            // 6. Push pucks apart (to avoid sticking)
            double overlap = minDist - distance;
            Vector2D correction = normal.multiply(overlap / 2.0);
            p1.setPosition(Vector2D.subtract(pos1, correction));
            p2.setPosition(Vector2D.add(pos2, correction));
        }
    }
}


    public void setElasticity(double e) {
        this.elasticity = e;
    }
    
    public void setupPuckDrag(Circle circle, Puck puck) {
    circle.setOnMousePressed(event -> {
        if (!isRunning) {
            circle.setOpacity(0.7);  // Visual cue for dragging start
            System.out.println("Puck drag started at: " + event.getX() + ", " + event.getY());
        }
    });

    circle.setOnMouseDragged(event -> {
        if (!isRunning) {
            double mouseX = event.getX();
            double mouseY = event.getY();

            // Constrain position inside bounds (assuming radius from puck)
            double radius = circle.getRadius();

            double clampedX = Math.max(radius, Math.min(width - radius, mouseX));
            double clampedY = Math.max(radius, Math.min(height - radius, mouseY));

            // Update circle position (visual)
            circle.setCenterX(clampedX);
            circle.setCenterY(clampedY);

            // Update puck physics position
            puck.setPosition(new Vector2D(clampedX, clampedY));
        }
    });

    circle.setOnMouseReleased(event -> {
        if (!isRunning) {
            circle.setOpacity(1.0);  // Reset visual opacity
        }
    });
}
}

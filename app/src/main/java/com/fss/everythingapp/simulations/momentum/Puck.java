package com.fss.everythingapp.simulations.momentum;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Represents a puck in the momentum simulation.
 * Each puck has a 2D position, velocity, mass, and an optional image.
 * Extends JavaFX's Circle to allow easy rendering on the scene.
 */
public class Puck extends Circle {

    private Vector2D position;  // Current position of the puck
    private Vector2D velocity;  // Current velocity of the puck
    private double mass;        // Mass of the puck
    private Image image;        // Optional image for styling the puck

    /**
     * Constructs a new puck with a given position and mass.
     * Initializes with zero velocity and default fill color.
     *
     * @param x    initial x position
     * @param y    initial y position
     * @param mass mass of the puck
     */
    public Puck(double x, double y, double mass) {
        this.mass = mass;
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D(0, 0); // Initially at rest

        setMass(mass);          // Applies radius based on mass
        setCenterX(x);
        setCenterY(y);
        setFill(Color.BLUE);    // Temporary fill color if no image is used
    }

    /**
     * Sets the mass of the puck and updates its radius accordingly.
     * Larger masses result in larger pucks.
     *
     * @param mass the new mass
     */
    void setMass(double mass) {
        this.mass = mass;

        // Set radius as a function of mass: base size + sqrt(mass) for scaling
        double radius = 10 + Math.sqrt(mass) * 2;
        setRadius(radius);

        applyImage(); // Reapply image fill after resizing
    }

    /**
     * Sets the velocity vector of the puck.
     *
     * @param v the new velocity vector
     */
    public void setVelocity(Vector2D v) {
        this.velocity = v;
    }

    /**
     * Returns the current velocity of the puck.
     *
     * @return velocity vector
     */
    public Vector2D getVelocity() {
        return velocity;
    }

    /**
     * Updates the puck's position based on its velocity and the time step.
     * Also updates the visual center of the JavaFX circle.
     *
     * @param dt the time step (delta time)
     */
    public void updatePosition(double dt) {
        position = position.add(velocity.multiply(dt)); // newPos = pos + v * dt
        setCenterX(position.x);
        setCenterY(position.y);
    }

    /**
     * Returns the current position of the puck.
     *
     * @return position vector
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Sets the puck's position to a given vector and updates the UI.
     *
     * @param pos the new position vector
     */
    public void setPosition(Vector2D pos) {
        this.position = pos;
        setCenterX(pos.x);
        setCenterY(pos.y);
    }

    /**
     * Returns the mass of the puck.
     *
     * @return mass
     */
    public double getMass() {
        return mass;
    }

    /**
     * Sets an image to use as the puck's visual fill.
     *
     * @param image the image to apply
     */
    public void setImage(Image image) {
        this.image = image;
        applyImage();
    }

    /**
     * Applies the current image (if any) as a fill using ImagePattern.
     * Ensures the image is scaled to fit and tile with the circle shape.
     */
    private void applyImage() {
        if (this.image != null) {
            // ImagePattern(x, y, width, height, proportional)
            // We use proportional = true to scale the image to the puck radius
            setFill(new ImagePattern(this.image, 0, 0, 1, 1, true));
        }
    }
}

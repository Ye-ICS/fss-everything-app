package com.fss.everythingapp.simulations.photoelectriceffect;

import com.fss.everythingapp.simulations.Vector2D;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Represents a photon in the photoelectric effect simulation.
 * Photons carry energy E = hf and momentum p = E/c = hf/c
 */
public class Photon extends Circle {
    private Vector2D position;
    private Vector2D velocity;
    private double energy; // Energy in eV
    private double frequency; // Frequency in Hz
    private boolean absorbed; // Whether this photon has been absorbed

    // Physical constants
    public static final double PLANCK_CONSTANT = 4.136e-15; // eV·s
    public static final double SPEED_OF_LIGHT = 3.0e8; // m/s (scaled for simulation)

    /**
     * Constructor for a Photon object.
     * Initializes position, velocity, energy, and visual properties.
     * 
     * @param x Initial X position
     * @param y Initial Y position
     * @param energy Energy of the photon in eV
     */
    public Photon(double x, double y, double energy) {
        super();
        this.energy = energy;
        this.frequency = energy / PLANCK_CONSTANT;
        this.position = new Vector2D(x, y);
        this.absorbed = false;

        // Set photon speed (always speed of light)
        double speed = 1000.0; // Scaled speed for visualization
        double minY = speed / 1.7;
        double maxY = speed / 1.2;
        double randomY = minY + Math.random() * (maxY - minY);
        this.velocity = new Vector2D(speed, randomY); // Initially moving right with random Y velocity

        // Visual properties
        setRadius(3);
        setCenterX(x);
        setCenterY(y);
        setPhotonColor();
    }

    /**
     * Set photon color based on energy (wavelength)
     * Higher energy = shorter wavelength = bluer color
     */
    private void setPhotonColor() {
        // Map energy to color (rough approximation)
        if (energy < 1.8) { // Infrared/Red
            setFill(Color.RED);
        } else if (energy < 2.5) { // Orange/Yellow
            setFill(Color.ORANGE);
            setFill(Color.GREEN);
        } else if (energy < 3.5) { // Blue
            setFill(Color.BLUE);
        } else { // Violet/UV
            setFill(Color.PURPLE);
        }
    }

    /**
     * Update the position of this photon based on its velocity and time delta.
     * If the photon is absorbed, it does not move.
     * 
     * @param dt Time delta in seconds
     */
    public void updatePosition(double dt) {
        if (absorbed)
            return;

        position.add(velocity.multiply(dt));
        setCenterX(position.x);
        setCenterY(position.y);
    }

    /**
     * Sets the velocity of this photon.
     * 
     * @param v The new velocity as a Vector2D.
     */
    public void setVelocity(Vector2D v) {
        // Normalize to maintain speed of light
        Vector2D normalized = v.normalize();
        double speed = 200.0; // Scaled speed
        this.velocity = normalized.multiply(speed);
    }

    /**
     * Returns the velocity of this photon
     * 
     * @return velocity as a Vector2D
     */
    public Vector2D getVelocity() {
        return velocity;
    }

    /**
     * Returns the position of this photon
     * 
     * @return position as a Vector2D
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Sets the position of this photon and updates its visual center.
     * 
     * @param pos The new position as a Vector2D
     */
    public void setPosition(Vector2D pos) {
        this.position = pos;
        setCenterX(pos.x);
        setCenterY(pos.y);
    }

    /**
     * Returns the energy of this photon
     * 
     * @return energy in eV
     */
    public double getEnergy() {
        return energy;
    }

    /**
     * Sets the energy of this photon and updates its frequency and color.
     * 
     * @param energy in eV
     */
    public void setEnergy(double energy) {
        this.energy = energy;
        this.frequency = energy / PLANCK_CONSTANT;
        setPhotonColor();
    }

    /**
     * Returns the frequency of this photon
     * 
     * @return frequency in Hz
     */
    public double getFrequency() {
        return frequency;
    }

    /**
     * Check if this photon has been absorbed
     * 
     * @return true if absorbed, false otherwise
     */
    public boolean isAbsorbed() {
        return absorbed;
    }

    /**
     * Absorb this photon, marking it as no longer active
     * and making it invisible in the simulation.
     */
    public void absorb() {
        this.absorbed = true;
        setVisible(false);
    }

    /**
     * Calculate photon momentum (p = E/c)
     * Momentum is a vector quantity, but here we return the magnitude
     * 
     * @return momentum magnitude
     */
    public double getMomentum() {
        return energy / SPEED_OF_LIGHT;
    }
}
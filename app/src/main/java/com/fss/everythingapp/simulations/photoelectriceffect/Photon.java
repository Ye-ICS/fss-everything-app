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
    
    public Photon(double x, double y, double energy) {
        super();
        this.energy = energy;
        this.frequency = energy / PLANCK_CONSTANT;
        this.position = new Vector2D(x, y);
        this.absorbed = false;
        
        // Set photon speed (always speed of light)
        double speed = 200.0; // Scaled speed for visualization
        this.velocity = new Vector2D(speed, speed/1.8); // Initially moving right
        
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
        } else if (energy < 3.0) { // Green
            setFill(Color.GREEN);
        } else if (energy < 3.5) { // Blue
            setFill(Color.BLUE);
        } else { // Violet/UV
            setFill(Color.PURPLE);
        }
    }
    
    public void updatePosition(double dt) {
        if (absorbed) return;
        
        position.add(velocity.multiply(dt));
        setCenterX(position.x);
        setCenterY(position.y);
    }
    
    public void setVelocity(Vector2D v) {
        // Normalize to maintain speed of light
        Vector2D normalized = v.normalize();
        double speed = 200.0; // Scaled speed
        this.velocity = normalized.multiply(speed);
    }
    
    public Vector2D getVelocity() {
        return velocity;
    }
    
    public Vector2D getPosition() {
        return position;
    }
    
    public void setPosition(Vector2D pos) {
        this.position = pos;
        setCenterX(pos.x);
        setCenterY(pos.y);
    }
    
    public double getEnergy() {
        return energy;
    }
    
    public void setEnergy(double energy) {
        this.energy = energy;
        this.frequency = energy / PLANCK_CONSTANT;
        setPhotonColor();
    }
    
    public double getFrequency() {
        return frequency;
    }
    
    public boolean isAbsorbed() {
        return absorbed;
    }
    
    public void absorb() {
        this.absorbed = true;
        setVisible(false);
    }
    
    /**
     * Calculate photon momentum (p = E/c)
     * @return momentum magnitude
     */
    public double getMomentum() {
        return energy / SPEED_OF_LIGHT;
    }
}
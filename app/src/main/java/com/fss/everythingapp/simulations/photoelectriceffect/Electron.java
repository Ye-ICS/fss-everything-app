package com.fss.everythingapp.simulations.photoelectriceffect;

import com.fss.everythingapp.simulations.Vector2D;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Represents an electron in the photoelectric effect simulation.
 * Electrons can absorb photons and be ejected if photon energy exceeds work function.
 */
public class Electron extends Circle {
    private Vector2D position;
    private Vector2D velocity;
    private double mass; // Electron rest mass
    private boolean ejected; // Whether this electron has been ejected
    private double kineticEnergy; // Current kinetic energy in eV
    private static final double velocityScale = 200; // Scale factor for visualization
    
    // Physical constants
    public static final double ELECTRON_MASS = 9.109e-31; // kg (for physics calculations)
    public static final double ELECTRON_CHARGE = 1.602e-19; // C
    
    
    public Electron(double x, double y) {
        super();
        this.mass = 1.0; // Simplified mass for simulation
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D(0, 0);
        this.ejected = false;
        this.kineticEnergy = 0;
        
        // Visual properties
        setRadius(5);
        setCenterX(x);
        setCenterY(y);
        setFill(Color.DARKBLUE);
        setStroke(Color.LIGHTBLUE);
        setStrokeWidth(1);
    }
    
    public void updatePosition(double dt) {
        if (!ejected) return; // Only move if ejected
        
        position.add(velocity.multiply(dt));
        setCenterX(position.x);
        setCenterY(position.y);
        
        // Apply gravity effect (electrons fall down when ejected)
        // Vector2D gravity = new Vector2D(100, -200); // Upward Initial Velocity
        // velocity.add(gravity.multiply(dt));
        
    }
    
    /**
     * Attempt to absorb a photon and potentially get ejected
     * @param photon The incoming photon
     * @param workFunction The work function of the material (eV)
     * @return true if photon was absorbed, false otherwise
     */
    public boolean absorbPhoton(Photon photon, double workFunction) {
        if (ejected || photon.isAbsorbed()) return false;

        double photonEnergy = photon.getEnergy();

        // Check if photon has enough energy to eject electron
        if (photonEnergy >= workFunction) {
            // Calculate kinetic energy of ejected electron
            kineticEnergy = photonEnergy - workFunction;

            // Convert kinetic energy to velocity (classical approximation)
            double speed = velocityScale * kineticEnergy; // Scaled for visualization

            // Get photon's velocity vector and compute a perpendicular direction
            Vector2D photonVelocity = photon.getVelocity().normalize();
            // Perpendicular vector in 2D: (-y, x)
            Vector2D perp = new Vector2D(photonVelocity.x,-photonVelocity.y);

            // Set electron velocity in the perpendicular direction (choose one direction)
            Vector2D electronVelocity = perp.multiply(speed);

            setVelocity(electronVelocity);

            // Mark as ejected and change appearance
            ejected = true;
            setFill(Color.YELLOW); // Ejected electrons are yellow
            setStroke(Color.ORANGE);

            // Absorb the photon
            photon.absorb();

            return true;
        }

        return false;
    }
    
    public void setVelocity(Vector2D v) {
        this.velocity = v;
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
    
    public double getMass() {
        return mass;
    }
    
    public boolean isEjected() {
        return ejected;
    }
    
    public double getKineticEnergy() {
        return kineticEnergy;
    }
    
    /**
     * Reset electron to initial state
     */
    public void reset() {
        ejected = false;
        velocity = new Vector2D(0, 0);
        kineticEnergy = 0;
        setFill(Color.DARKBLUE);
        setStroke(Color.LIGHTBLUE);
        setVisible(true);
    }
}
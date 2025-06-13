package com.fss.everythingapp.simulations.photoelectriceffect;

import com.fss.everythingapp.simulations.Vector2D;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Represents an electron in the photoelectric effect simulation.
 * Electrons can absorb photons and be ejected if photon energy exceeds work
 * function.
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

    /**
     * Constructor for an Electron object.
     * Initializes position, velocity, mass, and visual properties.
     * 
     * @param x Initial X position
     * @param y Initial Y position
     */
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

    /**
     * Update the position of this electron based on its velocity and time delta.
     * Only moves if the electron has been ejected.
     * 
     * @param dt Time delta in seconds
     */
    public void updatePosition(double dt) {
        if (!ejected)
            return; // Only move if ejected

        position.add(velocity.multiply(dt));
        setCenterX(position.x);
        setCenterY(position.y);

    }

    /**
     * Attempt to absorb a photon and potentially get ejected.
     * If the photon energy exceeds the work function, the electron is ejected.
     * 
     * @param photon       The incoming photon
     * @param workFunction The work function of the material (eV)
     * @return true if photon was absorbed and electron ejected, false otherwise
     */
    public boolean absorbPhoton(Photon photon, double workFunction) {
        if (ejected || photon.isAbsorbed())
            return false;

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
            Vector2D perp = new Vector2D(photonVelocity.x, -photonVelocity.y);

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

    /**
     * Sets the velocity of this electron.
     * 
     * @param v The new velocity as a Vector2D.
     */
    public void setVelocity(Vector2D v) {
        this.velocity = v;
    }

    /**
     * Returns the velocity of this electron.
     * 
     * @return velocity as a Vector2D
     */
    public Vector2D getVelocity() {
        return velocity;
    }

    /**
     * Returns the position of this electron.
     * 
     * @return position as a Vector2D
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Sets the position of this electron and updates its visual center.
     * 
     * @param pos The new position as a Vector2D
     */
    public void setPosition(Vector2D pos) {
        this.position = pos;
        setCenterX(pos.x);
        setCenterY(pos.y);
    }

    /**
     * Returns the mass of this electron.
     * 
     * @return mass in simulation units
     */
    public double getMass() {
        return mass;
    }

    /**
     * Checks if this electron has been ejected.
     * 
     * @return true if ejected, false otherwise
     */
    public boolean isEjected() {
        return ejected;
    }

    /**
     * Returns the current kinetic energy of this electron.
     * 
     * @return kinetic energy in eV
     */
    public double getKineticEnergy() {
        return kineticEnergy;
    }

    /**
     * Reset electron to initial state.
     * Sets velocity and kinetic energy to zero and restores visual appearance.
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
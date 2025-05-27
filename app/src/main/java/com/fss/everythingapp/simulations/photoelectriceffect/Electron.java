package com.fss.everythingapp.simulations.photoelectriceffect;

public class Electron {
    private double x, y;
    private double vx, vy;
    private double kineticEnergy;
    private boolean active;
    private static final double GRAVITY = 0.1; // Simplified gravity effect
    
    public Electron(double startX, double startY) {
        this.x = startX;
        this.y = startY;
        this.vx = 0;
        this.vy = 0;
        this.kineticEnergy = 0;
        this.active = false;
    }
    
    public void emit(double photonEnergy, double workFunction, double angle, double velocity) {
        // Calculate kinetic energy using Einstein's photoelectric equation
        // KE = hf - φ (photon energy - work function)
        this.kineticEnergy = Math.max(0, photonEnergy - workFunction);
        
        if (this.kineticEnergy > 0) {
            // Calculate velocity components based on angle and kinetic energy
            double speed = velocity * Math.sqrt(this.kineticEnergy / 10.0); // Scaled for visualization
            this.vx = speed * Math.cos(Math.toRadians(angle));
            this.vy = -speed * Math.sin(Math.toRadians(angle)); // Negative for upward motion
            
            this.active = true;
        }
    }
    
    public void update() {
        if (!active) return;
        x += vx;
        y += vy;
        vy += GRAVITY; // Apply gravity
    }

    
    public void reset() {
        this.active = false;
        this.vx = 0;
        this.vy = 0;
        this.kineticEnergy = 0;
    }
    
    // Getters and setters
    public boolean isActive() { return active; }
    public double getKineticEnergy() { return kineticEnergy; }
    public double getX() { return x; }
    public double getY() { return y; }
    
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

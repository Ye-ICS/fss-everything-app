package com.fss.everythingapp.simulations.photoelectriceffect;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

import com.fss.everythingapp.simulations.Vector2D;

import java.util.Iterator;

/**
 * Main simulation class for the photoelectric effect.
 * Manages photons, electrons, and their interactions.
 */
public class PhotoelectricSimulation {
    private final List<Photon> photons = new ArrayList<>();
    private final List<Electron> electrons = new ArrayList<>();
    private double workFunction = 2.5; // Work function in eV (typical for metals)
    private double photonEnergy = 3.0; // Default photon energy in eV
    private double intensity = 1.0; // Light intensity (photons per second)
    private boolean isRunning = false;
    private AnimationTimer timer;
    private double width;
    private double height;
    private static final double SURFACE_HEIGHT = 50; // Height of metal surface
    
    // Emission statistics
    private int totalPhotonsEmitted = 0;
    private int totalElectronsEjected = 0;
    private Rectangle metalSurface;
    
    public PhotoelectricSimulation(double width, double height) {
        this.width = width;
        this.height = height;
        
        // Create metal surface
        metalSurface = new Rectangle(0, height - SURFACE_HEIGHT, width, SURFACE_HEIGHT);
        metalSurface.setFill(Color.SILVER);
        metalSurface.setStroke(Color.DARKGRAY);
        metalSurface.setStrokeWidth(2);
        
        // Initialize electrons on the metal surface
        initializeElectrons();
    }
    
    /**
     * Update simulation dimensions when window is resized
     */
    public void updateDimensions(double newWidth, double newHeight) {
        if (newWidth <= 0 || newHeight <= 0) return;
        
        this.width = newWidth;
        this.height = newHeight;
        
        // Update metal surface dimensions and position
        metalSurface.setWidth(newWidth);
        metalSurface.setHeight(SURFACE_HEIGHT);
        metalSurface.setY(newHeight - SURFACE_HEIGHT);
        
        // Reposition existing electrons to new surface
        repositionElectronsToSurface();
        
        // Remove any photons or electrons that are now out of bounds
        cleanupOutOfBoundsElements();
    }
    
    /**
     * Reposition electrons to the new surface location
     */
    private void repositionElectronsToSurface() {
        for (Electron electron : electrons) {
            if (!electron.isEjected()) {
                Vector2D pos = electron.getPosition();
                // Keep x position but move to new surface level
                electron.setPosition(new Vector2D(
                    Math.min(pos.x, width - 20), // Ensure within bounds
                    height - SURFACE_HEIGHT - 5  // Just above surface
                ));
            }
        }
    }
    
    /**
     * Remove elements that are now outside the simulation bounds
     */
    private void cleanupOutOfBoundsElements() {
        photons.removeIf(photon -> 
            photon.getPosition().x < 0 || 
            photon.getPosition().x > width ||
            photon.getPosition().y < 0 ||
            photon.getPosition().y > height
        );
        
        electrons.removeIf(electron -> 
            electron.isEjected() && (
                electron.getPosition().x < -50 || 
                electron.getPosition().x > width + 50 ||
                electron.getPosition().y > height + 50
            )
        );
    }
    
    /**
     * Initialize electrons distributed along the metal surface
     */
    private void initializeElectrons() {
        electrons.clear();
        int numElectrons = Math.max(15, (int)(width / 30)); // Scale with width
        double spacing = width / numElectrons;
        
        for (int i = 0; i < numElectrons; i++) {
            double x = spacing * i + spacing / 2;
            double y = height - SURFACE_HEIGHT - 5; // Just above the metal surface
            electrons.add(new Electron(x, y));
        }
    }
    
    public void start() {
        if (isRunning) return;
        
        timer = new AnimationTimer() {
            private long lastUpdate = -1;
            private long lastPhotonEmission = 0;
            
            @Override
            public void handle(long now) {
                if (lastUpdate > 0) {
                    double dt = (now - lastUpdate) / 1e9;
                    update(dt);
                    
                    // Emit photons based on intensity
                    if (shouldEmitPhoton(now, lastPhotonEmission)) {
                        emitPhoton();
                        lastPhotonEmission = now;
                    }
                }
                lastUpdate = now;
            }
        };
        
        timer.start();
        isRunning = true;
        System.out.println("Photoelectric simulation started");
    }
    
    public void stop() {
        if (timer != null) {
            timer.stop();
        }
        isRunning = false;
        System.out.println("Photoelectric simulation stopped");
    }
    
    public void reset() {
        stop();
        photons.clear();
        initializeElectrons();
        totalPhotonsEmitted = 0;
        totalElectronsEjected = 0;
        System.out.println("Photoelectric simulation reset");
    }
    
    /**
     * Determine if a new photon should be emitted based on intensity
     */
    private boolean shouldEmitPhoton(long currentTime, long lastEmission) {
        double timeSinceLastEmission = (currentTime - lastEmission) / 1e9;
        double emissionInterval = 1.0 / intensity; // seconds between photons
        return timeSinceLastEmission >= emissionInterval;
    }
    
    /**
     * Emit a new photon from the left side of the screen
     */
    private void emitPhoton() {
        double y = Math.random() * (height - SURFACE_HEIGHT - 100) + 50; // Random height above surface
        Photon photon = new Photon(0, y, photonEnergy);
        photons.add(photon);
        totalPhotonsEmitted++;
    }
    
    public void update(double dt) {
        // Update photon positions
        Iterator<Photon> photonIter = photons.iterator();
        while (photonIter.hasNext()) {
            Photon photon = photonIter.next();
            
            if (photon.isAbsorbed()) {
                photonIter.remove();
                continue;
            }
            
            photon.updatePosition(dt);
            Vector2D photonPos = photon.getPosition();
            
            // Remove photons that have left the screen bounds
            if (photonPos.x > width || photonPos.x < -10 || 
                photonPos.y > height || photonPos.y < -10) {
                photonIter.remove();
                continue;
            }
            
            // Check if photon hits the metal surface (without hitting an electron)
            if (photonPos.y >= height - SURFACE_HEIGHT - photon.getRadius()) {
                // Check if it would collide with any electron first
                boolean willHitElectron = false;
                for (Electron electron : electrons) {
                    if (!electron.isEjected()) {
                        Vector2D electronPos = electron.getPosition();
                        double distance = photonPos.distanceTo(electronPos);
                        double collisionDistance = photon.getRadius() + electron.getRadius() + 5; // Slightly larger detection
                        
                        if (distance <= collisionDistance) {
                            willHitElectron = true;
                            break;
                        }
                    }
                }
                
                // If photon hits surface without hitting electron, remove it
                if (!willHitElectron) {
                    photonIter.remove();
                    continue;
                }
            }
            
            // Check for collisions with electrons
            checkPhotonElectronCollisions(photon);
        }
        
        // Update electron positions
        Iterator<Electron> electronIter = electrons.iterator();
        while (electronIter.hasNext()) {
            Electron electron = electronIter.next();
            electron.updatePosition(dt);
            
            // Remove electrons that have fallen off screen
            if (electron.isEjected() && electron.getPosition().y > height + 50) {
                electronIter.remove();
            }
        }
        
        // Replenish electrons on the surface
        replenishElectrons();
    }
    
    /**
     * Check for collisions between photons and electrons
     */
    private void checkPhotonElectronCollisions(Photon photon) {
        for (Electron electron : electrons) {
            if (electron.isEjected()) continue;
            
            Vector2D photonPos = photon.getPosition();
            Vector2D electronPos = electron.getPosition();
            
            double distance = photonPos.distanceTo(electronPos);
            double collisionDistance = photon.getRadius() + electron.getRadius();
            
            if (distance <= collisionDistance) {
                if (electron.absorbPhoton(photon, workFunction)) {
                    totalElectronsEjected++;
                    System.out.println("Electron ejected! KE = " + 
                                     String.format("%.2f", electron.getKineticEnergy()) + " eV");
                }
                break; // Photon can only interact with one electron
            }
        }
    }
    
    /**
     * Add new electrons to replace ejected ones
     */
    private void replenishElectrons() {
        long electronsOnSurface = electrons.stream()
            .filter(e -> !e.isEjected())
            .count();
        
        // Maintain at least 15 electrons on the surface, scaled by width
        int minElectrons = Math.max(15, (int)(width / 40));
        while (electronsOnSurface < minElectrons) {
            double x = Math.random() * (width - 20) + 10;
            double y = height - SURFACE_HEIGHT - 5;
            electrons.add(new Electron(x, y));
            electronsOnSurface++;
        }
    }
    
    // Getters and setters
    public double getWorkFunction() {
        return workFunction;
    }
    
    public void setWorkFunction(double workFunction) {
        this.workFunction = workFunction;
    }
    
    public double getPhotonEnergy() {
        return photonEnergy;
    }
    
    public void setPhotonEnergy(double photonEnergy) {
        this.photonEnergy = photonEnergy;
    }
    
    public double getIntensity() {
        return intensity;
    }
    
    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }
    
    public List<Photon> getPhotons() {
        return photons;
    }
    
    public List<Electron> getElectrons() {
        return electrons;
    }
    
    public Rectangle getMetalSurface() {
        return metalSurface;
    }
    
    public int getTotalPhotonsEmitted() {
        return totalPhotonsEmitted;
    }
    
    public int getTotalElectronsEjected() {
        return totalElectronsEjected;
    }
    
    public double getQuantumEfficiency() {
        if (totalPhotonsEmitted == 0) return 0;
        return (double) totalElectronsEjected / totalPhotonsEmitted;
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
}
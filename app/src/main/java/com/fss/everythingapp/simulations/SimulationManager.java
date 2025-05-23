package com.fss.everythingapp.simulations;
//~m :) 

public class SimulationManager {
    
    Simulation currentSimulation;
    CoordinateSystem coordinateSystem;
    boolean isRunning;

    public void initialize()
    {
        if (currentSimulation != null) {
            currentSimulation.initialize();
        }
    }
    public double update(double deltaTime)
    {
        if (currentSimulation != null) {
            currentSimulation.update(deltaTime);
        }
        return deltaTime;

    }

    public void render()
    {
        if (currentSimulation != null) {
            currentSimulation.render();
        }
    }

    public void stop()
    {
        isRunning = false;
    }

    public void start()
    {
        isRunning = true;
    }

    public void reset()
    {
        if (currentSimulation != null) {
            currentSimulation.reset();
        }
    }

    public void switchSimulation(Simulation simulation)
    {
        currentSimulation = simulation;
        if (currentSimulation != null) {
            currentSimulation.initialize();
        }
    }

    public Simulation getCurrentSimulation()
    {
        return currentSimulation;
    }

    // For JavaFX: get projectiles for rendering
    public java.util.List<KinematicObject> getProjectiles() {
        if (currentSimulation != null) {
            return currentSimulation.getProjectiles();
        }
        return null;
    }
}

// SimulationManager handles the lifecycle of a simulation

package com.fss.everythingapp.simulations;
//~m :) 

public class SimulationManager {
    
    Simulation currentSimulation;
    CoordinateSystem coordinateSystem;
    boolean isRunning;

    public void initialize()
    {

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

    }

    public void stop()
    {

    }

    public void start()
    {

    }

    public void reset()
    {

    }

    public void switchSimulation(Simulation simulation)
    {

    }

    public Simulation getCurrentSimulation()
    {
        return currentSimulation;
    }
}

// SimulationManager handles the lifecycle of a simulation

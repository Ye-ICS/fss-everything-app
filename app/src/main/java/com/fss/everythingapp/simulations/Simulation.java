package com.fss.everythingapp.simulations;
//~m :)
public class Simulation extends SimulationMenuController
{
    // Simulation-specific properties and methods
    //
    String name;
    String description;


    public void initialize() {
    }

    public double update(double deltaTime) {
      return -1; //this entire thing might be obsolete.
    }

    public void render() {
    
    }
    
    public void reset() {
        
    }

    //Getters and shtuff

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
}

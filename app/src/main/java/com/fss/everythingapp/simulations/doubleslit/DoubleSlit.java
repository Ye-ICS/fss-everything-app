package com.fss.everythingapp.simulations.doubleslit;

import java.util.ArrayList;
import java.util.List;

public class DoubleSlit {
    private double wavelength;
    private double slitSeparation;
    private double slitWidth;
    private double screenDistance = 2.0; // meters
    
    public DoubleSlit() {
        // Default values
        wavelength = 450e-9; // 500 nm
        slitSeparation = 1e-3; // 1 mm
        slitWidth = 0.1e-3; // 0.1 mm
    }
    
    void setWavelength(double wavelength) {
        this.wavelength = wavelength;
    }
    
    void setSlitSeparation(double separation) {
        this.slitSeparation = separation;
    }
    
    void setSlitWidth(double width) {
        this.slitWidth = width;
    }
    
    public double getWavelength() { return wavelength; }
    public double getSlitSeparation() { return slitSeparation; }
    public double getSlitWidth() { return slitWidth; }
}
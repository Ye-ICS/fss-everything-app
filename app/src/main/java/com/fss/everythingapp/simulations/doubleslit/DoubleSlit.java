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
    
    double calculateDoubleSlitIntensity(double y) {
        // Calculate the angle θ from the center of the slits to the point y on the screen
        double angleFromCenter = Math.atan(y / screenDistance);
    
        // Single slit diffraction area
        // This phase term accounts for the spreading of light due to the finite slit width
        double diffractionPhase = (Math.PI * slitWidth * Math.sin(angleFromCenter)) / wavelength;
        double singleSlitFactor = 1.0;
        if (Math.abs(diffractionPhase) > 1e-10) {
            // sinc(a) = sin(a) / a
            // The intensity envelope is proportional to sinc^2(β)
            double sinc = Math.sin(diffractionPhase) / diffractionPhase;
            singleSlitFactor = sinc * sinc;
        }
    
        //Double slit interference pattern
        // This phase term accounts for the path difference between the two slits
        double interferencePhase = (2 * Math.PI * slitSeparation * Math.sin(angleFromCenter)) / wavelength;
        // The intensity pattern is proportional to cos^2(b/2)
        double interferencePattern = Math.cos(interferencePhase / 2);
        interferencePattern *= interferencePattern;
    
        // The observed intensity is the product of the single slit area and the double slit pattern
        return (singleSlitFactor * interferencePattern);
    }

    public double getWavelength() { return wavelength; }
    public double getSlitSeparation() { return slitSeparation; }
    public double getSlitWidth() { return slitWidth; }
}
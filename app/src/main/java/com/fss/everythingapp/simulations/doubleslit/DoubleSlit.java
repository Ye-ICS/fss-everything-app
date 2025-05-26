package com.fss.everythingapp.simulations.doubleslit;

import java.util.ArrayList;
import java.util.List;

public class DoubleSlit {
    private double wavelength;
    private double slitSeparation;
    private double slitWidth;
    private double screenDistance = 2.0; // meters
    private List<Double> interferencePattern;
    
    public DoubleSlit() {
        interferencePattern = new ArrayList<>();
        // Default values
        wavelength = 450e-9; // 450 nm
        slitSeparation = 1e-3; // 1 mm
        slitWidth = 0.1e-3; // 0.1 mm
    }

    public void setSlitProperties(double separation, double width) {
        slitSeparation = separation;
        slitWidth = width;
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

    public void calculateInterferencePattern() {

        // Number of points (samples) across the screen to calculate intensity for
        int numPoints = 500;

        // Physical width of the screen (meters) where the pattern is observed
        double screenWidth = 0.02; // 2 cm

        // Distance between each sample point on the screen
        double distanceBetweenPoints = screenWidth / numPoints;

        // Loop over each point on the screen from left edge (-screenWidth/2)
        // to right edge (+screenWidth/2)
        for (int i = 0; i < numPoints; i++) {
            // Calculate the y-position on the screen for this sample
            // y = 0 is the center, negative is left, positive is right
            double y = -screenWidth / 2 + i * distanceBetweenPoints;

            // Calculate the intensity at this y-position using the double slit formula
            // This includes both diffraction (single slit) and interference (double slit) effects
            double intensity = calculateDoubleSlitIntensity(y);

            // Store the calculated intensity in the pattern list
            interferencePattern.add(intensity);
        }
    }
    
    public List<Double> getInterferencePattern() {
        return new ArrayList<>(interferencePattern);
    }

    double getWavelength() { return wavelength; }
    double getSlitSeparation() { return slitSeparation; }
    double getSlitWidth() { return slitWidth; }
}
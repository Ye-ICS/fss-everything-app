package com.fss.everythingapp.simulations.doubleslit;

import java.util.ArrayList;
import java.util.List;

public class DoubleSlit {
    private double wavelengthNano;
    private double slitSeparationMicro;
    private double slitWidthMicro;
    private double screenDistance;
    private List<Double> interferencePattern = new ArrayList<>();

    /**
     * Calculates the interference pattern for a double-slit experiment based on the given parameters.
     *
     * @param separation The distance between the two slits (recieved in micrometers).
     * @param width The width of each slit (recievd in micrometers).
     * @param wavelength The wavelength of the light used (recieved in nanometers).
     * @param distance The distance from the slits to the screen (in meters).
     * @return A list of intensity values representing the interference pattern across the screen.
     *         Each value corresponds to a point on the screen, calculated at regular intervals.
     * 
     * The method assumes:
     * - The screen is centered at y = 0 and spans a width of 2 cm.
     * - The interference pattern is calculated for 500 evenly spaced points across the screen.
     * - The intensity at each point is determined using the `calculateIntensity` method.
     *
     * - Ensure the `calculateIntensity` method is implemented to compute the intensity at a given position.
     * - The method clears any existing interference pattern before calculating a new one.
     */
    List<Double> calculateInterferencePattern(double separation, double width, double wavelength, double distance) {
        this.wavelengthNano = wavelength;
        this.slitSeparationMicro = separation;
        this.slitWidthMicro = width;
        this.screenDistance = distance;

        interferencePattern.clear(); // Clear any existing pattern
        
        int numPoints = 500; // Number of points to calculate on the screen
        double screenWidth = 0.02; // Total width of the screen (2 cm)
        double step = screenWidth / numPoints; // Step size between points
        double startY = -screenWidth / 2; // Start position (leftmost point on the screen)
        
        // Loop through each point on the screen
        for (int i = 0; i < numPoints; i++) {
            double currentPosition = startY + i * step; // Current position on the screen
            double intensity = calculateIntensity(currentPosition); // Calculate intensity at this position
            interferencePattern.add(intensity); // Add intensity to the pattern list
        }

        return new ArrayList<>(interferencePattern); // Return the calculated pattern
    }

    /**
     * Calculates the intensity of the double slit interference pattern at a given point y on the screen.
     * This method combines single slit diffraction and double slit interference effects.
     * wavelength is getting converted in nanometers, slit width and separation are in micrometers.
     * @param y position on the screen 
     * @return intensity at the given position
     */
    private double calculateIntensity(double currentPosition) {
        // Small angle approximation: sin(θ) ≈ y / L, where L is the screen distance
        double sinTheta = currentPosition / screenDistance;
        
        // Single slit diffraction factor:
        // beta = π * slitWidth * sin(θ) / wavelength
        // Intensity due to single slit diffraction is proportional to (sin(beta) / beta)^2
        double beta = Math.PI * (slitWidthMicro * 1e-6) * sinTheta / (wavelengthNano* 1e-9);
        double singleSlitFactor = 1.0; // Default intensity factor for single slit diffraction
        if (Math.abs(beta) > 1e-10) { // Avoid division by zero
            double sinc = Math.sin(beta) / beta; // sinc function
            singleSlitFactor = sinc * sinc; // Square of sinc gives intensity
        }
        
        // Double slit interference factor:
        // alpha = π * slitSeparation * sin(θ) / wavelength
        // Intensity due to double slit interference is proportional to cos^2(alpha)
        double alpha = Math.PI * (slitSeparationMicro * 1e-6) * sinTheta / (wavelengthNano* 1e-9);
        double doubleSlitFactor = Math.cos(alpha); // Cosine of alpha
        doubleSlitFactor *= doubleSlitFactor; // Square of cosine gives intensity
        
        // Combined intensity is the product of single slit diffraction and double slit interference factors
        return singleSlitFactor * doubleSlitFactor;
    }
}
package com.fss.everythingapp.simulations.doubleslit;

import java.util.List;
import javafx.scene.paint.Color;

public class Utils {
    /**
     * Converts a wavelength in the visible spectrum to an approximate RGB color representation.
     * 
     * @param wl The wavelength in nanometers (nm). Expected range is between 400 and 750 nm.
     *           Values outside this range will not produce meaningful results.
     * @param intensity The intensity factor to scale the RGB values. Should be between 0.0 and 1.0.
     *                  Higher values result in brighter colors.
     * @return A {@link Color} object representing the RGB color corresponding to the given wavelength
     *         and intensity. The color is calculated based on the wavelength's position in the visible spectrum.
     * 
     * Wavelengths between 400-440 nm produce colors in the violet range.
     * Wavelengths between 440-490 nm produce colors in the blue range.
     * Wavelengths between 490-510 nm produce colors in the cyan range.
     * Wavelengths between 510-580 nm produce colors in the green range.
     * Wavelengths between 580-645 nm produce colors in the orange-red range.
     * Wavelengths between 645-750 nm produce colors in the red range.
     */
    static Color wavelengthToColor(double wl, double intensity) {
        double r = 0;
        double g = 0;
        double b = 0;
        
        if (wl >= 400 && wl < 440) {
            r = -(wl - 440) / (440 - 400);
            b = 1.0;
        } else if (wl >= 440 && wl < 490) {
            g = (wl - 440) / (490 - 440);
            b = 1.0;
        } else if (wl >= 490 && wl < 510) {
            g = 1.0;
            b = -(wl - 510) / (510 - 490);
        } else if (wl >= 510 && wl < 580) {
            r = (wl - 510) / (580 - 510);
            g = 1.0;
        } else if (wl >= 580 && wl < 645) {
            r = 1.0;
            g = -(wl - 645) / (645 - 580);
        } else if (wl >= 645 && wl <= 750) {
            r = 1.0;
        }
        
        // Apply intensity
        r *= intensity;
        g *= intensity;
        b *= intensity;
        
        return Color.color(r, g, b);
    }

    /**
     * Calculates the maximum intensity value from a list of intensity values.
     *
     * @param pattern A list of double values representing intensity values.
     * @return The maximum intensity value found in the list. If the list is empty, returns 0.0.
     */
    static double calculateMaxIntensity(List<Double> pattern) {
        // Find the maximum intensity value
        double maxIntensity = 0.0;
        for (double value : pattern) {
            if (value > maxIntensity) {
                maxIntensity = value;
            }
        }
        return maxIntensity;
    }
}
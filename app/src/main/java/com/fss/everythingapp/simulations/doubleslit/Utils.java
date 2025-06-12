package com.fss.everythingapp.simulations.doubleslit;

import java.util.List;

import javafx.scene.paint.Color;

public class Utils {
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

package com.fss.everythingapp.simulations.doubleslit;

import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LightCanvas {
    private Canvas canvas;
    private GraphicsContext gc;
    
    public LightCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }
    
    public void drawInterferencePattern(List<Double> pattern, double wavelength, double screenDistance, double slitWidth) {
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();
        
        // Clear the entire canvas with a black background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        
        // Check for zero values and display appropriate message
        if (screenDistance <= 0) {
            drawMessage("Set screen distance > 0 to see interference pattern", canvasWidth, canvasHeight);
            return;
        }
        if (slitWidth <= 0) {
            drawMessage("Set slit width > 0 to see pattern", canvasWidth, canvasHeight);
            return;
        }
        
        // Find the maximum intensity value
        double maxIntensity = 0.0;
        for (double value : pattern) {
            if (value > maxIntensity) {
                maxIntensity = value;
            }
        }
    
        // Calculate the width of each vertical bar (pixel) to draw
        double pixelWidth = canvasWidth / pattern.size();
        
        // Loop through each point in the pattern and draw a colored bar
        for (int i = 0; i < pattern.size(); i++) {
            // Normalize intensity to a value between 0 and 1
            double intensity = pattern.get(i) / maxIntensity;

            // Get the color corresponding to the current wavelength and intensity
            Color color = wavelengthToColor(wavelength, intensity);
            gc.setFill(color);
            
            // Calculate the x position for this bar
            double x = i * pixelWidth;

            // Draw a vertical rectangle representing the intensity at this point
            gc.fillRect(x, 0, pixelWidth + 1, canvasHeight);
        }
    }
    
    void drawMessage(String message, double canvasWidth, double canvasHeight) {
        gc.setFill(Color.WHITE);
        gc.fillText(message, canvasWidth / 2 - message.length() * 3, canvasHeight / 2);
    }
    
    private Color wavelengthToColor(double wl, double intensity) {
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
}
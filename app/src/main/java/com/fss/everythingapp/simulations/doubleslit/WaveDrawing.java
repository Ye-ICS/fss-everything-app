package com.fss.everythingapp.simulations.doubleslit;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Handles all wave-related drawing operations for the double-slit experiment.
 * This includes incident light rays, wave patterns, and curved wave propagation.
 */
public class WaveDrawing {
    private GraphicsContext gc;
    private double canvasHeight;
    private double waveSourceX;
    private double screenX;

    /**
     * Constructs a WaveDrawing with the specified graphics context and canvas dimensions.
     * @param gc The JavaFX GraphicsContext to draw on
     * @param canvasWidth Width of the canvas
     * @param canvasHeight Height of the canvas
     * @param waveSourceX X position of the wave source
     * @param screenX X position of the detection screen
     */
    WaveDrawing(GraphicsContext gc, double canvasHeight, double waveSourceX, double screenX) {
        this.gc = gc;
        this.canvasHeight = canvasHeight;
        this.waveSourceX = waveSourceX;
        this.screenX = screenX;
    }

    /**
     * Draws the complete wave pattern including incident ray and interference waves.
     * Calculates interference pattern and renders waves from slits to screen.
     * @param slitX X position of barrier with slits
     * @param slitYs Array of slit Y positions
     * @param wavelength Light wavelength in nanometers
     * @param slitSeparation Distance between slits in micrometers
     * @param slitWidth Width of each slit in micrometers
     * @param screenDistance Physical distance to screen in meters
     */
    void drawWavePatterns(double slitX, double[] slitYs, double wavelength, double slitSeparation, double slitWidth, double screenDistance) {
        // Draw incident ray
        drawIncidentLightRay(wavelength, slitYs, slitX, slitSeparation);

        // Calculate interference pattern
        DoubleSlit doubleSlit = new DoubleSlit();
        List<Double> pattern = doubleSlit.calculateInterferencePattern(slitSeparation, slitWidth, wavelength, screenDistance);

        drawWavesFromDoubleSlits(slitX, slitYs, pattern, wavelength);
    }
    
    /**
     * Draws the incident light ray from source to barrier.
     * Creates a rectangular beam that encompasses both slits.
     * @param wavelength Light wavelength for color
     * @param slitYs Array of slit Y positions
     * @param slitX X position of barrier
     * @param slitSeparation Distance between slits (used for beam width calculation)
     */
    private void drawIncidentLightRay(double wavelength, double[] slitYs, double slitX, double slitSeparation) {
        Color baseWaveColor = Utils.wavelengthToColor(wavelength, 1);
        gc.setFill(baseWaveColor);
        
        double centerYSlits = (slitYs[0] + slitYs[1]) / 2;
        double rectHeight = 8 + (slitYs[1] - slitYs[0] + slitSeparation / 100.0);
        
        gc.fillRect(waveSourceX, centerYSlits - rectHeight / 2, slitX - waveSourceX - 5, rectHeight);
    }

    /**
     * Draws curved wave lines from both slits to the screen based on interference pattern.
     * Only renders waves with sufficient intensity (> 0.1) for performance.
     * @param slitX X position of the barrier
     * @param slitYs Array of slit Y positions
     * @param pattern List of intensity values across the screen
     * @param wavelength Light wavelength for color determination
     */
    private void drawWavesFromDoubleSlits(double slitX, double[] slitYs, List<Double> pattern, double wavelength) {
        double screenTop = 10;
        double screenHeight = canvasHeight - 20;
        double maxIntensity = Utils.calculateMaxIntensity(pattern);

        gc.setLineWidth(1.5);

        // Sample pattern and draw waves
        for (int i = 0; i < pattern.size(); i += 10) {
            double intensity = pattern.get(i) / maxIntensity;

            if (intensity > 0.1) { // Only draw visible waves
                double screenY = screenTop + (i / (double) pattern.size()) * screenHeight;
                Color waveColor = Utils.wavelengthToColor(wavelength, 0.3 + (intensity * 0.7));
                gc.setStroke(waveColor);

                // Draw curved waves from both slits
                drawCurvedWave(slitX, slitYs[0], screenY, intensity);
                drawCurvedWave(slitX, slitYs[1], screenY, intensity);
            }
        }
    }

    /**
     * Draws a single curved wave line from a slit to a point on the screen.
     * Creates sinusoidal wave pattern perpendicular to the propagation direction.
     * @param slitX Starting X position (slit)
     * @param slitY Starting Y position (slit)
     * @param screenY Ending Y position (screen point)
     * @param intensity Wave intensity (affects amplitude)
     */
    private void drawCurvedWave(double slitX, double slitY, double screenY, double intensity) {
        double pathDistance = Math.hypot(screenX - slitX, screenY - slitY);
        int numPoints = Math.max((int) (pathDistance / 3), 2);

        double amplitude = 2 + (intensity * 3);

        // Calculate perpendicular direction for wave oscillation
        double perpDirX = screenY - slitY;
        double perpDirY = slitX - screenX;
        double perpLength = Math.hypot(perpDirX, perpDirY);
        if (perpLength > 0) {
            perpDirX /= perpLength;
            perpDirY /= perpLength;
        }

        // Draw wave segments
        double prevWaveX = slitX, prevWaveY = slitY;
        for (int i = 1; i < numPoints; i++) {
            double t = i / (double) (numPoints - 1);
            double interpX = slitX + t * (screenX - slitX);
            double interpY = slitY + t * (screenY - slitY);

            double waveOffset = amplitude * Math.sin(i * 0.25);

            double currentWaveX = interpX + perpDirX * waveOffset;
            double currentWaveY = interpY + perpDirY * waveOffset;

            gc.strokeLine(prevWaveX, prevWaveY, currentWaveX, currentWaveY);
            prevWaveX = currentWaveX;
            prevWaveY = currentWaveY;
        }
    }
}
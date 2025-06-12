package com.fss.everythingapp.simulations.doubleslit;

import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ExplanationCanvas {
    private Canvas canvas;
    private GraphicsContext gc;

    /**
     * Constructs an ExplanationCanvas with the specified canvas.
     * @param canvas The JavaFX Canvas to draw on
     */
    ExplanationCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    /**
     * Draws the complete double-slit experiment explanation diagram.
     * @param wavelength Light wavelength in nanometers (400-750)
     * @param screenDistance Distance from barrier to screen in meters
     * @param slitWidth Width of each slit in micrometers
     * @param slitSeparation Distance between slit centers in micrometers
     */
    void drawExplanation(double wavelength, double screenDistance, double slitWidth, double slitSeparation) {
        clearCanvas();
        
        // Calculate key positions
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();
        double centerY = canvasHeight / 2;
        double waveSourceX = 50;
        double screenX = canvasWidth - 30;
        double slitX = calculateBarrierPosition(waveSourceX, screenDistance);
        double[] slitYs = calculateSlitPositions(centerY, slitSeparation, canvasHeight);

        // Draw components
        drawWaveSource(waveSourceX, centerY, wavelength);
        drawBarrierWithSlits(slitX, slitYs, canvasHeight);
        drawScreen(screenX, canvasHeight);

        // Draw wave patterns if parameters are valid
        if (screenDistance > 0 && slitWidth > 0) {
            drawWavePatterns(waveSourceX, slitX, screenX, slitYs, wavelength, slitSeparation, slitWidth, screenDistance);
        }
    }

    /**
     * Clears the canvas with a black background.
     */
    private void clearCanvas() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Calculates the X position of the barrier based on screen distance.
     * Determines the barrier position as a function of the distance to the screen.
     * @param sourceX X position of the wave source
     * @param screenDistance Physical distance to the screen in meters
     * @return X coordinate for barrier placement
     */
    private double calculateBarrierPosition(double sourceX, double screenDistance) {
        // Simplify barrier position calculation based on screen distance
        double basePosition = sourceX + 150; // Starting point
        double positionFactor = screenDistance * 65; // Scale factor
        return basePosition + positionFactor;
    }

    /**
     * Draws the barrier with two slits cut out and highlighted edges.
     * @param x X position of the barrier center
     * @param slitYs Array containing Y positions of both slits
     * @param canvasHeight Total height of the canvas
     */
    private void drawBarrierWithSlits(double barrierCentreX, double[] slitYs, double canvasHeight) {
        double barrierWidth = 10;
        double slitHeight = 30;

        // Draw barrier
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(barrierCentreX - barrierWidth / 2, 0, barrierWidth, canvasHeight);

        // Draw slits (cut-outs)
        gc.setFill(Color.BLACK);
        for (double slitY : slitYs) {
            gc.fillRect(barrierCentreX - barrierWidth / 2, slitY - slitHeight / 2, barrierWidth, slitHeight);
        }

        // Highlight slit edges
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(2);
        for (double slitY : slitYs) {
            gc.strokeRect(barrierCentreX - barrierWidth / 2, slitY - slitHeight / 2, barrierWidth, slitHeight);
        }
    }

    /**
     * Calculates the Y positions of both slits based on separation distance.
     * Maps slit separation (0-500 μm) to visual separation on canvas.
     * @param centerY Center Y position of the canvas
     * @param slitSeparationMicrometers Separation between slits in micrometers
     * @param canvasHeight Total height of the canvas
     * @return Array containing Y positions of both slits
     */
    private double[] calculateSlitPositions(double centerY, double slitSeparationMicrometers, double canvasHeight) {
        double baseVisualSeparation = 10.0;
        double maxVisualSeparation = 120.0;
        double heightConstraint = canvasHeight * 0.4;
        double effectiveMaxSeparation = Math.min(maxVisualSeparation, heightConstraint);
        
        double normalizedSeparation = Math.min(slitSeparationMicrometers / 500.0, 1.0);

        double visualSeparation = baseVisualSeparation;
        if (slitSeparationMicrometers > 0) {
            visualSeparation = baseVisualSeparation + (normalizedSeparation * (effectiveMaxSeparation - baseVisualSeparation));
        }

        double slit1Y = centerY - visualSeparation / 2;
        double slit2Y = centerY + visualSeparation / 2;
        return new double[]{slit1Y, slit2Y};
    }

    /**
     * Draws the detection screen as a vertical white line.
     * @param x X position of the screen
     * @param canvasHeight Total height of the canvas
     */
    private void drawScreen(double x, double canvasHeight) {
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(3);
        gc.strokeLine(x, 5, x, canvasHeight - 5);
    }

    /**
     * Draws the wave source as a colored circle based on wavelength.
     * @param x X position of the source
     * @param y Y position of the source
     * @param wavelength Light wavelength in nanometers for color determination
     */
    private void drawWaveSource(double x, double y, double wavelength) {
        Color baseWaveColor = Utils.wavelengthToColor(wavelength, 1.0);
        gc.setFill(baseWaveColor);
        gc.fillOval(x - 4, y - 4, 8, 8);
    }

    /**
     * Draws the complete wave pattern including incident ray and interference waves.
     * Calculates interference pattern and renders waves from slits to screen.
     * @param sourceX X position of wave source
     * @param slitX X position of barrier with slits
     * @param screenX X position of detection screen
     * @param slitYs Array of slit Y positions
     * @param wavelength Light wavelength in nanometers
     * @param slitSeparation Distance between slits in micrometers
     * @param slitWidth Width of each slit in micrometers
     * @param screenDistance Physical distance to screen in meters
     */
    private void drawWavePatterns(double sourceX, double slitX, double screenX, double[] slitYs, double wavelength, double slitSeparation, double slitWidth, double screenDistance) {
        // Draw incident ray
        drawIncidentLightRay(wavelength, sourceX, slitYs, slitX, screenDistance);

        // Calculate interference pattern
        DoubleSlit doubleSlit = new DoubleSlit();
        List<Double> pattern = doubleSlit.calculateInterferencePattern(            
        slitSeparation, 
        slitWidth,
        wavelength,   
        screenDistance);

        drawWavesFromDoubleSlits(slitX, slitYs, screenX, pattern, wavelength);
    }
    
    /**
     * Draws the incident light ray from source to barrier.
     * Creates a rectangular beam that encompasses both slits.
     * @param wavelength Light wavelength for color
     * @param sourceX X position of wave source
     * @param slitYs Array of slit Y positions
     * @param slitX X position of barrier
     * @param slitSeparation Distance between slits (used for beam width calculation)
     */
    private void drawIncidentLightRay(double wavelength, double sourceX, double[] slitYs, double slitX, double slitSeparation) {
        Color baseWaveColor = Utils.wavelengthToColor(wavelength, 1);
        gc.setFill(baseWaveColor);
        
        double centerY = (slitYs[0] + slitYs[1]) / 2;
        double rectHeight = 8 + (slitYs[1] - slitYs[0] + slitSeparation / 100.0);
        
        gc.fillRect(sourceX, centerY - rectHeight / 2, slitX - sourceX - 5, rectHeight);
    }

    /**
     * Draws curved wave lines from both slits to the screen based on interference pattern.
     * Only renders waves with sufficient intensity (> 0.1) for performance.
     * @param slitX X position of the barrier
     * @param slitYs Array of slit Y positions
     * @param screenX X position of the screen
     * @param pattern List of intensity values across the screen
     * @param wavelength Light wavelength for color determination
     */
    private void drawWavesFromDoubleSlits(double slitX, double[] slitYs, double screenX, List<Double> pattern, double wavelength) {
        double canvasHeight = canvas.getHeight();
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
                drawCurvedWave(slitX, slitYs[0], screenX, screenY, intensity);
                drawCurvedWave(slitX, slitYs[1], screenX, screenY, intensity);
            }
        }
    }

    /**
     * Draws a single curved wave line from a slit to a point on the screen.
     * Creates sinusoidal wave pattern perpendicular to the propagation direction.
     * @param slitX Starting X position (slit)
     * @param slitY Starting Y position (slit)
     * @param screenX Ending X position (screen point)
     * @param screenY Ending Y position (screen point)
     * @param intensity Wave intensity (affects amplitude)
     */
    private void drawCurvedWave(double slitX, double slitY, double screenX, double screenY, double intensity) {
        double pathDistance = Math.sqrt((screenX - slitX) * (screenX - slitX) + (screenY - slitY) * (screenY - slitY));
        int numPoints = Math.max((int) (pathDistance / 3), 2);

        double amplitude = 2 + (intensity * 3);

        // Calculate perpendicular direction for wave oscillation
        double perpDirX = screenY - slitY;
        double perpDirY = slitX - screenX;
        double perpLength = Math.sqrt(perpDirX * perpDirX + perpDirY * perpDirY);
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

            double wavePhase = (i * 25 / 100.0);
            double waveOffset = amplitude * Math.sin(wavePhase);

            double currentWaveX = interpX + perpDirX * waveOffset;
            double currentWaveY = interpY + perpDirY * waveOffset;

            gc.strokeLine(prevWaveX, prevWaveY, currentWaveX, currentWaveY);
            prevWaveX = currentWaveX;
            prevWaveY = currentWaveY;
        }
    }
}
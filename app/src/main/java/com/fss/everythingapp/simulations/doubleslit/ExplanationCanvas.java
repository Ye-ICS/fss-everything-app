package com.fss.everythingapp.simulations.doubleslit;

import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ExplanationCanvas {
    private Canvas canvas;
    private GraphicsContext gc;
    
    // Canvas key positions 
    private double canvasWidth;
    private double canvasHeight;
    private double centerY;
    private double waveSourceX;
    private double screenX;

    /**
     * Constructs an ExplanationCanvas with the specified canvas.
     * @param canvas The JavaFX Canvas to draw on
     */
    ExplanationCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        initializeCanvasProperties();
    }
    
    /**
     * Initializes canvas dimensions and key positions.
     */
    private void initializeCanvasProperties() {
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();
        this.centerY = canvasHeight / 2;
        this.waveSourceX = 50;
        this.screenX = canvasWidth - 30;
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
        double slitX = calculateBarrierPosition(screenDistance);
        double[] slitYs = calculateSlitPositions(slitSeparation);

        // Draw components
        drawWaveSource(wavelength);
        drawBarrierWithSlits(slitX, slitYs);
        drawScreen();

        // Draw wave patterns if parameters are valid
        if (screenDistance > 0 && slitWidth > 0) {
            drawWavePatterns(slitX, slitYs, wavelength, slitSeparation, slitWidth, screenDistance);
        }
    }

    /**
     * Clears the canvas with a black background.
     */
    private void clearCanvas() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    /**
     * Calculates the X position of the barrier based on screen distance.
     * @param screenDistance Physical distance to the screen in meters
     * @return X coordinate for barrier placement
     */
    private double calculateBarrierPosition(double screenDistance) {
        // barrier position calculation based on screen distance
        double barrierPosition = waveSourceX + 10 + screenDistance * (canvasWidth / 3);
        return barrierPosition;
    }

    /**
     * Calculates the Y positions of both slits based on separation distance.
     * Maps slit separation (0-500 μm) to visual separation on canvas.
     * @param slitSeparationMicrometers Separation between slits in micrometers
     * @return Array containing Y positions of both slits
     */
    private double[] calculateSlitPositions(double slitSeparationMicrometers) {
        double visualSeparation = Math.min(slitSeparationMicrometers / 5.0, canvasHeight * 0.4);
        double slit1Y = centerY - visualSeparation / 2;
        double slit2Y = centerY + visualSeparation / 2;
        return new double[]{slit1Y, slit2Y};
    }

    /**
     * Draws the barrier with two slits cut out and highlighted edges.
     * @param barrierCentreX X position of the barrier center
     * @param slitYs Array containing Y positions of both slits
     */
    private void drawBarrierWithSlits(double barrierCentreX, double[] slitYs) {
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
     * Draws the wave source as a colored circle based on wavelength.
     * @param wavelength Light wavelength in nanometers for color determination
     */
    private void drawWaveSource(double wavelength) {
        Color baseWaveColor = Utils.wavelengthToColor(wavelength, 1.0);
        gc.setFill(baseWaveColor);
        gc.fillOval(waveSourceX - 4, centerY - 4, 8, 8);
    }

    /**
     * Draws the detection screen as a vertical white line.
    */
    private void drawScreen() {
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(3);
        gc.strokeLine(screenX, 5, screenX, canvasHeight - 5);
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
    private void drawWavePatterns(double slitX, double[] slitYs, double wavelength, double slitSeparation, double slitWidth, double screenDistance) {
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
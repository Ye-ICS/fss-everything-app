package com.fss.everythingapp.simulations.doubleslit;

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

    // class instances
    private WaveDrawing waveDrawing;
    private BarrierWithSlit barrierWithSlit;

    /**
     * Constructs an ExplanationCanvas with the specified canvas.
     * @param canvas The JavaFX Canvas to draw on
     */
    ExplanationCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        initializeCanvasProperties();
        initializeRenderers();
    }
    
    /**
     * Initializes canvas dimensions and key positions.
     */
    private void initializeCanvasProperties() {
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();
        this.centerY = canvasHeight / 2;
        this.waveSourceX = 50;
        this.screenX = canvasWidth - 10;
    }

    /**
     * Initializes the class instances with canvas properties.
     */
    private void initializeRenderers() {
        waveDrawing = new WaveDrawing(gc, canvasHeight, waveSourceX, screenX);
        barrierWithSlit = new BarrierWithSlit(gc, canvasWidth, canvasHeight, waveSourceX);
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
        
        // Calculate key positions of slits
        double slitX = barrierWithSlit.calculateBarrierPosition(screenDistance);
        double[] slitYs = barrierWithSlit.calculateSlitPositions(slitSeparation);

        // Draw components
        drawWaveSource(wavelength);
        barrierWithSlit.drawBarrierWithSlits(slitX, slitYs);
        drawScreen();

        // Draw wave patterns if parameters are valid
        if (screenDistance > 0 && slitWidth > 0) {
            waveDrawing.drawWavePatterns(slitX, slitYs, wavelength, slitSeparation, slitWidth, screenDistance);
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
}
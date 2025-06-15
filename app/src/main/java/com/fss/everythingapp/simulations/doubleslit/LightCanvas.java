package com.fss.everythingapp.simulations.doubleslit;

import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LightCanvas {
    private Canvas canvas;
    private GraphicsContext gc;
    
    /**
     * The LightCanvas class is responsible for managing and interacting with a given Canvas object.
     * It initializes the GraphicsContext2D for drawing operations on the Canvas.
     *
     * @param canvas The Canvas object to be managed and used for drawing.
     */
    LightCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }
    
    /**
     * Draws an interference pattern on the canvas based on the given parameters.
     * The pattern is represented as a series of horizontal bars, where each bar's color and intensity correspond to the calculated interference pattern.
     *
     * @param pattern A list of intensity values representing the interference pattern.
     *                Each value corresponds to a point in the pattern.
     * @param wavelength The wavelength of the light used to generate the interference pattern.
     *                   This value determines the color of the pattern.
     * @param screenDistance The distance between the screen and the slits. Must be greater than 0.
     *                        If the value is less than or equal to 0, a message will be displayed instead of the pattern.
     * @param slitWidth The width of the slits used to generate the interference pattern.
     *                  Must be greater than 0. If the value is less than or equal to 0, a message will be displayed instead of the pattern.
     */
    void drawInterferencePattern(List<Double> pattern, double wavelength, double screenDistance, double slitWidth) {
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
        
        double maxIntensity = Utils.calculateMaxIntensity(pattern);
    
        // Calculate the height of each horizontal bar (pixel) to draw (rotated 90 degrees)
        double pixelHeight = canvasHeight / pattern.size();
        
        // Loop through each point in the pattern and draw a colored horizontal bar
        for (int i = 0; i < pattern.size(); i++) {
            // Normalize intensity to a value between 0 and 1
            double intensity = pattern.get(i) / maxIntensity;

            // Get the color corresponding to the current wavelength and intensity
            Color color = Utils.wavelengthToColor(wavelength, intensity);
            gc.setFill(color);
            
            // Calculate the y position for this bar (now horizontal bars stacked vertically)
            double y = i * pixelHeight;

            // Draw a horizontal rectangle representing the intensity at this point
            gc.fillRect(0, y, canvasWidth, pixelHeight + 1);
        }
    }
    
    /**
     * Draws a message on the canvas at the center position.
     *
     * @param message      The text message to be displayed.
     * @param canvasWidth  The width of the canvas.
     * @param canvasHeight The height of the canvas.
     */
    private void drawMessage(String message, double canvasWidth, double canvasHeight) {
        gc.setFill(Color.WHITE);
        gc.fillText(message, canvasWidth / 2 - message.length() * 2.5, canvasHeight / 2);
    }
}
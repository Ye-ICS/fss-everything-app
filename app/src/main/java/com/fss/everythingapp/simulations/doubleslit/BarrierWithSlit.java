package com.fss.everythingapp.simulations.doubleslit;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Handles barrier calculations and rendering of physical components
 * for the double-slit experiment (barrier, slits, screen positioning).
 */
public class BarrierWithSlit {
    private GraphicsContext gc;
    private double canvasWidth;
    private double canvasHeight;
    private double centerY;
    private double waveSourceX;

    /**
     * Constructs a BarrierWithSlit with the specified graphics context and canvas dimensions.
     * @param gc The JavaFX GraphicsContext to draw on
     * @param canvasWidth Width of the canvas
     * @param canvasHeight Height of the canvas
     * @param waveSourceX X position of the wave source
     */
    BarrierWithSlit(GraphicsContext gc, double canvasWidth, double canvasHeight, double waveSourceX) {
        this.gc = gc;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.centerY = canvasHeight / 2;
        this.waveSourceX = waveSourceX;
    }

    /**
     * Calculates the X position of the barrier based on screen distance.
     * @param screenDistance Physical distance to the screen in meters
     * @return X coordinate for barrier placement
     */
    double calculateBarrierPosition(double screenDistance) {
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
    double[] calculateSlitPositions(double slitSeparationMicrometers) {
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
    void drawBarrierWithSlits(double barrierCentreX, double[] slitYs) {
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
}
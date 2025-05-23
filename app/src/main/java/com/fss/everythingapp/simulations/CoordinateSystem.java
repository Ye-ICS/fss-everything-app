package com.fss.everythingapp.simulations;

/**
 * CoordinateSystem handles conversion between simulation (center-origin) coordinates
 * and JavaFX screen (top-left origin) coordinates.
 */

public class CoordinateSystem {
    /** Screen width in pixels */
    private double screenWidth;
    /** Screen height in pixels */
    private double screenHeight;
    /** Scale factor: pixels per simulation unit */
    private double pixelsPerUnit;

    /**
     * Constructs a coordinate system with given screen dimensions and scale.
     * @param screenWidth width of the display area in pixels
     * @param screenHeight height of the display area in pixels
     * @param pixelsPerUnit number of pixels representing one simulation unit
     */
    public CoordinateSystem(double screenWidth, double screenHeight, double pixelsPerUnit) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.pixelsPerUnit = pixelsPerUnit;
    }

    /**
     * Converts a simulation X coordinate (center-origin) to screen X (JavaFX) coordinate.
     * @param simX X in simulation space
     * @return X in screen space (pixels)
     */
    public double toScreenX(double simX) {
        return (screenWidth / 2.0) + simX * pixelsPerUnit;
    }

    /**
     * Converts a simulation Y coordinate (center-origin) to screen Y (JavaFX) coordinate.
     * Note: Y is inverted because screen Y grows downward.
     * @param simY Y in simulation space
     * @return Y in screen space (pixels)
     */
    public double toScreenY(double simY) {
        return (screenHeight / 2.0) - simY * pixelsPerUnit;
    }

    /**
     * Converts a screen X coordinate (JavaFX) to simulation X.
     * @param screenX X coordinate in pixels
     * @return X in simulation space
     */
    public double fromScreenX(double screenX) {
        return (screenX - (screenWidth / 2.0)) / pixelsPerUnit;
    }

    /**
     * Converts a screen Y coordinate (JavaFX) to simulation Y.
     * @param screenY Y coordinate in pixels
     * @return Y in simulation space
     */
    public double fromScreenY(double screenY) {
        return ((screenHeight / 2.0) - screenY) / pixelsPerUnit;
    }

    /**
     * Optionally, update screen dimensions (e.g., on resize).
     * @param screenWidth new width in pixels
     * @param screenHeight new height in pixels
     */
    public void updateScreenSize(double screenWidth, double screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * String representation for debugging.
     */
    @Override
    public String toString() {
        return String.format(
            "CoordinateSystem[screenWxH=%.0fx%.0f, scale=%.2fpx/unit]", 
            screenWidth, screenHeight, pixelsPerUnit
        );
    }

    public double getScreenWidth() {
        return screenWidth;
    }

    public double getScreenHeight() {
        return screenHeight;
    }

    public double getPixelsPerUnit() {
        return pixelsPerUnit;
    }
}
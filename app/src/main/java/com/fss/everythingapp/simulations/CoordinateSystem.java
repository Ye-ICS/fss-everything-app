public class CoordinateSystem {
    private final double screenWidth;     // Width of the canvas in pixels
    private final double screenHeight;    // Height of the canvas in pixels
    private final double pixelsPerUnit;   // Scale: how many pixels per one unit of simulation space
    import javafx.scene.canvas.GraphicsContext;
    import javafx.scene.paint.Color;

    /**
     * Constructor for the coordinate system.
     *
     * @param screenWidth     Width of the screen in pixels (e.g., 800)
     * @param screenHeight    Height of the screen in pixels (e.g., 600)
     * @param pixelsPerUnit   How many pixels represent one unit of simulation distance
     */
    public CoordinateSystem(double screenWidth, double screenHeight, double pixelsPerUnit) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.pixelsPerUnit = pixelsPerUnit;
    }

    /**
     * Converts a simulation-space X value to screen-space X (left to right).
     */
    public double toScreenX(double x) {
        return x * pixelsPerUnit;
    }

    /**
     * Converts a simulation-space Y value to screen-space Y.
     * Since JavaFX’s Y=0 is at the top, we flip it.
     */
    public double toScreenY(double y) {
        return screenHeight - y * pixelsPerUnit;
    }

    /**
     * Converts a screen-space X value to simulation-space X.
     */
    public double toSimX(double screenX) {
        return screenX / pixelsPerUnit;
    }

    /**
     * Converts a screen-space Y value to simulation-space Y.
     */
    public double toSimY(double screenY) {
        return (screenHeight - screenY) / pixelsPerUnit;
    }

    /**
     * @return How many pixels represent one simulation unit.
     */
    public double getPixelsPerUnit() {
        return pixelsPerUnit;
    }

    /**
     * @return Width of the screen in pixels
     */
    public double getScreenWidth() {
        return screenWidth;
    }

    /**
     * @return Height of the screen in pixels
     */
    public double getScreenHeight() {
        return screenHeight;
    }

    public void drawGrid(GraphicsContext gc, boolean drawAxes) {
    gc.setStroke(Color.LIGHTGRAY);
    gc.setLineWidth(1);

    // Draw vertical grid lines
    for (double x = 0; x <= screenWidth / pixelsPerUnit; x++) {
        double sx = toScreenX(x);
        gc.strokeLine(sx, 0, sx, screenHeight);
    }

    // Draw horizontal grid lines
    for (double y = 0; y <= screenHeight / pixelsPerUnit; y++) {
        double sy = toScreenY(y);
        gc.strokeLine(0, sy, screenWidth, sy);
    }

    if (drawAxes) {
        gc.setStroke(Color.DARKGRAY);
        gc.setLineWidth(2);

        // Draw X-axis
        double xAxisY = toScreenY(0);
        gc.strokeLine(0, xAxisY, screenWidth, xAxisY);

        // Draw Y-axis
        double yAxisX = toScreenX(0);
        gc.strokeLine(yAxisX, 0, yAxisX, screenHeight);
    }
}

    @Override
    public String toString() {
        return String.format("CoordinateSystem: screen=%.0fx%.0f px, scale=%.2f px/unit",
                screenWidth, screenHeight, pixelsPerUnit);
    }
}

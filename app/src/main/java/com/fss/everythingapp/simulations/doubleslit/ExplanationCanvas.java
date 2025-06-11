package com.fss.everythingapp.simulations.doubleslit;

import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ExplanationCanvas {
    private final Canvas canvas;
    private final GraphicsContext gc;
    
    private static Color barrierColour = Color.DARKGRAY;
    private static Color slitHighlightColour = Color.YELLOW;
    private static Color screenColour = Color.WHITE;

    
    private double animationTime = 0;
    private static double animationSpeed = 0.15;

    public ExplanationCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public void drawExplanation(double wavelength, double screenDistance, double slitWidth, double slitSeparation) {
        clearCanvas();
        
        // Calculate key positions
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();
        double centerY = canvasHeight / 2;
        double waveSourceX = 50;
        double screenX = canvasWidth - 30;
        double slitX = calculateBarrierPosition(waveSourceX, screenX, screenDistance);
        double[] slitYs = calculateSlitPositions(centerY, slitSeparation, canvasHeight);

        // Draw components
        drawWaveSource(waveSourceX, centerY, wavelength);
        drawBarrierWithSlits(slitX, slitYs, canvasHeight);
        drawScreen(screenX, canvasHeight);

        // Draw wave patterns if parameters are valid
        if (screenDistance > 0 && slitWidth > 0) {
            drawWavePatterns(waveSourceX, slitX, screenX, slitYs, wavelength, slitSeparation, slitWidth, screenDistance);
        }

        animationTime += animationSpeed;
    }

    private void clearCanvas() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private double calculateBarrierPosition(double sourceX, double screenX, double screenDistance) {
        double defaultRatio = 0.4;
        
        if (screenDistance <= 0) {
            return sourceX + (screenX - sourceX) * defaultRatio;
        }
        
        // Map screen distance (0-2m) to barrier position (40%-65%)
        double minRatio = defaultRatio;
        double maxRatio = 0.65;
        double normalizedDistance = Math.min(screenDistance / 2.0, 1.0);
        double positionRatio = minRatio + normalizedDistance * (maxRatio - minRatio);
        
        return sourceX + (screenX - sourceX) * positionRatio;
    }

    private void drawWaveSource(double x, double y, double wavelength) {
        Color baseWaveColor = Utils.wavelengthToColor(wavelength, 1.0);
        gc.setFill(baseWaveColor);
        gc.fillOval(x - 4, y - 4, 8, 8);
    }

    private double[] calculateSlitPositions(double centerY, double slitSeparationMicrometers, double canvasHeight) {
        double minVisualSeparation = 10.0;
        double maxVisualSeparation = Math.min(120.0, canvasHeight * 0.4);
        
        double visualSeparation = (slitSeparationMicrometers <= 0) ? minVisualSeparation : minVisualSeparation + (Math.min(slitSeparationMicrometers / 500.0, 1.0) * (maxVisualSeparation - minVisualSeparation));

        double slit1Y = centerY - visualSeparation / 2;
        double slit2Y = centerY + visualSeparation / 2;

        // Keep slits within canvas bounds
        double slitHeight = 30;
        double topBound = slitHeight / 2;
        double bottomBound = canvasHeight - slitHeight / 2;
        
        if (slit1Y < topBound) {
            double offset = topBound - slit1Y;
            slit1Y += offset;
            slit2Y += offset;
        } else if (slit2Y > bottomBound) {
            double offset = slit2Y - bottomBound;
            slit1Y -= offset;
            slit2Y -= offset;
        }
        
        return new double[]{slit1Y, slit2Y};
    }

    private void drawBarrierWithSlits(double x, double[] slitYs, double canvasHeight) {
        double barrierWidth = 10;
        double slitHeight = 30;

        // Draw barrier
        gc.setFill(barrierColour);
        gc.fillRect(x - barrierWidth / 2, 0, barrierWidth, canvasHeight);

        // Draw slits (cut-outs)
        gc.setFill(Color.BLACK);
        for (double slitY : slitYs) {
            gc.fillRect(x - barrierWidth / 2, slitY - slitHeight / 2, barrierWidth, slitHeight);
        }

        // Highlight slit edges
        gc.setStroke(slitHighlightColour);
        gc.setLineWidth(2);
        for (double slitY : slitYs) {
            gc.strokeRect(x - barrierWidth / 2, slitY - slitHeight / 2, barrierWidth, slitHeight);
        }
    }

    private void drawScreen(double x, double canvasHeight) {
        gc.setStroke(screenColour);
        gc.setLineWidth(3);
        gc.strokeLine(x, 10, x, canvasHeight - 10);
    }

    private void drawWavePatterns(double sourceX, double slitX, double screenX, double[] slitYs, double wavelength, double slitSeparation, double slitWidth, double screenDistance) {
        // Draw incident ray
        drawIncidentLightRay(wavelength, sourceX, slitYs, slitX, screenDistance);

        // Calculate interference pattern
        DoubleSlit doubleSlit = new DoubleSlit();
        doubleSlit.setWavelength(wavelength * 1e-9);
        doubleSlit.setSlitProperties(slitSeparation * 1e-6, slitWidth * 1e-6);
        doubleSlit.setScreenDistance(screenDistance);
        doubleSlit.calculateInterferencePattern();

        List<Double> pattern = doubleSlit.getInterferencePattern();
        drawWavesFromDoubleSlits(slitX, slitYs, screenX, pattern, wavelength);
    }

    private void drawIncidentLightRay(double wavelength, double sourceX, double[] slitYs, double slitX, double slitSeparation) {
        Color baseWaveColor = Utils.wavelengthToColor(wavelength, 1);
        gc.setFill(baseWaveColor);
        
        double centerY = (slitYs[0] + slitYs[1]) / 2;
        double rectHeight = Math.max(8, slitYs[1] - slitYs[0] + slitSeparation / 100.0);
        
        gc.fillRect(sourceX, centerY - rectHeight / 2, slitX - sourceX - 5, rectHeight);
    }

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

    private void drawCurvedWave(double x1, double y1, double x2, double y2, double intensity) {
        double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        int numPoints = Math.max(2, (int) (distance / 3));
        
        double amplitude = 2 + (intensity * 3);
        
        // Calculate perpendicular direction for wave oscillation
        double dx = y2 - y1;
        double dy = x1 - x2;
        double length = Math.sqrt(dx * dx + dy * dy);
        if (length > 0) {
            dx /= length;
            dy /= length;
        }

        // Draw wave segments
        double prevX = x1, prevY = y1;
        for (int i = 1; i < numPoints; i++) {
            double t = i / (double) (numPoints - 1);
            double x = x1 + t * (x2 - x1);
            double y = y1 + t * (y2 - y1);

            double wavePhase = (i * 25 / 100.0) + animationTime;
            double waveOffset = amplitude * Math.sin(wavePhase);
            
            double currentX = x + dx * waveOffset;
            double currentY = y + dy * waveOffset;
            
            gc.strokeLine(prevX, prevY, currentX, currentY);
            prevX = currentX;
            prevY = currentY;
        }
    }
}
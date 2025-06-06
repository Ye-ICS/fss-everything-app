package com.fss.everythingapp.simulations.doubleslit;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ExplanationCanvas {
    
    private Canvas canvas;
    private GraphicsContext gc;
    
    public ExplanationCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }
    
    public void drawExplanation(double wavelength, double screenDistance, double width) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.WHITE);
        gc.fillText("This is the explanation canvas.", canvas.getWidth()/2, canvas.getHeight()/2);
    }
}
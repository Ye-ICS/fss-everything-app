package com.fss.everythingapp.simulations.momentum;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

public class ControlPanel extends VBox {
    @SuppressWarnings("unused")
    public ControlPanel(Simulation sim, Puck puck1, Puck puck2) {
        setPadding(new Insets(15));
        setSpacing(15);
        setStyle("-fx-background-color: #efefef;");
        setPrefWidth(700);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(75);
        grid.getColumnConstraints().addAll(col1, col2);

        // ---- Elasticity ----
        Label elasticityLabel = new Label("Elasticity:");
        Slider elasticitySlider = new Slider(0, 1, sim.getElasticity());
        elasticitySlider.setShowTickLabels(true);
        elasticitySlider.setShowTickMarks(true);
        elasticitySlider.setMajorTickUnit(0.2);
        elasticitySlider.setBlockIncrement(0.1);
        elasticitySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            sim.setElasticity(newVal.doubleValue());
        });
        grid.add(elasticityLabel, 0, 0);
        grid.add(elasticitySlider, 1, 0);

        // ---- Mass Puck 1 ----
        Label mass1Label = new Label("Mass of Puck 1:");
        Slider massSlider1 = new Slider(1, 100, puck1.getMass());
        massSlider1.setShowTickLabels(true);
        massSlider1.setShowTickMarks(true);
        massSlider1.setBlockIncrement(1);
        massSlider1.valueProperty().addListener((obs, oldVal, newVal) -> {
            puck1.setMass(newVal.doubleValue());
        });
        grid.add(mass1Label, 0, 1);
        grid.add(massSlider1, 1, 1);

        // ---- Mass Puck 2 ----
        Label mass2Label = new Label("Mass of Puck 2:");
        Slider massSlider2 = new Slider(1, 100, puck2.getMass());
        massSlider2.setShowTickLabels(true);
        massSlider2.setShowTickMarks(true);
        massSlider2.setBlockIncrement(1);
        massSlider2.valueProperty().addListener((obs, oldVal, newVal) -> {
            puck2.setMass(newVal.doubleValue());
        });
        grid.add(mass2Label, 0, 2);
        grid.add(massSlider2, 1, 2);

        // ---- Velocity Puck 1 ----
        Label velocity1Label = new Label("Velocity (Puck 1):");
        velocity1Label.setFont(Font.font("System", 12));
        HBox velBox1 = new HBox(10);
        velBox1.setAlignment(Pos.CENTER_LEFT);

        Spinner<Double> speed1Spinner = new Spinner<>(0.0, 500.0, 100.0, 10.0);
        speed1Spinner.setEditable(true);
        Spinner<Double> angle1Spinner = new Spinner<>(0.0, 360.0, 0.0, 5.0);
        angle1Spinner.setEditable(true);
        Button apply1Btn = new Button("Apply");
        apply1Btn.setOnAction(e -> {
            double mag = speed1Spinner.getValue();
            double angleRad = Math.toRadians(angle1Spinner.getValue());
            double vx = mag * Math.cos(angleRad);
            double vy = mag * Math.sin(angleRad);
            puck1.setVelocity(new Vector2D(vx, vy));
        });
        velBox1.getChildren().addAll(new Label("Speed:"), speed1Spinner, new Label("Angle:"), angle1Spinner, apply1Btn);
        grid.add(velocity1Label, 0, 3);
        grid.add(velBox1, 1, 3);

        // ---- Velocity Puck 2 ----
        Label velocity2Label = new Label("Velocity (Puck 2):");
        velocity2Label.setFont(Font.font("System", 12));
        HBox velBox2 = new HBox(10);
        velBox2.setAlignment(Pos.CENTER_LEFT);

        Spinner<Double> speed2Spinner = new Spinner<>(0.0, 500.0, 100.0, 10.0);
        speed2Spinner.setEditable(true);
        Spinner<Double> angle2Spinner = new Spinner<>(0.0, 360.0, 180.0, 5.0);
        angle2Spinner.setEditable(true);
        Button apply2Btn = new Button("Apply");
        apply2Btn.setOnAction(e -> {
            double mag = speed2Spinner.getValue();
            double angleRad = Math.toRadians(angle2Spinner.getValue());
            double vx = mag * Math.cos(angleRad);
            double vy = mag * Math.sin(angleRad);
            puck2.setVelocity(new Vector2D(vx, vy));
        });
        velBox2.getChildren().addAll(new Label("Speed:"), speed2Spinner, new Label("Angle:"), angle2Spinner, apply2Btn);
        grid.add(velocity2Label, 0, 4);
        grid.add(velBox2, 1, 4);

        // ---- Upload Image Buttons ----
        Button uploadImage1Btn = new Button("Upload Image for Puck 1");
        Button uploadImage2Btn = new Button("Upload Image for Puck 2");

        uploadImage1Btn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image for Puck 1");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(getScene().getWindow());
            if (selectedFile != null) {
                Image img = new Image(selectedFile.toURI().toString());
                puck1.setImage(img);
            }
        });

        uploadImage2Btn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image for Puck 2");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(getScene().getWindow());
            if (selectedFile != null) {
                Image img = new Image(selectedFile.toURI().toString());
                puck2.setImage(img);
            }
        });

        VBox imageButtonsBox = new VBox(10, uploadImage1Btn, uploadImage2Btn);
        imageButtonsBox.setAlignment(Pos.CENTER_LEFT);
        grid.add(imageButtonsBox, 1, 6);

        // ---- Start/Stop Buttons ----
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        Button startBtn = new Button("Start");
        Button stopBtn = new Button("Stop");
        Button restetButton = new Button("Reset");
        startBtn.setOnAction(e -> sim.start());
        stopBtn.setOnAction(e -> sim.stop());
        restetButton.setOnAction(e -> {
            puck1.setPosition(new Vector2D(200, 300));
            puck1.setVelocity(new Vector2D(230, 100));
            puck2.setPosition(new Vector2D(400, 300));
            puck2.setVelocity(new Vector2D(-370, 200));
            sim.reset();
        });


        buttonBox.getChildren().addAll(startBtn, stopBtn, restetButton);
        grid.add(buttonBox, 1, 5);

        getChildren().add(grid);
    }
}

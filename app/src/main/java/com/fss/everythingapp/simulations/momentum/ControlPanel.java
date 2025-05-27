import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class ControlPanel extends VBox {
    public ControlPanel(Simulation sim) {
        setPadding(new Insets(10));
        setSpacing(10);
        setStyle("-fx-background-color: #efefef;");
        setPrefWidth(300);

        Label label = new Label("Elasticity:");
        Slider elasticitySlider = new Slider(0, 1, 1);
        elasticitySlider.setShowTickLabels(true);
        elasticitySlider.setShowTickMarks(true);
        elasticitySlider.setBlockIncrement(0.1);

        elasticitySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            sim.setElasticity(newVal.doubleValue());
        });

        Button startBtn = new Button("Start");
        Button stopBtn = new Button("Stop");

        startBtn.setOnAction(e -> sim.start());
        stopBtn.setOnAction(e -> sim.stop());

        getChildren().addAll(label, elasticitySlider, startBtn, stopBtn);
    }
}

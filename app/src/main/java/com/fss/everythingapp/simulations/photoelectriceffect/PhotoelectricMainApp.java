import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Main application class for the photoelectric effect simulation.
 * Sets up the UI layout with simulation pane on the left and controls on the right.
 */
public class PhotoelectricMainApp extends Application {
    private PhotoelectricSimulation simulation;
    private Pane simPane;
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        
        // Create simulation
        simulation = new PhotoelectricSimulation(600, 600);
        
        // Left: Simulation Pane
        simPane = new Pane();
        simPane.setStyle("-fx-background-color: linear-gradient(to bottom, #87CEEB 0%, #E0F6FF 100%);"); // Sky gradient
        simPane.setMinSize(600, 600);
        simPane.setPrefSize(600, 600);

        // Add title to simulation pane
        Text titleText = new Text(20, 30, "Photoelectric Effect Simulation");
        titleText.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleText.setFill(Color.DARKBLUE);
        
        Text instructionText = new Text(20, 50, "Photons (colored circles) travel from left to right and interact with electrons on the metal surface");
        instructionText.setFont(Font.font("System", 12));
        instructionText.setFill(Color.DARKBLUE);
        
        // Add metal surface to simulation pane
        simPane.getChildren().addAll(titleText, instructionText, simulation.getMetalSurface());
        
        root.setCenter(simPane);
        
        // Right: Control Panel
        PhotoelectricControlPanel controls = new PhotoelectricControlPanel(simulation);
        root.setRight(controls);
        
        // Set up dynamic addition/removal of photons and electrons
        setupDynamicElements();
        
        // Create scene
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Photoelectric Effect Simulation");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
    
    /**
     * Set up dynamic addition and removal of visual elements (photons and electrons)
     * as they are created and destroyed in the simulation
     */
    private void setupDynamicElements() {
        // Timeline to periodically check for new elements to add/remove
        javafx.animation.Timeline elementUpdateTimeline = new javafx.animation.Timeline(
            new javafx.animation.KeyFrame(
                javafx.util.Duration.millis(50), // Update every 50ms
                e -> updateVisualElements()
            )
        );
        elementUpdateTimeline.setCycleCount(javafx.animation.Timeline.INDEFINITE);
        elementUpdateTimeline.play();
    }
    
    /**
     * Update visual elements in the simulation pane based on current simulation state
     */
    private void updateVisualElements() {
        // Get current photons and electrons from simulation
        var currentPhotons = simulation.getPhotons();
        var currentElectrons = simulation.getElectrons();
        
        // Remove photons that are no longer in the simulation
        simPane.getChildren().removeIf(node -> {
            if (node instanceof Photon) {
                Photon photon = (Photon) node;
                return !currentPhotons.contains(photon) || photon.isAbsorbed();
            }
            return false;
        });
        
        // Remove electrons that are no longer in the simulation
        simPane.getChildren().removeIf(node -> {
            if (node instanceof Electron) {
                Electron electron = (Electron) node;
                return !currentElectrons.contains(electron);
            }
            return false;
        });
        
        // Add new photons that aren't already in the pane
        for (Photon photon : currentPhotons) {
            if (!simPane.getChildren().contains(photon) && !photon.isAbsorbed()) {
                simPane.getChildren().add(photon);
            }
        }
        
        // Add new electrons that aren't already in the pane
        for (Electron electron : currentElectrons) {
            if (!simPane.getChildren().contains(electron)) {
                simPane.getChildren().add(electron);
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
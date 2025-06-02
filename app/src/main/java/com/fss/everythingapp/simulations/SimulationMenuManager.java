package com.fss.everythingapp.simulations;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
public class SimulationMenuManager {
    
    @FXML
    BorderPane rootContainer;

    @FXML
    private void openProjectileMotion(ActionEvent actionEvent) throws IOException {
        Parent simulationsLayout = (Parent) FXMLLoader.load(getClass().getResource("../simulations/projectile_motion.fxml"));
        rootContainer.getScene().setRoot(simulationsLayout);
    }

    @FXML
    private void openDoubleSlit(ActionEvent actionEvent) throws IOException {
        Parent simulationsLayout = (Parent) FXMLLoader.load(getClass().getResource("../simulations/double_slit.fxml"));
        rootContainer.getScene().setRoot(simulationsLayout);
    }

    @FXML
    private void openMomentum(ActionEvent actionEvent) throws IOException {
        Parent simulationsLayout = (Parent) FXMLLoader.load(getClass().getResource("../simulations/momentum.fxml"));
        rootContainer.getScene().setRoot(simulationsLayout);
    }

    @FXML
    private void openPhotoElectric(ActionEvent actionEvent) throws IOException {
        Parent simulationsLayout = (Parent) FXMLLoader.load(getClass().getResource("../simulations/photoelectric.fxml"));
        rootContainer.getScene().setRoot(simulationsLayout);
    }

    @FXML
    private void Back(ActionEvent actionEvent) throws IOException {
        Parent mainMenuLayout = (Parent) FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/app/fxml/Example.fxml")); //I will make work later... i tried to use Claude, CoPilot, ChatGPT, You name it, it didn't work, losing my mind.
        rootContainer.getScene().setRoot(mainMenuLayout);
    }

    
}

// SimulationManager handles the lifecycle of a simulation

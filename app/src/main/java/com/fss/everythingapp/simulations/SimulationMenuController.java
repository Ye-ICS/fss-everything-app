package com.fss.everythingapp.simulations;

import java.io.IOException;

import com.fss.everythingapp.app.MainMenuController;
import com.fss.everythingapp.simulations.doubleslit.DoubleSlitController;
import com.fss.everythingapp.simulations.projectilemotion.ProjectileController;
import com.fss.everythingapp.simulations.photoelectriceffect.PhotoelectricRoot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class SimulationMenuController {
    
    @FXML
    BorderPane rootContainer;

    @FXML
    private void openProjectileMotion(ActionEvent actionEvent) throws IOException {
        Parent simulationsLayout;
       
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../simulations/projectile_motion.fxml"));
        ProjectileController controller = new ProjectileController();
        fxmlLoader.setController(controller);
        simulationsLayout = fxmlLoader.load();
        rootContainer.getScene().setRoot(simulationsLayout);
    }

    @FXML
    private void openDoubleSlit(ActionEvent actionEvent) throws IOException {
        Parent simulationsLayout;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../simulations/DoubleSlitSimulation.fxml"));
        //Rad already put a controller in the FXML, so theres no need to load a controller.
        simulationsLayout = fxmlLoader.load();
        rootContainer.getScene().setRoot(simulationsLayout);
    }

    @FXML
    private void openMomentum(ActionEvent actionEvent) throws IOException {
        com.fss.everythingapp.simulations.momentum.MomentumRoot momentumRoot = new com.fss.everythingapp.simulations.momentum.MomentumRoot();
        rootContainer.getScene().setRoot(momentumRoot.getRootNode());
    }

    @FXML
    private void openPhotoElectric(ActionEvent actionEvent) {
        com.fss.everythingapp.simulations.photoelectriceffect.PhotoelectricRoot photoelectricRoot = new com.fss.everythingapp.simulations.photoelectriceffect.PhotoelectricRoot();
        rootContainer.getScene().setRoot(photoelectricRoot.getRootNode());
    }

    @FXML
    private void back(ActionEvent actionEvent) throws IOException {
        Parent mainMenuLayout = (Parent) FXMLLoader.load(getClass().getResource("/com/fss/everythingapp/app/fxml/Example.fxml")); //I will make work later... i tried to use Claude, CoPilot, ChatGPT, You name it, it didn't work, losing my mind.
        rootContainer.getScene().setRoot(mainMenuLayout);
    }

    
}

// SimulationManager handles the lifecycle of a simulation

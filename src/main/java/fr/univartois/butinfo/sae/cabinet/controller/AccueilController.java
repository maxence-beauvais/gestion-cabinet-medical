package fr.univartois.butinfo.sae.cabinet.controller;

import fr.univartois.butinfo.sae.cabinet.model.modele.CabinetFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AccueilController {
    private CabinetFacade facade;
    private Stage stage;
    private Scene sceneAccueil; // Pour y revenir plus tard

    public void initContext(Stage stage, Scene sceneAccueil, CabinetFacade facade) {
        this.stage = stage;
        this.sceneAccueil = sceneAccueil;
        this.facade = facade;
    }

    @FXML
    void ouvrirGestionPatients(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr.univartois.butinfo.sae.cabinet.view/PatientListView.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            PatientListController controller = loader.getController();
            controller.initContext(stage, sceneAccueil, facade);

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void ouvrirGestionMedecins(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr.univartois.butinfo.sae.cabinet.view/MedecinListView.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            MedecinListController controller = loader.getController();
            controller.initContext(stage, sceneAccueil, facade);

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ouvrirGestionMedicaments(ActionEvent event) {
        /**
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr.univartois.butinfo.sae.cabinet.view/MedicamentListView.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            MedicamentListController controller = loader.getController();
            controller.initContext(stage, sceneAccueil, facade);

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

    @FXML
    void ouvrirGestionRdv(ActionEvent event) {
        /**
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr.univartois.butinfo.sae.cabinet.view/RendezVousListView.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            RendezVousListController controller = loader.getController();
            controller.initContext(stage, sceneAccueil, facade);

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

    @FXML
    void ouvrirGestionOrdonnances(ActionEvent event) {
        /**
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr.univartois.butinfo.sae.cabinet.view/OrdonnanceListView.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            OrdonnanceListController controller = loader.getController();
            controller.initContext(stage, sceneAccueil, facade);

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

}
package fr.univartois.butinfo.sae.cabinet.controller;

import fr.univartois.butinfo.sae.cabinet.model.modele.CabinetFacade;
import fr.univartois.butinfo.sae.cabinet.model.modele.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;

public class PatientListController {
    @FXML private ListView<Patient> listViewPatients;
    @FXML private Label lblNom, lblPrenom, lblSecu;

    private Stage stage;
    private Scene sceneAccueil;
    private Scene maSceneActuelle;
    private CabinetFacade facade;

    public void initContext(Stage stage, Scene sceneAccueil, CabinetFacade facade) {
        this.stage = stage;
        this.sceneAccueil = sceneAccueil;
        this.facade = facade;
        this.maSceneActuelle = stage.getScene();

        listViewPatients.setItems(facade.getGestionPatients().getPatients());

        listViewPatients.setCellFactory(list -> new ListCell<>() {
            @Override
            public void updateItem(Patient patient, boolean empty) {
                super.updateItem(patient, empty);
                if (empty || patient == null) {
                    setText(null);
                } else {
                    setText(patient.getNomComplet());
                }
            }
        });

        listViewPatients.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> afficherDetails(newV));
    }

    private void afficherDetails(Patient patient) {
        if (patient != null) {
            lblNom.setText("Nom : " + patient.getNom());
            lblPrenom.setText("Prénom : " + patient.getPrenom());
            lblSecu.setText("N° Sécu : " + patient.getNumeroSecu());
        } else {
            lblNom.setText("Nom : ");
            lblPrenom.setText("Prénom : ");
            lblSecu.setText("N° Sécu : ");
        }
    }

    @FXML
    void ajouterPatient(ActionEvent event) {
        ouvrirFormulaire(null);
    }

    @FXML
    void modifierPatient(ActionEvent event) {
        Patient selection = listViewPatients.getSelectionModel().getSelectedItem();
        if (selection != null) {
            ouvrirFormulaire(selection);
        }
    }

    @FXML
    void supprimerPatient(ActionEvent event) {
        Patient selection = listViewPatients.getSelectionModel().getSelectedItem();
        if (selection != null) {
            facade.getGestionPatients().supprimerPatient(selection.getId());
        }
    }

    @FXML
    void retourAccueil(ActionEvent event) {
        stage.setScene(sceneAccueil);
    }

    private void ouvrirFormulaire(Patient patient) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr.univartois.butinfo.sae.cabinet.view/PatientFormView.fxml"));
            Scene sceneForm = new Scene(loader.load(), 400, 300);

            PatientFormController controller = loader.getController();
            controller.initContext(stage, maSceneActuelle, facade, patient);

            stage.setScene(sceneForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
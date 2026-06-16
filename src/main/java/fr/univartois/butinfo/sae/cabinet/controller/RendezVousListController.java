package fr.univartois.butinfo.sae.cabinet.controller;

import fr.univartois.butinfo.sae.cabinet.model.modele.CabinetFacade;
import fr.univartois.butinfo.sae.cabinet.model.modele.RendezVous;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class RendezVousListController {

    @FXML private ListView<RendezVous> listViewRdv;
    @FXML private Label lblPatient, lblMedecin, lblDate, lblMotif;

    private Stage stage;
    private Scene sceneAccueil;
    private Scene maSceneActuelle;
    private CabinetFacade facade;

    public void initContext(Stage stage, Scene sceneAccueil, CabinetFacade facade) {
        this.stage = stage;
        this.sceneAccueil = sceneAccueil;
        this.facade = facade;
        this.maSceneActuelle = stage.getScene();

        listViewRdv.setItems(facade.getGestionRendezVous().listerTousLesRendezVous());

        listViewRdv.setCellFactory(list -> new ListCell<>() {
            @Override
            public void updateItem(RendezVous rdv, boolean empty) {
                super.updateItem(rdv, empty);
                if (empty || rdv == null) {
                    setText(null);
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    setText(rdv.getDateHeure().format(formatter) + " - " + rdv.getPatient().getNomComplet());
                }
            }
        });

        listViewRdv.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> afficherDetails(newV));
    }

    private void afficherDetails(RendezVous rdv) {
        if (rdv != null) {
            lblPatient.setText("Patient : " + rdv.getPatient().getNomComplet());
            lblMedecin.setText("Médecin : Dr " + rdv.getMedecin().getNomComplet());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
            lblDate.setText("Date et heure : " + rdv.getDateHeure().format(formatter));
            lblMotif.setText("Motif : " + rdv.getMotif() + " (" + rdv.getStatut().getLibelle() + ")");
        } else {
            lblPatient.setText("Patient : ");
            lblMedecin.setText("Médecin : ");
            lblDate.setText("Date et heure : ");
            lblMotif.setText("Motif : ");
        }
    }

    @FXML
    void ajouter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr.univartois.butinfo.sae.cabinet.view/RendezVousFormView.fxml"));
            Scene sceneForm = new Scene(loader.load(), 400, 350);

            RendezVousFormController controller = loader.getController();
            controller.initContext(stage, maSceneActuelle, facade);

            stage.setScene(sceneForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void annulerRdv(ActionEvent event) {
        RendezVous selection = listViewRdv.getSelectionModel().getSelectedItem();
        if (selection != null) {
            facade.getGestionRendezVous().annulerRendezVous(selection.getId());
            listViewRdv.refresh();
            afficherDetails(selection);
        }
    }

    @FXML
    void retour(ActionEvent event) {
        stage.setScene(sceneAccueil);
    }
}
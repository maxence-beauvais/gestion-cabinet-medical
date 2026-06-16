package fr.univartois.butinfo.sae.cabinet.controller;

import fr.univartois.butinfo.sae.cabinet.model.modele.CabinetFacade;
import fr.univartois.butinfo.sae.cabinet.model.modele.Ordonnance;
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

public class OrdonnanceListController {

    @FXML private ListView<Ordonnance> listViewOrdos;
    @FXML private Label lblPatient, lblMedecin, lblDates, lblRemarques;

    private Stage stage;
    private Scene sceneAccueil;
    private Scene maSceneActuelle;
    private CabinetFacade facade;

    public void initContext(Stage stage, Scene sceneAccueil, CabinetFacade facade) {
        this.stage = stage;
        this.sceneAccueil = sceneAccueil;
        this.facade = facade;
        this.maSceneActuelle = stage.getScene();

        listViewOrdos.setItems(facade.getGestionOrdonnances().listerToutesLesOrdonnances());

        listViewOrdos.setCellFactory(list -> new ListCell<>() {
            @Override
            public void updateItem(Ordonnance ordo, boolean empty) {
                super.updateItem(ordo, empty);
                if (empty || ordo == null) {
                    setText(null);
                } else {
                    setText("Ordonnance n°" + ordo.getId() + " - " + ordo.getPatient().getNomComplet());
                }
            }
        });

        listViewOrdos.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> afficherDetails(newV));
    }

    private void afficherDetails(Ordonnance ordo) {
        if (ordo != null) {
            lblPatient.setText("Patient : " + ordo.getPatient().getNomComplet());
            lblMedecin.setText("Médecin : Dr " + ordo.getMedecin().getNomComplet());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            lblDates.setText("Émise le : " + ordo.getDateEmission().format(formatter) + " | Valide jusqu'au : " + ordo.getDateValidite().format(formatter));
            lblRemarques.setText("Remarques : " + (ordo.getRemarques().isEmpty() ? "Aucune" : ordo.getRemarques()));
        } else {
            lblPatient.setText("Patient : ");
            lblMedecin.setText("Médecin : ");
            lblDates.setText("Émise le : ");
            lblRemarques.setText("Remarques : ");
        }
    }

    @FXML
    void ajouter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr.univartois.butinfo.sae.cabinet.view/OrdonnanceFormView.fxml"));
            Scene sceneForm = new Scene(loader.load(), 400, 350);

            OrdonnanceFormController controller = loader.getController();
            controller.initContext(stage, maSceneActuelle, facade);

            stage.setScene(sceneForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void retour(ActionEvent event) {
        stage.setScene(sceneAccueil);
    }
}
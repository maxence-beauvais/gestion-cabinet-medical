package fr.univartois.butinfo.sae.cabinet.controller;

import fr.univartois.butinfo.sae.cabinet.model.modele.CabinetFacade;
import fr.univartois.butinfo.sae.cabinet.model.modele.Medecin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class MedecinListController {
    @FXML private ListView<Medecin> listViewMedecins;
    @FXML private Label lblNom, lblPrenom, lblSpecialite, lblOrdre, lblPrix;

    private Stage stage;
    private Scene sceneAccueil;
    private Scene maScene;
    private CabinetFacade facade;

    public void initContext(Stage stage, Scene sceneAccueil, CabinetFacade facade) {
        this.stage = stage;
        this.sceneAccueil = sceneAccueil;
        this.facade = facade;
        this.maScene = stage.getScene();

        listViewMedecins.setItems(facade.getGestionMedecins().listerTousLesMedecins());
        listViewMedecins.setCellFactory(list -> new ListCell<>() {
            @Override
            public void updateItem(Medecin m, boolean empty) {
                super.updateItem(m, empty);
                setText((empty || m == null) ? null : m.getNomComplet() + " - " + m.getSpecialite());
            }
        });

        listViewMedecins.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> afficherDetails(newV));
    }

    private void afficherDetails(Medecin m) {
        if (m != null) {
            lblNom.setText("Nom : " + m.getNom());
            lblPrenom.setText("Prénom : " + m.getPrenom());
            lblSpecialite.setText("Spécialité : " + m.getSpecialite().getLibelle());
            lblOrdre.setText("N° Ordre : " + m.getNumeroOrdre());
            lblPrix.setText("Prix consultation : " + m.getTarifHoraire());
        }
    }

    @FXML void ajouter(ActionEvent event) { ouvrirFormulaire(null); }
    @FXML void modifier(ActionEvent event) { ouvrirFormulaire(listViewMedecins.getSelectionModel().getSelectedItem()); }
    @FXML void supprimer(ActionEvent event) {
        Medecin sel = listViewMedecins.getSelectionModel().getSelectedItem();
        if (sel != null) facade.getGestionMedecins().supprimerMedecin(sel.getId());
    }
    @FXML void retourAccueil(ActionEvent event) { stage.setScene(sceneAccueil); }

    private void ouvrirFormulaire(Medecin m) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr.univartois.butinfo.sae.cabinet.view/MedecinFormView.fxml"));
            stage.setScene(new Scene(loader.load(), 400, 350));
            MedecinFormController controller = loader.getController();
            controller.initContext(stage, maScene, facade, m);
        } catch (IOException e) { e.printStackTrace(); }
    }
}
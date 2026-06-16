package fr.univartois.butinfo.sae.cabinet.controller;

import fr.univartois.butinfo.sae.cabinet.model.modele.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class MedicamentListController {
    @FXML private ListView<Medicament> listViewMedicaments;
    @FXML private Label lblNom, lblPrix, lblDesc;
    private Stage stage; private Scene sceneAccueil, maScene; private CabinetFacade facade;

    public void initContext(Stage stage, Scene sceneAccueil, CabinetFacade facade) {
        this.stage = stage; this.sceneAccueil = sceneAccueil; this.facade = facade; this.maScene = stage.getScene();
        listViewMedicaments.setItems(facade.getGestionMedicaments().listerTousLesMedicaments());
        listViewMedicaments.setCellFactory(list -> new ListCell<>() {
            @Override public void updateItem(Medicament m, boolean empty) {
                super.updateItem(m, empty); setText((empty || m == null) ? null : m.getNom());
            }
        });
        listViewMedicaments.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> afficher(n));
    }

    private void afficher(Medicament m) {
        if (m != null) { lblNom.setText("Nom : " + m.getNom()); lblPrix.setText("Prix : " + m.getPrix() + " €"); lblDesc.setText("Desc : " + m.getDescription()); }
    }
    
    @FXML void ajouter(ActionEvent event) { ouvrir(null); }
    @FXML void modifier(ActionEvent event) { ouvrir(listViewMedicaments.getSelectionModel().getSelectedItem()); }
    @FXML void supprimer(ActionEvent event) {
        Medicament m = listViewMedicaments.getSelectionModel().getSelectedItem();
        if (m != null) facade.getGestionMedicaments().supprimerMedicament(m.getId());
    }
    @FXML void retour(ActionEvent event) { stage.setScene(sceneAccueil); }

    private void ouvrir(Medicament m) {
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("/fr.univartois.butinfo.sae.cabinet.view/MedicamentFormView.fxml"));
            stage.setScene(new Scene(l.load(), 400, 300));
            ((MedicamentFormController)l.getController()).initContext(stage, maScene, facade, m);
        } catch (IOException e) { e.printStackTrace(); }
    }
}
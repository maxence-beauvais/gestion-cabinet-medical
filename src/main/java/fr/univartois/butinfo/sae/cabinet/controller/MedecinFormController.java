package fr.univartois.butinfo.sae.cabinet.controller;

import fr.univartois.butinfo.sae.cabinet.model.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.model.modele.CabinetFacade;
import fr.univartois.butinfo.sae.cabinet.model.modele.Medecin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MedecinFormController {
    @FXML private TextField txtNom, txtPrenom, txtOrdre, txtTarif;
    @FXML private ComboBox<Specialite> comboSpecialite;

    private Stage stage;
    private Scene scenePrecedente;
    private CabinetFacade facade;
    private Medecin medecinEdite;

    public void initContext(Stage stage, Scene scenePrecedente, CabinetFacade facade, Medecin m) {
        this.stage = stage;
        this.scenePrecedente = scenePrecedente;
        this.facade = facade;
        this.medecinEdite = m;

        comboSpecialite.getItems().setAll(Specialite.values());

        if (m != null) {
            txtNom.setText(m.getNom());
            txtPrenom.setText(m.getPrenom());
            txtOrdre.setText(m.getNumeroOrdre());
            txtTarif.setText(String.valueOf(m.getTarifHoraire()));
            comboSpecialite.getSelectionModel().select(m.getSpecialite());
            txtOrdre.setDisable(true);
        }
    }

    @FXML void valider(ActionEvent event) {
        double tarif = Double.parseDouble(txtTarif.getText());
        if (medecinEdite == null) {
            Medecin nouveau = new Medecin(txtNom.getText(), txtPrenom.getText(), "", "", txtOrdre.getText(), comboSpecialite.getValue(), tarif);
            facade.getGestionMedecins().ajouterMedecin(nouveau);
        } else {
            facade.getGestionMedecins().mettreAJourMedecin(medecinEdite.getId(), txtNom.getText(), txtPrenom.getText(), tarif, "", "", comboSpecialite.getValue());
            listViewRefresh(medecinEdite);
        }
        stage.setScene(scenePrecedente);
    }

    private void listViewRefresh(Medecin m) {
        int idx = facade.getGestionMedecins().listerTousLesMedecins().indexOf(m);
        if(idx >= 0) facade.getGestionMedecins().listerTousLesMedecins().set(idx, m);
    }

    @FXML void annuler(ActionEvent event) { stage.setScene(scenePrecedente); }
}
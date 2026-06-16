package fr.univartois.butinfo.sae.cabinet.controller;

import fr.univartois.butinfo.sae.cabinet.model.modele.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MedicamentFormController {
    @FXML private TextField txtNom, txtDesc, txtPrix;
    private Stage stage; private Scene scenePrecedente; private CabinetFacade facade; private Medicament med;

    public void initContext(Stage stage, Scene scenePrecedente, CabinetFacade facade, Medicament med) {
        this.stage = stage; this.scenePrecedente = scenePrecedente; this.facade = facade; this.med = med;
        if (med != null) { txtNom.setText(med.getNom()); txtDesc.setText(med.getDescription()); txtPrix.setText(String.valueOf(med.getPrix())); }
    }

    @FXML void valider(ActionEvent event) {
        if (med == null) {
            facade.getGestionMedicaments().ajouterMedicament(new Medicament(txtNom.getText(), txtDesc.getText(), Double.parseDouble(txtPrix.getText())));
        } else {
            med.setNom(txtNom.getText()); med.setDescription(txtDesc.getText()); med.setPrix(Double.parseDouble(txtPrix.getText()));
            int idx = facade.getGestionMedicaments().listerTousLesMedicaments().indexOf(med);
            if(idx >= 0) facade.getGestionMedicaments().listerTousLesMedicaments().set(idx, med);
        }
        stage.setScene(scenePrecedente);
    }
    @FXML void annuler(ActionEvent event) { stage.setScene(scenePrecedente); }
}
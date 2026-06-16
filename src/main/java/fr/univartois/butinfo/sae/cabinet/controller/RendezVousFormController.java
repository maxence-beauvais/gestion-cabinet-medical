package fr.univartois.butinfo.sae.cabinet.controller;

import fr.univartois.butinfo.sae.cabinet.model.modele.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDateTime;

public class RendezVousFormController {
    @FXML private ComboBox<Patient> comboPatient;
    @FXML private ComboBox<Medecin> comboMedecin;
    @FXML private TextField txtDate, txtMotif;

    private Stage stage; private Scene scenePrecedente; private CabinetFacade facade;

    public void initContext(Stage stage, Scene scenePrecedente, CabinetFacade facade) {
        this.stage = stage; this.scenePrecedente = scenePrecedente; this.facade = facade;
        comboPatient.setItems(facade.getGestionPatients().getPatients());
        comboMedecin.setItems(facade.getGestionMedecins().listerTousLesMedecins());
    }

    @FXML void valider(ActionEvent event) {
        Patient p = comboPatient.getValue();
        Medecin m = comboMedecin.getValue();
        LocalDateTime dt = LocalDateTime.parse(txtDate.getText());

        if (p != null && m != null) {
            facade.getGestionRendezVous().prendreRendezVous(p, m, dt, 30, txtMotif.getText());
        }
        stage.setScene(scenePrecedente);
    }

    @FXML void annuler(ActionEvent event) { stage.setScene(scenePrecedente); }
}
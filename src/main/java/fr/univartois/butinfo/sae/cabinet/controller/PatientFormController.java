package fr.univartois.butinfo.sae.cabinet.controller;

import fr.univartois.butinfo.sae.cabinet.model.modele.CabinetFacade;
import fr.univartois.butinfo.sae.cabinet.model.modele.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.LocalDate;

public class PatientFormController {
    @FXML private TextField txtNom, txtPrenom, txtSecu;

    private Stage stage;
    private Scene scenePrecedente;
    private CabinetFacade facade;
    private Patient patientEdite;

    public void initContext(Stage stage, Scene scenePrecedente, CabinetFacade facade, Patient patient) {
        this.stage = stage;
        this.scenePrecedente = scenePrecedente;
        this.facade = facade;
        this.patientEdite = patient;

        if (patientEdite != null) {
            txtNom.setText(patientEdite.getNom());
            txtPrenom.setText(patientEdite.getPrenom());
            txtSecu.setText(patientEdite.getNumeroSecu());
        }
    }

    @FXML
    void valider(ActionEvent event) {
        if (patientEdite == null) {
            Patient nouveau = new Patient(txtNom.getText(), txtPrenom.getText(), "", "", "", LocalDate.now(), txtSecu.getText());
            facade.getGestionPatients().ajouterPatient(nouveau);
        } else {
            facade.getGestionPatients().mettreAJourPatient(patientEdite.getId(), txtNom.getText(), txtPrenom.getText(), "", "", "", 0.75);
            int index = facade.getGestionPatients().getPatients().indexOf(patientEdite);
            if (index >= 0) {
                facade.getGestionPatients().getPatients().set(index, patientEdite);
            }
        }
        retourner();
    }

    @FXML
    void annuler(ActionEvent event) {
        retourner();
    }

    private void retourner() {
        stage.setScene(scenePrecedente);
    }
}
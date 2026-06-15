package fr.univartois.butinfo.sae.cabinet.utils;

import fr.univartois.butinfo.sae.cabinet.model.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.model.modele.Medecin;
import fr.univartois.butinfo.sae.cabinet.model.modele.Patient;
import fr.univartois.butinfo.sae.cabinet.model.service.GestionMedecins;
import fr.univartois.butinfo.sae.cabinet.model.service.GestionPatients;
import fr.univartois.butinfo.sae.cabinet.model.utils.Recherche;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RechercheTest {

    @Mock
    private GestionPatients gestionPatients;

    @Mock
    private GestionMedecins gestionMedecins;

    @InjectMocks
    private Recherche recherche;

    @Mock
    private Patient patient;

    @Mock
    private Medecin medecin;

    @Test
    void testRechercherPatientParNomTrouve() {
        when(gestionPatients.rechercherParNom("leblanc")).thenReturn(List.of(patient));

        List<Patient> resultats = recherche.rechercherPatientParNom("leblanc");

        assertThat(resultats).hasSize(1).contains(patient);
    }

    @Test
    void testRechercherPatientParNomVide() {
        when(gestionPatients.rechercherParNom("inconnu")).thenReturn(Collections.emptyList());

        List<Patient> resultats = recherche.rechercherPatientParNom("inconnu");

        assertThat(resultats).isEmpty();
    }

    @Test
    void testRechercherPatientParSecuEchec() {
        when(gestionPatients.rechercherParNumeroSecu("0000")).thenReturn(null);

        Patient resultat = recherche.rechercherPatientParSecu("0000");

        assertThat(resultat).isNull();
    }

    @Test
    void testRechercherMedecinParSpecialite() {
        when(gestionMedecins.rechercherParSpecialite(Specialite.CARDIOLOGUE)).thenReturn(List.of(medecin));

        List<Medecin> resultats = recherche.rechercherMedecinParSpecialite(Specialite.CARDIOLOGUE);

        assertThat(resultats).hasSize(1).contains(medecin);
    }
}
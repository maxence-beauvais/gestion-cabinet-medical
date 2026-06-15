package fr.univartois.butinfo.sae.cabinet.model.modele;

import fr.univartois.butinfo.sae.cabinet.model.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.model.modele.Medecin;
import fr.univartois.butinfo.sae.cabinet.model.modele.Patient;
import fr.univartois.butinfo.sae.cabinet.model.modele.Medicament;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrdonnanceTest {

    @Mock
    private Medecin medecin;

    @Mock
    private Patient patient;

    @Mock
    private Medicament medicament;

    @Test
    void testCreationOrdonnanceEtGetters() {
        LocalDate dateValidite = LocalDate.now().plusMonths(3);
        Ordonnance ordonnance = new Ordonnance(medecin, patient, dateValidite, "Prendre au repas");

        assertThat(ordonnance.getId()).isEqualTo(1);
        assertThat(ordonnance.getMedecin()).isEqualTo(medecin);
        assertThat(ordonnance.getPatient()).isEqualTo(patient);
        assertThat(ordonnance.getDateValidite()).isEqualTo(dateValidite);
        assertThat(ordonnance.getRemarques()).isEqualTo("Prendre au repas");
    }

    @Test
    void testToStringContientLesInformations() {
        LocalDate dateValidite = LocalDate.of(2026, 12, 31);

        // Configuration des mocks pour éviter le NullPointerException dans le toString()
        when(medecin.getNomComplet()).thenReturn("Jean Dupont");
        when(medecin.getSpecialite()).thenReturn(Specialite.GENERALISTE); // Empêche le getSpecialite() d'être null
        when(patient.getNomComplet()).thenReturn("Paul Martin");

        Ordonnance ordonnance = new Ordonnance(medecin, patient, dateValidite, "Prendre au repas");
        String renduTextuel = ordonnance.toString();

        assertThat(renduTextuel)
                .contains("Dr Jean Dupont")
                .contains("Paul Martin")
                .contains("2026-12-31");
    }
    
}


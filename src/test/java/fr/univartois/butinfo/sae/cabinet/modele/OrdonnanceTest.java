package fr.univartois.butinfo.sae.cabinet.modele;

import fr.univartois.butinfo.sae.cabinet.model.modele.Medecin;
import fr.univartois.butinfo.sae.cabinet.model.modele.Patient;
import fr.univartois.butinfo.sae.cabinet.model.modele.Medicament;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

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
        Ordonnance ordonnance = new Ordonnance(1, medecin, patient, dateValidite, "Prendre au repas");

        assertThat(ordonnance.getId()).isEqualTo(1);
        assertThat(ordonnance.getMedecin()).isEqualTo(medecin);
        assertThat(ordonnance.getPatient()).isEqualTo(patient);
        assertThat(ordonnance.getDateValidite()).isEqualTo(dateValidite);
        assertThat(ordonnance.getInstructionsGenerales()).isEqualTo("Prendre au repas");
    }

    @Test
    void testToStringContientLesInformations() {
        LocalDate dateValidite = LocalDate.of(2026, 12, 31);
        Ordonnance ordonnance = new Ordonnance(2, medecin, patient, dateValidite, "Pas de sport");

        assertThat(ordonnance.toString())
                .contains("2")
                .contains("Pas de sport");
    }
    
}


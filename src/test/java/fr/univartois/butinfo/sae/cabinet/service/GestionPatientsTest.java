package fr.univartois.butinfo.sae.cabinet.service;

import fr.univartois.butinfo.sae.cabinet.modele.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GestionPatientsTest {

    @InjectMocks
    private GestionPatients gestionPatients;

    @Mock
    private Patient mockPatient;

    @Test
    void testAjouterEtListerPatient() {
        assertThat(gestionPatients.getNombrePatients()).isZero();

        gestionPatients.ajouterPatient(mockPatient);

        assertThat(gestionPatients.getNombrePatients()).isEqualTo(1);
        List<Patient> liste = gestionPatients.listerTousLesPatients();
        assertThat(liste).contains(mockPatient);
    }

    @Test
    void testSupprimerPatient() {
        when(mockPatient.getId()).thenReturn(10);

        gestionPatients.ajouterPatient(mockPatient);
        assertThat(gestionPatients.getNombrePatients()).isEqualTo(1);

        gestionPatients.supprimerPatient(10);
        assertThat(gestionPatients.getNombrePatients()).isZero();
    }

    @Test
    void testMettreAJourPatient() {
        Patient reelPatient = new Patient("Leblanc", "Paul", "0612345678", "p.leblanc@mail.fr",
                "Arras", LocalDate.of(1985, 3, 22), "185036562012345");

        gestionPatients.ajouterPatient(reelPatient);
        int id = reelPatient.getId();

        gestionPatients.mettreAJourPatient(id, "Leblanc", "Paul-Henri", "0699887766",
                "p.leblanc@newmail.fr", "Lens", 0.80);

        Patient maj = gestionPatients.rechercherParNumeroSecu("185036562012345");
        assertThat(maj).isNotNull();
        assertThat(maj.getPrenom()).isEqualTo("Paul-Henri");
        assertThat(maj.getTelephone()).isEqualTo("0699887766");
    }
}
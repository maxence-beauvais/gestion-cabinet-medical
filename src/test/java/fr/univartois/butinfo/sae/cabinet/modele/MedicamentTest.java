package fr.univartois.butinfo.sae.cabinet.modele;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MedicamentTest {

    @Test
    void testConstructeurEtGetters() {
        Medicament medicament = new Medicament("Doliprane 1000mg", "Antalgique", 5.5);

        assertThat(medicament.getId()).isPositive();
        assertThat(medicament.getNom()).isEqualTo("Doliprane 1000mg");
        assertThat(medicament.getDescription()).isEqualTo("Antalgique");
        assertThat(medicament.getPrix()).isEqualTo(5.5);
    }

    @Test
    void testSetters() {
        Medicament medicament = new Medicament("Doliprane 1000mg", "Antalgique", 5.5);

        medicament.setNom("Amoxicilline 500mg");
        medicament.setDescription("Antibiotique");
        medicament.setPrix(13.25);

        assertThat(medicament.getNom()).isEqualTo("Amoxicilline 500mg");
        assertThat(medicament.getDescription()).isEqualTo("Antibiotique");
        assertThat(medicament.getPrix()).isEqualTo(13.25);
    }

    @Test
    void testEqualsEtHashCode() {
        Medicament med1 = new Medicament("Doliprane", "Description A", 5.5);
        Medicament med2 = new Medicament("doliprane", "Description B", 5.5);
        Medicament med3 = new Medicament("Ibuprofène", "Description A", 5.5);
        Medicament med4 = new Medicament("Doliprane", "Description A", 6.0);

        assertThat(med1).isEqualTo(med1);
        assertThat(med1).isEqualTo(med2);
        assertThat(med1).isNotEqualTo(med3);
        assertThat(med1).isNotEqualTo(med4);
        assertThat(med1).isNotEqualTo(null);
        assertThat(med1).isNotEqualTo("Une simple chaîne de caractères"); // Classes différentes

        assertThat(med1.hashCode()).isEqualTo(med2.hashCode());
        assertThat(med1.hashCode()).isNotEqualTo(med3.hashCode());
    }

    @Test
    void testToString() {
        Medicament medicament = new Medicament("Doliprane 1000mg", "Antalgique", 5.5);
        String resultatToString = medicament.toString();

        assertThat(resultatToString)
                .contains("Doliprane 1000mg")
                .contains("5.5")
                .contains("Antalgique")
                .contains("ID=");
    }
}
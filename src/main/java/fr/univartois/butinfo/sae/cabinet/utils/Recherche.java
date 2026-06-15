package fr.univartois.butinfo.sae.cabinet.utils;

import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.modele.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.Patient;
import fr.univartois.butinfo.sae.cabinet.service.GestionMedecins;
import fr.univartois.butinfo.sae.cabinet.service.GestionPatients;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Module de recherche transversale pour patients et médecins.
 * Regroupe les fonctionnalités de recherche avancée.
 */
public class Recherche {

    private final GestionPatients gestionPatients;
    private final GestionMedecins gestionMedecins;
    private static final Logger LOGGER = LogManager.getLogger(Recherche.class);

    /**
     * Constructeur : injecte les services nécessaires.
     */
    public Recherche(GestionPatients gestionPatients, GestionMedecins gestionMedecins) {
        this.gestionPatients = gestionPatients;
        this.gestionMedecins = gestionMedecins;
    }

    // -------------------------
    // Recherche patients
    // -------------------------

    /**
     * Recherche des patients par nom ou prénom.
     */
    public List<Patient> rechercherPatientParNom(String terme) {
        List<Patient> resultats = gestionPatients.rechercherParNom(terme);
        if (resultats.isEmpty()) {
            LOGGER.debug("Aucun patient trouvé pour le terme : " + terme);
        }
        return resultats;
    }

    /**
     * Recherche un patient par son numéro de sécurité sociale.
     */
    public Patient rechercherPatientParSecu(String numeroSecu) {
        Patient p = gestionPatients.rechercherParNumeroSecu(numeroSecu);
        if (p == null) {
            LOGGER.debug("Aucun patient trouvé pour le numéro de sécu : " + numeroSecu);
        }
        return p;
    }

    // -------------------------
    // Recherche médecins
    // -------------------------

    /**
     * Recherche des médecins par nom ou prénom.
     */
    public List<Medecin> rechercherMedecinParNom(String terme) {
        List<Medecin> resultats = gestionMedecins.rechercherParNom(terme);
        if (resultats.isEmpty()) {
            LOGGER.debug("Aucun médecin trouvé pour le terme : " + terme);
        }
        return resultats;
    }

    /**
     * Recherche des médecins par spécialité.
     */
    public List<Medecin> rechercherMedecinParSpecialite(Specialite specialite) {
        List<Medecin> resultats = gestionMedecins.rechercherParSpecialite(specialite);
        if (resultats.isEmpty()) {
            LOGGER.debug("Aucun médecin trouvé pour la spécialité : " + specialite.getLibelle());
        }
        return resultats;
    }

    /**
     * Recherche un médecin par son numéro d'ordre.
     */
    public Medecin rechercherMedecinParOrdre(String numeroOrdre) {
        Medecin m = gestionMedecins.rechercherParNumeroOrdre(numeroOrdre);
        if (m == null) {
            LOGGER.debug("Aucun médecin trouvé pour le n° d'ordre : " + numeroOrdre);
        }
        return m;
    }

    @Override
    public String toString() {
        return "Module Recherche { patients=" + gestionPatients.getNombrePatients() +
                ", médecins=" + gestionMedecins.getNombreMedecins() + " }";
    }
}
package fr.univartois.butinfo.sae.cabinet.model.utils;

import fr.univartois.butinfo.sae.cabinet.model.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.model.modele.Medecin;
import fr.univartois.butinfo.sae.cabinet.model.modele.Patient;
import fr.univartois.butinfo.sae.cabinet.model.service.GestionMedecins;
import fr.univartois.butinfo.sae.cabinet.model.service.GestionPatients;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Module de recherche transversale pour patients et médecins.
 * <p>
 * Cette classe regroupe toutes les fonctionnalités de recherche avancée
 * en utilisant les données des services de gestion des patients et des médecins.
 * </p>
 * *  @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public class Recherche {

    /** Le service pour récupérer et chercher des patients. */
    private final GestionPatients gestionPatients;

    /** Le service pour récupérer et chercher des médecins. */
    private final GestionMedecins gestionMedecins;

    /** Le logger pour afficher des messages de débogage dans la console. */
    private static final Logger LOGGER = LogManager.getLogger(Recherche.class);

    /**
     * Constructeur de la classe Recherche.
     * On lui donne les services de gestion pour qu'il puisse accéder aux listes de données.
     * * @param gestionPatients Le service contenant la liste des patients.
     * @param gestionMedecins Le service contenant la liste des médecins.
     */
    public Recherche(GestionPatients gestionPatients, GestionMedecins gestionMedecins) {
        this.gestionPatients = gestionPatients;
        this.gestionMedecins = gestionMedecins;
    }

    // -------------------------
    // Recherche patients
    // -------------------------

    /**
     * Recherche des patients par leur nom ou leur prénom.
     * * @param terme Le texte recherché (le nom ou le prénom du patient).
     * @return La liste des patients qui correspondent au texte recherché.
     */
    public List<Patient> rechercherPatientParNom(String terme) {
        List<Patient> resultats = gestionPatients.rechercherParNom(terme);
        if (resultats.isEmpty()) {
            LOGGER.debug("Aucun patient trouvé pour le terme : " + terme);
        }
        return resultats;
    }

    /**
     * Recherche un patient unique en utilisant son numéro de sécurité sociale.
     * * @param numeroSecu Le numéro de sécurité sociale recherché (sous forme de chaîne).
     * @return Le patient trouvé, ou null si aucun patient ne possède ce numéro.
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
     * Recherche des médecins par leur nom ou leur prénom.
     * * @param terme Le texte recherché (le nom ou le prénom du médecin).
     * @return La liste des médecins qui correspondent au texte recherché.
     */
    public List<Medecin> rechercherMedecinParNom(String terme) {
        List<Medecin> resultats = gestionMedecins.rechercherParNom(terme);
        if (resultats.isEmpty()) {
            LOGGER.debug("Aucun médecin trouvé pour le terme : " + terme);
        }
        return resultats;
    }

    /**
     * Recherche tous les médecins qui ont une spécialité précise.
     * * @param specialite La spécialité médicale recherchée (Ex: GENERALISTE, CARDIOLOGUE).
     * @return La liste des médecins qui pratiquent cette spécialité.
     */
    public List<Medecin> rechercherMedecinParSpecialite(Specialite specialite) {
        List<Medecin> resultats = gestionMedecins.rechercherParSpecialite(specialite);
        if (resultats.isEmpty()) {
            LOGGER.debug("Aucun médecin trouvé pour la spécialité : " + specialite.getLibelle());
        }
        return resultats;
    }

    /**
     * Recherche un médecin unique en utilisant son numéro d'ordre.
     * * @param numeroOrdre Le numéro d'ordre du médecin recherché.
     * @return Le médecin trouvé, ou null si aucun médecin ne possède ce numéro d'ordre.
     */
    public Medecin rechercherMedecinParOrdre(String numeroOrdre) {
        Medecin m = gestionMedecins.rechercherParNumeroOrdre(numeroOrdre);
        if (m == null) {
            LOGGER.debug("Aucun médecin trouvé pour le n° d'ordre : " + numeroOrdre);
        }
        return m;
    }

    /**
     * Crée une version texte simplifiée du module de recherche indiquant le nombre d'éléments disponibles.
     * * @return Une chaîne de caractères résumant le contenu du module.
     */
    @Override
    public String toString() {
        return "Module Recherche { patients=" + gestionPatients.getNombrePatients() +
                ", médecins=" + gestionMedecins.getNombreMedecins() + " }";
    }
}
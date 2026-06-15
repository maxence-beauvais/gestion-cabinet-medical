package fr.univartois.butinfo.sae.cabinet.model.service;

import fr.univartois.butinfo.sae.cabinet.model.modele.Patient;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Service de gestion des patients.
 * <p>
 * Cette classe fournit les opérations CRUD (Créer, Lire, Mettre à jour, Supprimer)
 * pour gérer l'ensemble des patients enregistrés dans le cabinet médical.
 * </p>
 * * @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public class GestionPatients {

    /** Le logger pour afficher les messages de suivi ou de débogage. */
    private static final Logger LOGGER = LogManager.getLogger(GestionPatients.class);

    /** Liste interne des patients enregistrés dans l'application. */
    private final List<Patient> patients;

    /**
     * Constructeur de la classe GestionPatients.
     * Il initialise la liste des patients sous forme d'une ArrayList vide.
     */
    public GestionPatients() {
        this.patients = new ArrayList<>();
    }

    // -------------------------
    // CRUD
    // -------------------------

    /**
     * Ajoute un nouveau patient dans le système si son numéro de sécurité sociale n'est pas déjà utilisé.
     * * @param patient Le patient que l'on souhaite ajouter à la liste.
     */
    public void ajouterPatient(Patient patient) {
        if (rechercherParNumeroSecu(patient.getNumeroSecu()) != null) {
            LOGGER.debug("ERREUR : Un patient avec ce numéro de sécu existe déjà.");
            return;
        }
        patients.add(patient);
        LOGGER.info("Patient ajouté : " + patient.getNomComplet());
    }

    /**
     * Recherche un patient dans la liste en utilisant son identifiant numérique unique.
     * * @param id L'identifiant (ID) du patient recherché.
     * @return Le patient correspondant à l'ID donné, ou null si aucun ne correspond.
     */
    public Patient rechercherParId(int id) {
        for (Patient p : patients) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    /**
     * Recherche un patient dans la liste en utilisant son numéro de sécurité sociale.
     * * @param numeroSecu Le numéro de sécurité sociale recherché.
     * @return Le patient possédant ce numéro de sécu, ou null s'il n'existe pas.
     */
    public Patient rechercherParNumeroSecu(String numeroSecu) {
        for (Patient p : patients) {
            if (p.getNumeroSecu().equalsIgnoreCase(numeroSecu)) return p;
        }
        return null;
    }

    /**
     * Recherche tous les patients dont le nom ou le prénom contient le texte recherché.
     * La recherche ignore les différences entre majuscules et minuscules.
     * * @param terme Le texte recherché (une partie du nom ou du prénom).
     * @return Une liste contenant les patients correspondants (liste vide si aucun résultat).
     */
    public List<Patient> rechercherParNom(String terme) {
        List<Patient> resultats = new ArrayList<>();
        String termeLower = terme.toLowerCase();
        for (Patient p : patients) {
            if (p.getNom().toLowerCase().contains(termeLower) ||
                    p.getPrenom().toLowerCase().contains(termeLower)) {
                resultats.add(p);
            }
        }
        return resultats;
    }

    /**
     * Met à jour les informations personnelles d'un patient existant (sauf son ID et son numéro de sécu).
     * * @param id L'identifiant du patient à modifier.
     * @param nouveauNom Le nouveau nom de famille.
     * @param nouveauPrenom Le nouveau prénom.
     * @param nouveauTel Le nouveau numéro de téléphone.
     * @param nouveauEmail L'adresse e-mail mise à jour.
     * @param nouvelleAdresse La nouvelle adresse postale.
     * @param nouveauTauxRemboursement Le nouveau taux de prise en charge (valeur décimale).
     */
    public void mettreAJourPatient(int id, String nouveauNom, String nouveauPrenom,
                                   String nouveauTel, String nouveauEmail,
                                   String nouvelleAdresse, double nouveauTauxRemboursement) {
        Patient p = rechercherParId(id);
        if (p == null) {
            LOGGER.debug("ERREUR : Patient ID=" + id + " introuvable.");
            return;
        }
        p.setNom(nouveauNom);
        p.setPrenom(nouveauPrenom);
        p.setTelephone(nouveauTel);
        p.setEmail(nouveauEmail);
        p.setAdresse(nouvelleAdresse);
        p.setTauxRemboursement(nouveauTauxRemboursement);
        System.out.println("Patient mis à jour : " + p.getNomComplet());
    }

    /**
     * Supprime un patient définitivement de la liste grâce à son identifiant.
     * * @param id L'identifiant (ID) du patient à enlever du système.
     */
    public void supprimerPatient(int id) {
        Patient p = rechercherParId(id);
        if (p == null) {
            LOGGER.debug("ERREUR : Patient ID=" + id + " introuvable.");
            return;
        }
        patients.remove(p);
        LOGGER.info("Patient supprimé : " + p.getNomComplet());
    }

    /**
     * Retourne une copie complète de la liste de tous les patients.
     * Cela protège la liste originale contre les modifications extérieures involontaires.
     * * @return Une nouvelle liste contenant l'ensemble des patients enregistrés.
     */
    public List<Patient> listerTousLesPatients() {
        return new ArrayList<>(patients);
    }

    /**
     * Retourne la taille actuelle de la liste des patients.
     * * @return Le nombre total de patients présents dans le système.
     */
    public int getNombrePatients() {
        return patients.size();
    }
}
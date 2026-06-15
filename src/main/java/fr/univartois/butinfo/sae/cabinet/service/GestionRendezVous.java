package fr.univartois.butinfo.sae.cabinet.service;

import fr.univartois.butinfo.sae.cabinet.enumeration.StatutRendezVous;
import fr.univartois.butinfo.sae.cabinet.modele.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.Patient;
import fr.univartois.butinfo.sae.cabinet.modele.RendezVous;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Service de gestion des rendez-vous médicaux.
 * <p>
 * Cette classe permet de gérer le planning du cabinet : ajouter, valider,
 * annuler et chercher des rendez-vous dans une liste.
 * </p>
 * *  @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public class GestionRendezVous {

    /** La liste qui stocke tous les rendez-vous du cabinet médical. */
    private final List<RendezVous> rendezVous;

    /** Le logger pour afficher les messages et erreurs dans la console. */
    private static final Logger LOGGER = LogManager.getLogger(GestionRendezVous.class);

    /**
     * Constructeur de la classe GestionRendezVous.
     * Il crée une liste vide au départ pour pouvoir enregistrer les futurs rendez-vous.
     */
    public GestionRendezVous() {
        this.rendezVous = new ArrayList<>();
    }

    // -------------------------
    // Gestion des RDV
    // -------------------------

    /**
     * Permet de créer et de prendre un rendez-vous si le médecin est libre.
     * * @param patient Le patient qui demande le rendez-vous.
     * @param medecin Le médecin choisi pour la consultation.
     * @param dateHeure Le jour et l'heure prévus pour le rendez-vous.
     * @param dureeMinutes La durée totale de la consultation en minutes.
     * @param motif La raison médicale du rendez-vous.
     * @return Le rendez-vous créé, ou null si le médecin est déjà occupé à ce moment-je.
     */
    public RendezVous prendreRendezVous(Patient patient, Medecin medecin,
                                        LocalDateTime dateHeure, int dureeMinutes, String motif) {
        // Vérification de disponibilité du médecin
        if (!estDisponible(medecin, dateHeure)) {
            LOGGER.debug("ERREUR : Le Dr " + medecin.getNomComplet() +
                    " n'est pas disponible à ce créneau.");
            return null;
        }
        RendezVous rdv = new RendezVous(patient, medecin, dateHeure, dureeMinutes, motif);
        rendezVous.add(rdv);
        LOGGER.info("Rendez-vous créé : " + rdv);
        return rdv;
    }

    /**
     * Annule un rendez-vous enregistré en utilisant son identifiant numérique.
     * * @param id L'identifiant (ID) unique du rendez-vous à annuler.
     */
    public void annulerRendezVous(int id) {
        RendezVous rdv = rechercherParId(id);
        if (rdv == null) {
            LOGGER.debug("ERREUR : Rendez-vous ID=" + id + " introuvable.");
            return;
        }
        rdv.annuler();
        LOGGER.info("Rendez-vous annulé : ID=" + id);
    }

    /**
     * Confirme un rendez-vous enregistré en utilisant son identifiant numérique.
     * * @param id L'identifiant (ID) unique du rendez-vous à confirmer.
     */
    public void confirmerRendezVous(int id) {
        RendezVous rdv = rechercherParId(id);
        if (rdv == null) {
            LOGGER.debug("ERREUR : Rendez-vous ID=" + id + " introuvable.");
            return;
        }
        rdv.confirmer();
        LOGGER.info("Rendez-vous confirmé : ID=" + id);
    }

    /**
     * Marque un rendez-vous comme terminé quand la consultation est finie.
     * * @param id L'identifiant (ID) unique du rendez-vous à clore.
     * @return true si le rendez-vous a bien été marqué comme terminé, false s'il n'existe pas.
     */
    public boolean terminerRendezVous(int id) {
        RendezVous rdv = rechercherParId(id);
        if (rdv == null) {
            LOGGER.debug("ERREUR : Rendez-vous ID=" + id + " introuvable.");
            return false;
        }
        rdv.terminer();
        LOGGER.info("Rendez-vous terminé : ID=" + id);
        return true;
    }

    // -------------------------
    // Recherche
    // -------------------------

    /**
     * Recherche un rendez-vous précis dans la liste grâce à son identifiant unique.
     * * @param id L'identifiant (ID) recherché.
     * @return Le rendez-vous correspondant à cet ID, ou null si on ne le trouve pas.
     */
    public RendezVous rechercherParId(int id) {
        for (RendezVous rdv : rendezVous) {
            if (rdv.getId() == id) return rdv;
        }
        return null;
    }

    /**
     * Récupère la liste de tous les rendez-vous qui concernent un patient précis.
     * * @param patient Le patient dont on cherche l'historique des rendez-vous.
     * @return Une liste contenant les rendez-vous de ce patient (liste vide si aucun).
     */
    public List<RendezVous> getRendezVousPatient(Patient patient) {
        List<RendezVous> resultats = new ArrayList<>();
        for (RendezVous rdv : rendezVous) {
            if (rdv.getPatient().equals(patient)) resultats.add(rdv);
        }
        return resultats;
    }

    /**
     * Récupère la liste de tous les rendez-vous programmés pour un médecin précis.
     * * @param medecin Le médecin dont on cherche les rendez-vous.
     * @return Une liste contenant les rendez-vous de ce médecin (liste vide si aucun).
     */
    public List<RendezVous> getRendezVousMedecin(Medecin medecin) {
        List<RendezVous> resultats = new ArrayList<>();
        for (RendezVous rdv : rendezVous) {
            if (rdv.getMedecin().equals(medecin)) resultats.add(rdv);
        }
        return resultats;
    }

    /**
     * Récupère les rendez-vous filtrés selon un statut donné.
     * * @param statut Le statut recherché (Ex: CONFIRME, ANNULE, PLANIFIE).
     * @return Une liste contenant uniquement les rendez-vous ayant ce statut.
     */
    public List<RendezVous> getRendezVousParStatut(StatutRendezVous statut) {
        List<RendezVous> resultats = new ArrayList<>();
        for (RendezVous rdv : rendezVous) {
            if (rdv.getStatut() == statut) resultats.add(rdv);
        }
        return resultats;
    }

    /**
     * Vérifie si un médecin est libre à un horaire précis.
     * Un médecin n'est pas libre s'il a déjà un rendez-vous à cette heure-là qui n'est pas annulé.
     * * @param medecin Le médecin à vérifier.
     * @param dateHeure Le jour et l'heure du créneau à tester.
     * @return true si le médecin n'a pas d'autre rendez-vous en même temps, false sinon.
     */
    public boolean estDisponible(Medecin medecin, LocalDateTime dateHeure) {
        for (RendezVous rdv : rendezVous) {
            if (rdv.getMedecin().equals(medecin) &&
                    rdv.getDateHeure().equals(dateHeure) &&
                    rdv.getStatut() != StatutRendezVous.ANNULE) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retourne une copie de la liste complète de tous les rendez-vous de l'application.
     * * @return Une nouvelle liste contenant tous les rendez-vous enregistrés.
     */
    public List<RendezVous> listerTousLesRendezVous() {
        return new ArrayList<>(rendezVous);
    }

    /**
     * Donne la taille de la liste, c'est-à-dire le nombre total de rendez-vous stockés.
     * * @return Le nombre total de rendez-vous existants.
     */
    public int getNombreRendezVous() {
        return rendezVous.size();
    }

}
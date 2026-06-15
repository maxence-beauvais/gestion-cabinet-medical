package fr.univartois.butinfo.sae.cabinet.model.service;

import fr.univartois.butinfo.sae.cabinet.model.modele.LignePrescription;
import fr.univartois.butinfo.sae.cabinet.model.modele.Medecin;
import fr.univartois.butinfo.sae.cabinet.model.modele.Medicament;
import fr.univartois.butinfo.sae.cabinet.model.modele.Ordonnance;
import fr.univartois.butinfo.sae.cabinet.model.modele.Patient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Service de gestion des ordonnances médicales.
 * <p>
 * Cette classe permet de créer des ordonnances pour les patients, de leur
 * ajouter des lignes de prescription (médicaments) et de faire des recherches.
 * </p>
 * * @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public class GestionOrdonnances {

    /** Le logger pour afficher des messages de suivi ou de débogage dans la console. */
    private static final Logger LOGGER = LogManager.getLogger(GestionOrdonnances.class);

    /** Liste interne qui stocke toutes les ordonnances du cabinet. */
    private final ObservableList<Ordonnance> ordonnances;
    /**
     * Constructeur de la classe GestionOrdonnances.
     * Il initialise la liste des ordonnances sous forme d'une ArrayList vide.
     */
    public GestionOrdonnances() {
        this.ordonnances = FXCollections.observableArrayList();
    }

    // -------------------------
    // Gestion des ordonnances
    // -------------------------

    /**
     * Crée une nouvelle ordonnance vide (sans médicaments pour le moment) et l'enregistre.
     * * @param medecin Le médecin qui rédige et signe l'ordonnance.
     * @param patient Le patient à qui est destinée l'ordonnance.
     * @param dateValidite La date maximale de validité de l'ordonnance.
     * @param remarques Les consignes ou remarques particulières écrites par le médecin.
     * @return L'ordonnance qui vient d'être créée et enregistrée.
     */
    public Ordonnance creerOrdonnance(Medecin medecin, Patient patient,
                                      LocalDate dateValidite, String remarques) {
        Ordonnance ordo = new Ordonnance(medecin, patient, dateValidite, remarques);
        ordonnances.add(ordo);
        LOGGER.info("Ordonnance créée (ID=" + ordo.getId() + ") pour " +
                patient.getNomComplet() + " par Dr " + medecin.getNomComplet());
        return ordo;
    }

    /**
     * Ajoute une nouvelle ligne de prescription (un médicament et ses détails) à une ordonnance existante.
     * * @param ordonnanceId L'identifiant numérique unique de l'ordonnance à modifier.
     * @param medicament Le médicament à ajouter à la prescription.
     * @param quantite Le nombre de boîtes ou d'unités à délivrer.
     * @param posologie La manière de prendre le médicament (Ex: "1 comprimé matin et soir").
     * @param dureeJours Le nombre de jours pendant lesquels le traitement doit être suivi.
     * @param instructions Les détails supplémentaires (Ex: "À prendre au milieu du repas").
     */
    public void ajouterPrescription(int ordonnanceId, Medicament medicament, int quantite,
                                    String posologie, int dureeJours, String instructions) {
        Ordonnance ordo = rechercherParId(ordonnanceId);
        if (ordo == null) {
            LOGGER.debug("ERREUR : Ordonnance ID=" + ordonnanceId + " introuvable.");
            return;
        }
        LignePrescription ligne = new LignePrescription(medicament, quantite, posologie, dureeJours, instructions);
        ordo.ajouterPrescription(ligne);
        LOGGER.info("Prescription ajoutée : " + medicament.getNom() +
                " → ordonnance ID=" + ordonnanceId);
    }

    /**
     * Cherche une ordonnance précise dans la liste grâce à son identifiant unique.
     * * @param id L'identifiant (ID) recherché.
     * @return L'ordonnance correspondante à cet ID, ou null si elle n'existe pas.
     */
    public Ordonnance rechercherParId(int id) {
        for (Ordonnance o : ordonnances) {
            if (o.getId() == id) return o;
        }
        return null;
    }

    /**
     * Récupère la liste de toutes les ordonnances créées pour un patient donné.
     * * @param patient Le patient dont on souhaite obtenir l'historique des ordonnances.
     * @return Une liste contenant toutes les ordonnances de ce patient (liste vide si aucune).
     */
    public List<Ordonnance> getOrdonnancesPatient(Patient patient) {
        List<Ordonnance> resultats = new ArrayList<>();
        for (Ordonnance o : ordonnances) {
            if (o.getPatient().equals(patient)) resultats.add(o);
        }
        return resultats;
    }

    /**
     * Récupère la liste de toutes les ordonnances rédigées par un médecin donné.
     * * @param medecin Le médecin dont on veut obtenir les ordonnances émises.
     * @return Une liste contenant toutes les ordonnances de ce médecin (liste vide si aucune).
     */
    public List<Ordonnance> getOrdonnancesMedecin(Medecin medecin) {
        List<Ordonnance> resultats = new ArrayList<>();
        for (Ordonnance o : ordonnances) {
            if (o.getMedecin().equals(medecin)) resultats.add(o);
        }
        return resultats;
    }

    /**
     * Récupère uniquement les ordonnances du cabinet qui sont encore valides aujourd'hui.
     * * @return Une liste contenant toutes les ordonnances non périmées.
     */
    public List<Ordonnance> getOrdonnancesValides() {
        List<Ordonnance> resultats = new ArrayList<>();
        for (Ordonnance o : ordonnances) {
            if (o.estValide()) resultats.add(o);
        }
        return resultats;
    }

    /**
     * Retourne une copie de la liste de toutes les ordonnances stockées dans le système.
     * Cela évite que la liste d'origine ne soit modifiée en dehors du service.
     * * @return Une nouvelle liste contenant l'intégralité des ordonnances.
     */
    public List<Ordonnance> listerToutesLesOrdonnances() {
        return new ArrayList<>(ordonnances);
    }

    /**
     * Donne le nombre total d'ordonnances enregistrées dans le cabinet médical.
     * * @return Le nombre total d'ordonnances de la liste.
     */
    public int getNombreOrdonnances() {
        return ordonnances.size();
    }
}
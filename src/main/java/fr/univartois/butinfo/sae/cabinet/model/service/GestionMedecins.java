package fr.univartois.butinfo.sae.cabinet.model.service;

import fr.univartois.butinfo.sae.cabinet.model.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.model.modele.Medecin;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Service de gestion des médecins du cabinet.
 * <p>
 * Cette classe permet de gérer l'annuaire des professionnels de santé du cabinet :
 * ajouter de nouveaux praticiens, modifier leurs coordonnées ou spécialités,
 * effectuer des recherches et supprimer des profils.
 * </p>
 *
 * @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public class GestionMedecins {

    /** Liste interne qui stocke tous les médecins enregistrés dans le système. */
    private final ObservableList<Medecin> medecins;
    /** Le logger pour afficher les messages de suivi ou de débogage dans la console. */
    private static final Logger LOGGER = LogManager.getLogger(GestionMedecins.class);

    /**
     * Constructeur de la classe GestionMedecins.
     * Il initialise la liste des médecins sous forme d'une ArrayList vide.
     */
    public GestionMedecins() {
        this.medecins = FXCollections.observableArrayList();
    }

    // -------------------------
    // CRUD
    // -------------------------

    /**
     * Enregistre un nouveau médecin dans le système après avoir vérifié que son numéro d'ordre est unique.
     *
     * @param medecin Le médecin que l'on souhaite ajouter à la liste.
     */
    public void ajouterMedecin(Medecin medecin) {
        if (rechercherParNumeroOrdre(medecin.getNumeroOrdre()) != null) {
            LOGGER.debug("ERREUR : Un médecin avec ce numéro d'ordre existe déjà.");
            return;
        }
        medecins.add(medecin);
        LOGGER.info("Médecin ajouté : Dr " + medecin.getNomComplet());
    }

    /**
     * Recherche un médecin dans la liste en utilisant son identifiant numérique unique.
     *
     * @param id L'identifiant (ID) du médecin recherché.
     * @return Le médecin correspondant à l'ID donné, ou null si aucun ne possède cet identifiant.
     */
    public Medecin rechercherParId(int id) {
        for (Medecin m : medecins) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    /**
     * Recherche un médecin unique dans la liste grâce à son numéro d'ordre professionnel.
     *
     * @param numeroOrdre Le numéro d'ordre recherché (sans tenir compte des majuscules/minuscules).
     * @return Le médecin possédant ce numéro d'ordre, ou null s'il n'est pas enregistré.
     */
    public Medecin rechercherParNumeroOrdre(String numeroOrdre) {
        for (Medecin m : medecins) {
            if (m.getNumeroOrdre().equalsIgnoreCase(numeroOrdre)) return m;
        }
        return null;
    }

    /**
     * Recherche tous les médecins dont le nom ou le prénom contient le texte recherché.
     * La recherche ignore les différences entre majuscules et minuscules.
     *
     * @param terme Le texte recherché (une partie ou la totalité du nom ou du prénom).
     * @return Une liste contenant les médecins correspondants (liste vide si aucun résultat).
     */
    public List<Medecin> rechercherParNom(String terme) {
        List<Medecin> resultats = new ArrayList<>();
        String termeLower = terme.toLowerCase();
        for (Medecin m : medecins) {
            if (m.getNom().toLowerCase().contains(termeLower) ||
                    m.getPrenom().toLowerCase().contains(termeLower)) {
                resultats.add(m);
            }
        }
        return resultats;
    }

    /**
     * Récupère la liste de tous les médecins pratiquant une spécialité médicale particulière.
     *
     * @param specialite La spécialité recherchée (Ex: GENERALISTE, CARDIOLOGUE, PEDIATRE).
     * @return Une liste contenant uniquement les médecins ayant cette spécialité.
     */
    public List<Medecin> rechercherParSpecialite(Specialite specialite) {
        List<Medecin> resultats = new ArrayList<>();
        for (Medecin m : medecins) {
            if (m.getSpecialite() == specialite) {
                resultats.add(m);
            }
        }
        return resultats;
    }

    /**
     * Met à jour les informations professionnelles et de contact d'un médecin existant.
     *
     * @param id L'identifiant numérique du médecin à modifier.
     * @param nouveauNom Le nouveau nom de famille mis à jour.
     * @param nouveauPrenom Le nouveau prénom mis à jour.
     * @param nouveauTarifConsultation Le nouveau tarif de consultation (prix horaire ou par acte).
     * @param nouveauTel Le nouveau numéro de téléphone professionnel.
     * @param nouveauEmail La nouvelle adresse e-mail de contact.
     * @param nouvelleSpecialite La nouvelle spécialité médicale attribuée.
     */
    public void mettreAJourMedecin(int id, String nouveauNom, String nouveauPrenom, double nouveauTarifConsultation,
                                   String nouveauTel, String nouveauEmail,
                                   Specialite nouvelleSpecialite) {
        Medecin m = rechercherParId(id);
        if (m == null) {
            LOGGER.debug("ERREUR : Médecin ID=" + id + " introuvable.");
            return;
        }
        m.setNom(nouveauNom);
        m.setPrenom(nouveauPrenom);
        m.setTarifHoraire(nouveauTarifConsultation);
        m.setTelephone(nouveauTel);
        m.setEmail(nouveauEmail);
        m.setSpecialite(nouvelleSpecialite);
        LOGGER.info("Médecin mis à jour : Dr " + m.getNomComplet());
    }

    /**
     * Supprime un médecin définitivement de la liste à partir de son identifiant unique.
     *
     * @param id L'identifiant (ID) du médecin à enlever du cabinet.
     * @return true si le médecin a été trouvé et retiré de la liste, false si l'ID n'existait pas.
     */
    public boolean supprimerMedecin(int id) {
        Medecin m = rechercherParId(id);
        if (m == null) {
            LOGGER.debug("ERREUR : Médecin ID=" + id + " introuvable.");
            return false;
        }
        medecins.remove(m);
        LOGGER.info("Médecin supprimé : Dr " + m.getNomComplet());
        return true;
    }

    /**
     * Retourne une copie complète de la liste contenant tous les médecins enregistrés.
     * La copie défensive permet d'empêcher que des modifications externes n'altèrent la liste d'origine.
     *
     * @return Une nouvelle liste contenant l'intégralité des médecins de l'application.
     */
    public ObservableList<Medecin> listerTousLesMedecins() {
        return medecins;
    }

    /**
     * Donne la taille actuelle de la liste, c'est-à-dire le nombre total de médecins stockés.
     *
     * @return Le nombre total de médecins présents dans le système.
     */
    public int getNombreMedecins() {
        return medecins.size();
    }
}
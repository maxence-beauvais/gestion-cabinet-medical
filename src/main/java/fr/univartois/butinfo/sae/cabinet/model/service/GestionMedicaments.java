package fr.univartois.butinfo.sae.cabinet.model.service;

import fr.univartois.butinfo.sae.cabinet.model.modele.Medicament;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Service de gestion des médicaments disponibles dans le système.
 * <p>
 * Cette classe permet de gérer le catalogue des médicaments du cabinet médical
 * en proposant des fonctionnalités pour ajouter, chercher, lister ou supprimer des produits.
 * </p>
 *
 * @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public class GestionMedicaments {

    /** Le logger pour afficher les messages de suivi ou de débogage dans la console. */
    private static final Logger LOGGER = LogManager.getLogger(GestionMedicaments.class);

    /** Liste interne contenant l'ensemble des médicaments référencés par le cabinet. */
    private final List<Medicament> medicaments;

    /**
     * Constructeur de la classe GestionMedicaments.
     * Il initialise la liste des médicaments sous la forme d'une ArrayList vide.
     */
    public GestionMedicaments() {
        this.medicaments = new ArrayList<>();
    }

    // -------------------------
    // CRUD
    // -------------------------

    /**
     * Ajoute un nouveau médicament au catalogue s'il n'y est pas déjà présent.
     *
     * @param medicament Le médicament que l'on souhaite ajouter à la liste.
     */
    public void ajouterMedicament(Medicament medicament) {
        if (medicaments.contains(medicament)) {
            LOGGER.debug("ERREUR : Ce médicament existe déjà.");
            return;
        }
        medicaments.add(medicament);
        LOGGER.info("Médicament ajouté : " + medicament.getNom());
    }

    /**
     * Recherche un médicament précis dans le catalogue grâce à son identifiant unique.
     *
     * @param id L'identifiant (ID) numérique du médicament recherché.
     * @return Le médicament correspondant à l'ID donné, ou null s'il n'existe pas.
     */
    public Medicament rechercherParId(int id) {
        for (Medicament m : medicaments) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    /**
     * Recherche tous les médicaments dont le nom contient le texte recherché.
     * La recherche ignore les différences entre les majuscules et les minuscules.
     *
     * @param terme Le texte recherché (une partie ou la totalité du nom du médicament).
     * @return Une liste contenant les médicaments correspondants (liste vide si aucun résultat).
     */
    public List<Medicament> rechercherParNom(String terme) {
        List<Medicament> resultats = new ArrayList<>();
        String termeLower = terme.toLowerCase();
        for (Medicament m : medicaments) {
            if (m.getNom().toLowerCase().contains(termeLower)) {
                resultats.add(m);
            }
        }
        return resultats;
    }

    /**
     * Supprime définitivement un médicament du catalogue en utilisant son identifiant.
     *
     * @param id L'identifiant (ID) du médicament à enlever du système.
     * @return true si le médicament a bien été trouvé et supprimé, false sinon.
     */
    public boolean supprimerMedicament(int id) {
        Medicament m = rechercherParId(id);
        if (m == null) {
            LOGGER.debug("ERREUR : Médicament ID=" + id + " introuvable.");
            return false;
        }
        medicaments.remove(m);
        LOGGER.info("Médicament supprimé : " + m.getNom());
        return true;
    }

    /**
     * Retourne une copie complète de la liste de tous les médicaments enregistrés.
     * Faire une copie permet de protéger la liste originale contre les modifications extérieures involontaires.
     *
     * @return Une nouvelle liste contenant l'intégralité des médicaments référencés.
     */
    public List<Medicament> listerTousLesMedicaments() {
        return new ArrayList<>(medicaments);
    }

    /**
     * Retourne la taille actuelle de la liste, c'est-à-dire le nombre total de médicaments stockés.
     *
     * @return Le nombre total de médicaments présents dans le système.
     */
    public int getNombreMedicaments() {
        return medicaments.size();
    }
}
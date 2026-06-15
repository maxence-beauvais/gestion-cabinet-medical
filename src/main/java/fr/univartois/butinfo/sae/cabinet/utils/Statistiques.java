package fr.univartois.butinfo.sae.cabinet.utils;

import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.enumeration.StatutRendezVous;
import fr.univartois.butinfo.sae.cabinet.modele.Medecin;
import fr.univartois.butinfo.sae.cabinet.service.GestionMedecins;
import fr.univartois.butinfo.sae.cabinet.service.GestionMedicaments;
import fr.univartois.butinfo.sae.cabinet.service.GestionOrdonnances;
import fr.univartois.butinfo.sae.cabinet.service.GestionPatients;
import fr.univartois.butinfo.sae.cabinet.service.GestionRendezVous;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Module de statistiques du cabinet médical.
 * Agrège des informations issues des différents services pour produire un tableau de bord.
 */
public class Statistiques {
    private static final Logger LOGGER = LogManager.getLogger(Statistiques.class);
    private final GestionPatients gestionPatients;
    private final GestionMedecins gestionMedecins;
    private final GestionMedicaments gestionMedicaments;
    private final GestionRendezVous gestionRendezVous;
    private final GestionOrdonnances gestionOrdonnances;

    /**
     * Constructeur : injecte tous les services nécessaires.
     */
    public Statistiques(GestionPatients gestionPatients,
                        GestionMedecins gestionMedecins,
                        GestionMedicaments gestionMedicaments,
                        GestionRendezVous gestionRendezVous,
                        GestionOrdonnances gestionOrdonnances) {
        this.gestionPatients = gestionPatients;
        this.gestionMedecins = gestionMedecins;
        this.gestionMedicaments = gestionMedicaments;
        this.gestionRendezVous = gestionRendezVous;
        this.gestionOrdonnances = gestionOrdonnances;
    }

    /**
     * Affiche un résumé complet des statistiques du cabinet.
     */
    public void afficherTableauDeBord() {
        LOGGER.info("\n╔══════════════════════════════════════════╗");
        LOGGER.info("║        TABLEAU DE BORD - CABINET MÉDICAL  ║");
        LOGGER.info("╚══════════════════════════════════════════╝");

        // -- Chiffres globaux --
        LOGGER.info("\n📋 CHIFFRES GLOBAUX :");
        LOGGER.info("  • Patients enregistrés   : " + gestionPatients.getNombrePatients());
        LOGGER.info("  • Médecins enregistrés   : " + gestionMedecins.getNombreMedecins());
        LOGGER.info("  • Médicaments référencés : " + gestionMedicaments.getNombreMedicaments());
        LOGGER.info("  • Rendez-vous total      : " + gestionRendezVous.getNombreRendezVous());
        LOGGER.info("  • Ordonnances émises     : " + gestionOrdonnances.getNombreOrdonnances());

        // -- Détail des rendez-vous par statut --
        LOGGER.info("\n📅 RENDEZ-VOUS PAR STATUT :");
        for (StatutRendezVous statut : StatutRendezVous.values()) {
            int nb = gestionRendezVous.getRendezVousParStatut(statut).size();
            LOGGER.info("  • " + statut.getLibelle() + " : " + nb);
        }

        // -- Médecins par spécialité --
        LOGGER.info("\n🩺 MÉDECINS PAR SPÉCIALITÉ :");
        for (Specialite sp : Specialite.values()) {
            int nb = gestionMedecins.rechercherParSpecialite(sp).size();
            if (nb > 0) {
                LOGGER.info("  • " + sp.getLibelle() + " : " + nb);
            }
        }

        // -- Top médecins (par nombre de RDV) --
        LOGGER.info("\n🏆 MÉDECINS PAR NOMBRE DE RENDEZ-VOUS :");
        for (Medecin m : gestionMedecins.listerTousLesMedecins()) {
            int nbRdv = gestionRendezVous.getRendezVousMedecin(m).size();
            int nbOrdo = gestionOrdonnances.getOrdonnancesMedecin(m).size();
            LOGGER.info("  • Dr " + m.getNomComplet() +
                               " (" + m.getSpecialite().getLibelle() + ")" +
                               " → " + nbRdv + " RDV, " + nbOrdo + " ordonnances");
        }

        // -- Ordonnances valides --
        LOGGER.info("\n💊 ORDONNANCES VALIDES (en cours) : " +
                           gestionOrdonnances.getOrdonnancesValides().size());

        LOGGER.info("\n══════════════════════════════════════════════\n");
    }

    // -------------------------
    // Méthodes utilitaires
    // -------------------------

    /** Retourne le nombre de rendez-vous pour un statut donné. */
    public int getNombreRendezVousParStatut(StatutRendezVous statut) {
        return gestionRendezVous.getRendezVousParStatut(statut).size();
    }

    /** Retourne le médecin ayant le plus de rendez-vous (ou null si aucun). */
    public Medecin getMedecinLePlusActif() {
        Medecin medecinMax = null;
        int max = 0;
        for (Medecin m : gestionMedecins.listerTousLesMedecins()) {
            int nb = gestionRendezVous.getRendezVousMedecin(m).size();
            if (nb > max) {
                max = nb;
                medecinMax = m;
            }
        }
        return medecinMax;
    }

    @Override
    public String toString() {
        return "Statistiques { " +
               "patients=" + gestionPatients.getNombrePatients() +
               ", médecins=" + gestionMedecins.getNombreMedecins() +
               ", médicaments=" + gestionMedicaments.getNombreMedicaments() +
               ", RDV=" + gestionRendezVous.getNombreRendezVous() +
               ", ordonnances=" + gestionOrdonnances.getNombreOrdonnances() + " }";
    }
}

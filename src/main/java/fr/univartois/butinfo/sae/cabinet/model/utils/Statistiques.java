package fr.univartois.butinfo.sae.cabinet.model.utils;

import fr.univartois.butinfo.sae.cabinet.model.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.model.enumeration.StatutRendezVous;
import fr.univartois.butinfo.sae.cabinet.model.modele.Medecin;
import fr.univartois.butinfo.sae.cabinet.model.service.GestionMedecins;
import fr.univartois.butinfo.sae.cabinet.model.service.GestionMedicaments;
import fr.univartois.butinfo.sae.cabinet.model.service.GestionOrdonnances;
import fr.univartois.butinfo.sae.cabinet.model.service.GestionPatients;
import fr.univartois.butinfo.sae.cabinet.model.service.GestionRendezVous;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Module de statistiques du cabinet médical.
 * <p>
 * Cette classe rassemble les informations de tous les services
 * pour fabriquer un tableau de bord textuel et faire des calculs.
 * </p>
 * *  @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public class Statistiques {

    /** Le logger pour afficher des messages ou faire du debogage. */
    private static final Logger LOGGER = LogManager.getLogger(Statistiques.class);

    /** Le service pour gerer les patients. */
    private final GestionPatients gestionPatients;

    /** Le service pour gerer les medecins. */
    private final GestionMedecins gestionMedecins;

    /** Le service pour gerer les medicaments. */
    private final GestionMedicaments gestionMedicaments;

    /** Le service pour gerer les rendez-vous. */
    private final GestionRendezVous gestionRendezVous;

    /** Le service pour gerer les ordonnances. */
    private final GestionOrdonnances gestionOrdonnances;

    /**
     * Constructeur de la classe Statistiques.
     * On lui donne tous les services pour qu'il puisse recuperer les donnees.
     *
     * @param gestionPatients Le service contenant la liste des patients.
     * @param gestionMedecins Le service contenant la liste des medecins.
     * @param gestionMedicaments Le service contenant la liste des medicaments.
     * @param gestionRendezVous Le service contenant la liste des rendez-vous.
     * @param gestionOrdonnances Le service contenant la liste des ordonnances.
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
     * Genere un texte qui contient tout le resume des statistiques du cabinet.
     * * @return Une chaine de caracteres contenant le tableau de bord complet formate.
     */
    public String getStatistiques() {

        StringBuilder string = new StringBuilder();

        string.append("\n╔══════════════════════════════════════════╗\n");
        string.append("║        TABLEAU DE BORD - CABINET MÉDICAL  ║\n");
        string.append("╚══════════════════════════════════════════╝\n");

        // -- Chiffres globaux --
        string.append("\n📋 CHIFFRES GLOBAUX :");
        string.append("  • Patients enregistrés   : ").append(gestionPatients.getNombrePatients()).append("\n");
        string.append("  • Médecins enregistrés   : ").append(gestionMedecins.getNombreMedecins()).append("\n");
        string.append("  • Médicaments référencés : ").append(gestionMedicaments.getNombreMedicaments()).append("\n");
        string.append("  • Rendez-vous total      : ").append(gestionRendezVous.getNombreRendezVous()).append("\n");
        string.append("  • Ordonnances émises     : ").append(gestionOrdonnances.getNombreOrdonnances()).append("\n");

        // -- Détail des rendez-vous par statut --
        string.append("\n📅 RENDEZ-VOUS PAR STATUT :\n");
        for (StatutRendezVous statut : StatutRendezVous.values()) {
            int nb = gestionRendezVous.getRendezVousParStatut(statut).size();
            string.append("  • ").append(statut.getLibelle()).append(" : ").append(nb).append("\n");
        }

        // -- Médecins par spécialité --
        string.append("\n🩺 MÉDECINS PAR SPÉCIALITÉ :\n");
        for (Specialite sp : Specialite.values()) {
            int nb = gestionMedecins.rechercherParSpecialite(sp).size();
            if (nb > 0) {
                string.append("  • ").append(sp.getLibelle()).append(" : ").append(nb).append("\n");
            }
        }

        // -- Top médecins (par nombre de RDV) --
        string.append("\n🏆 MÉDECINS PAR NOMBRE DE RENDEZ-VOUS :\n");
        for (Medecin m : gestionMedecins.listerTousLesMedecins()) {
            int nbRdv = gestionRendezVous.getRendezVousMedecin(m).size();
            int nbOrdo = gestionOrdonnances.getOrdonnancesMedecin(m).size();
            string.append("  • Dr ").append(m.getNomComplet()).append(" (").append(m.getSpecialite().getLibelle()).append(")").append(" → ").append(nbRdv).append(" RDV, ").append(nbOrdo).append(" ordonnances\n");
        }

        // -- Ordonnances valides --
        string.append("\n💊 ORDONNANCES VALIDES (en cours) : ").append(gestionOrdonnances.getOrdonnancesValides().size());

        string.append("\n══════════════════════════════════════════════\n");
        return string.toString();
    }

    // -------------------------
    // Méthodes utilitaires
    // -------------------------

    /** * Compte le nombre de rendez-vous pour un statut particulier.
     * * @param statut Le statut du rendez-vous que l'on veut compter (Ex: CONFIRME, ANNULE).
     * @return Le nombre total de rendez-vous qui ont ce statut.
     */
    public int getNombreRendezVousParStatut(StatutRendezVous statut) {
        return gestionRendezVous.getRendezVousParStatut(statut).size();
    }

    /** * Cherche le medecin qui a le plus grand nombre de rendez-vous.
     * * @return Le medecin le plus actif de la liste, ou null si aucun medecin n'a de rendez-vous.
     */
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

    /**
     * Cree une version texte simplifiee de l'objet Statistiques avec les compteurs principaux.
     *
     * @return Une chaine de caracteres resumant le contenu de l'objet.
     */
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
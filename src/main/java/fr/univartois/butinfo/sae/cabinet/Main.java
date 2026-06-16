package fr.univartois.butinfo.sae.cabinet;

import fr.univartois.butinfo.sae.cabinet.model.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.model.modele.Medecin;
import fr.univartois.butinfo.sae.cabinet.model.modele.Medicament;
import fr.univartois.butinfo.sae.cabinet.model.modele.Ordonnance;
import fr.univartois.butinfo.sae.cabinet.model.modele.Patient;
import fr.univartois.butinfo.sae.cabinet.model.modele.RendezVous;
import fr.univartois.butinfo.sae.cabinet.model.service.GestionMedecins;
import fr.univartois.butinfo.sae.cabinet.model.service.GestionMedicaments;
import fr.univartois.butinfo.sae.cabinet.model.service.GestionOrdonnances;
import fr.univartois.butinfo.sae.cabinet.model.service.GestionPatients;
import fr.univartois.butinfo.sae.cabinet.model.service.GestionRendezVous;
import fr.univartois.butinfo.sae.cabinet.model.utils.Recherche;
import fr.univartois.butinfo.sae.cabinet.model.utils.Statistiques;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe principale du projet (SAE Cabinet Medical).
 * <p>
 * Elle contient la methode main pour lancer un scenario de test.
 * On cree des donnees, on les modifie et on verifie que tout fonctionne.
 * </p>
 * * @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public class Main {

    /**
     * Le logger pour afficher des messages dans la console.
     */
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    /**
     * La methode principale qui lance les tests du programme.
     *
     * @param args Les arguments de la ligne de commande (pas utilises dans ce programme).
     */
    public static void main(String[] args) {

        LOGGER.info("╔═══════════════════════════════════════════╗");
        LOGGER.info("║   SYSTÈME DE GESTION DE CABINET MÉDICAL   ║");
        LOGGER.info("╚═══════════════════════════════════════════╝\n");

        // ============================================================
        // 1. Creation des services de gestion
        // ============================================================

        /** Service pour gérer la liste des patients */
        GestionPatients gestionPatients       = new GestionPatients();

        /** Service pour gérer la liste des medecins */
        GestionMedecins gestionMedecins       = new GestionMedecins();

        /** Service pour gérer la liste des medicaments */
        GestionMedicaments gestionMedicaments = new GestionMedicaments();

        /** Service pour gérer les rendez-vous */
        GestionRendezVous gestionRdv          = new GestionRendezVous();

        /** Service pour gérer les ordonnances */
        GestionOrdonnances gestionOrdonnances = new GestionOrdonnances();

        /** * Outil pour faire des recherches de patients ou de medecins.
         * @param gestionPatients Le service contenant la liste des patients.
         * @param gestionMedecins Le service contenant la liste des medecins.
         */
        Recherche recherche                   = new Recherche(gestionPatients, gestionMedecins);

        /** * Outil pour afficher des statistiques sur le cabinet.
         * @param gestionPatients Le service des patients.
         * @param gestionMedecins Le service des medecins.
         * @param gestionMedicaments Le service des medicaments.
         * @param gestionRdv Le service des rendez-vous.
         * @param gestionOrdonnances Le service des ordonnances.
         */
        Statistiques stats                    = new Statistiques(
                gestionPatients, gestionMedecins, gestionMedicaments, gestionRdv, gestionOrdonnances);

        // ============================================================
        // 2. Creation et ajout des medecins
        // ============================================================
        LOGGER.info("=== AJOUT DES MÉDECINS ===");

        /** * Creation du premier medecin (Generaliste).
         * @param nom "Dupont"
         * @param prenom "Jean"
         * @param telephone "0312345678"
         * @param email "j.dupont@cabinet.fr"
         * @param numOrdre "ORD-001"
         * @param specialite Specialite.GENERALISTE
         * @param tarif 30.0
         */
        Medecin dr1 = new Medecin("Dupont", "Jean", "0312345678", "j.dupont@cabinet.fr",
                "ORD-001", Specialite.GENERALISTE, 30.0);

        /** * Creation du deuxieme medecin (Cardiologue).
         * @param nom "Martin"
         * @param prenom "Sophie"
         * @param telephone "0398765432"
         * @param email "s.martin@cabinet.fr"
         * @param numOrdre "ORD-002"
         * @param specialite Specialite.CARDIOLOGUE
         * @param tarif 50.0
         */
        Medecin dr2 = new Medecin("Martin", "Sophie", "0398765432", "s.martin@cabinet.fr",
                "ORD-002", Specialite.CARDIOLOGUE, 50.0);

        /** * Creation du troisieme medecin (Pediatre).
         * @param nom "Bernard"
         * @param prenom "Lucie"
         * @param telephone "0367891234"
         * @param email "l.bernard@cabinet.fr"
         * @param numOrdre "ORD-003"
         * @param specialite Specialite.PEDIATRE
         * @param tarif 40.0
         */
        Medecin dr3 = new Medecin("Bernard", "Lucie", "0367891234", "l.bernard@cabinet.fr",
                "ORD-003", Specialite.PEDIATRE, 40.0);

        // On ajoute les 3 medecins dans le service de gestion
        gestionMedecins.ajouterMedecin(dr1);
        gestionMedecins.ajouterMedecin(dr2);
        gestionMedecins.ajouterMedecin(dr3);

        // ============================================================
        // 3. Creation et ajout des patients
        // ============================================================
        LOGGER.info("\n=== AJOUT DES PATIENTS ===");

        /** * Creation du patient Paul Leblanc.
         * @param nom "Leblanc"
         * @param prenom "Paul"
         * @param telephone "0612345678"
         * @param email "p.leblanc@mail.fr"
         * @param adresse "12 rue des Lilas, Arras"
         * @param dateNaissance 22 mars 1985
         * @param numeroSecu "185036562012345"
         * @param tauxPriseEnCharge 0.50 (50%)
         */
        Patient p1 = new Patient("Leblanc", "Paul", "0612345678", "p.leblanc@mail.fr",
                "12 rue des Lilas, Arras",
                LocalDate.of(1985, 3, 22), "185036562012345", .50);

        /** * Creation de la patiente Claire Moreau.
         * @param nom "Moreau"
         * @param prenom "Claire"
         * @param telephone "0687654321"
         * @param email "c.moreau@mail.fr"
         * @param adresse "8 avenue Foch, Lens"
         * @param dateNaissance 15 juillet 1995
         * @param numeroSecu "295076210054321"
         */
        Patient p2 = new Patient("Moreau", "Claire", "0687654321", "c.moreau@mail.fr",
                "8 avenue Foch, Lens",
                LocalDate.of(1995, 7, 15), "295076210054321");

        /** * Creation du patient Antoine Petit.
         * @param nom "Petit"
         * @param prenom "Antoine"
         * @param telephone "0655443322"
         * @param email "a.petit@mail.fr"
         * @param adresse "3 impasse du Moulin, Douai"
         * @param dateNaissance 5 novembre 2010
         * @param numeroSecu "210116210011122"
         * @param tauxPriseEnCharge 0.60 (60%)
         */
        Patient p3 = new Patient("Petit", "Antoine", "0655443322", "a.petit@mail.fr",
                "3 impasse du Moulin, Douai",
                LocalDate.of(2010, 11, 5), "210116210011122", .6);

        // On ajoute les 3 patients dans le service de gestion
        gestionPatients.ajouterPatient(p1);
        gestionPatients.ajouterPatient(p2);
        gestionPatients.ajouterPatient(p3);

        // ============================================================
        // 4. Creation et ajout des medicaments
        // ============================================================
        LOGGER.info("\n=== AJOUT DES MÉDICAMENTS ===");

        /** * Creation du Doliprane.
         * @param nom "Doliprane 1000mg"
         * @param description "Antalgique et antipyrétique"
         * @param prix 5.5
         */
        Medicament med1 = new Medicament("Doliprane 1000mg",
                "Antalgique et antipyrétique", 5.5);

        /** * Creation de l'Amoxicilline.
         * @param nom "Amoxicilline 500mg"
         * @param description "Antibiotique à large spectre"
         * @param prix 13.25
         */
        Medicament med2 = new Medicament("Amoxicilline 500mg",
                "Antibiotique à large spectre", 13.25);

        /** * Creation de l'Ibuprofene.
         * @param nom "Ibuprofène 400mg"
         * @param description "Anti-inflammatoire non stéroïdien"
         * @param prix 43.12
         */
        Medicament med3 = new Medicament("Ibuprofène 400mg",
                "Anti-inflammatoire non stéroïdien", 43.12);

        /** * Creation du Lisinopril.
         * @param nom "Lisinopril 10mg"
         * @param description "Inhibiteur de l'enzyme de conversion"
         * @param prix 24.56
         */
        Medicament med4 = new Medicament("Lisinopril 10mg",
                "Inhibiteur de l'enzyme de conversion", 24.56);

        // On ajoute les medicaments au catalogue du cabinet
        gestionMedicaments.ajouterMedicament(med1);
        gestionMedicaments.ajouterMedicament(med2);
        gestionMedicaments.ajouterMedicament(med3);
        gestionMedicaments.ajouterMedicament(med4);

        // ============================================================
        // 5. Gestion des rendez-vous
        // ============================================================
        LOGGER.info("\n=== PRISE DE RENDEZ-VOUS ===");

        // On cree des dates et des heures pour les rendez-vous
        LocalDateTime rdv1Date = LocalDateTime.of(2025, 9, 10, 9, 0);
        LocalDateTime rdv2Date = LocalDateTime.of(2025, 9, 10, 10, 30);
        LocalDateTime rdv3Date = LocalDateTime.of(2025, 9, 11, 14, 0);

        /** * Prise du rendez-vous 1.
         * @param patient p1 (Paul Leblanc)
         * @param medecin dr1 (Dr Dupont)
         * @param date rdv1Date
         * @param duree 30 minutes
         * @param motif "Consultation de routine"
         */
        RendezVous rdv1 = gestionRdv.prendreRendezVous(p1, dr1, rdv1Date, 30, "Consultation de routine");

        /** * Prise du rendez-vous 2.
         * @param patient p2 (Claire Moreau)
         * @param medecin dr2 (Dr Martin)
         * @param date rdv2Date
         * @param duree 45 minutes
         * @param motif "Bilan cardiaque"
         */
        RendezVous rdv2 = gestionRdv.prendreRendezVous(p2, dr2, rdv2Date, 45, "Bilan cardiaque");

        /** * Prise du rendez-vous 3.
         * @param patient p3 (Antoine Petit)
         * @param medecin dr3 (Dr Bernard)
         * @param date rdv3Date
         * @param duree 20 minutes
         * @param motif "Vaccination enfant"
         */
        RendezVous rdv3 = gestionRdv.prendreRendezVous(p3, dr3, rdv3Date, 20, "Vaccination enfant");

        // Test : On confirme le premier rendez-vous avec son identifiant
        if (rdv1 != null) gestionRdv.confirmerRendezVous(rdv1.getId());

        // Test : On annule le troisieme rendez-vous avec son identifiant
        if (rdv3 != null) gestionRdv.annulerRendezVous(rdv3.getId());

        // ============================================================
        // 6. Creation des ordonnances
        // ============================================================
        LOGGER.info("\n=== CRÉATION D'ORDONNANCES ===");

        /** * On cree une premiere ordonnance pour Paul Leblanc faite par le Dr Dupont.
         * @param medecin dr1
         * @param patient p1
         * @param dateExpiration Date du jour + 3 mois
         * @param instructions "Renouveler si nécessaire"
         */
        Ordonnance ordo1 = gestionOrdonnances.creerOrdonnance(
                dr1, p1, LocalDate.now().plusMonths(3), "Renouveler si nécessaire");

        // On ajoute deux medicaments sur cette premiere ordonnance
        /**
         * @param idOrdonnance ordo1.getId()
         * @param medicament med1 (Doliprane)
         * @param quantite 4
         * @param posologie "2 comprimés par jour"
         * @param dureeTraitement 7 jours
         * @param instructionsParticulieres "À prendre avec de l'eau"
         */
        gestionOrdonnances.ajouterPrescription(
                ordo1.getId(), med1, 4,"2 comprimés par jour", 7, "À prendre avec de l'eau");

        /**
         * @param idOrdonnance ordo1.getId()
         * @param medicament med2 (Amoxicilline)
         * @param quantite 3
         * @param posologie "1 comprimé 3 fois/jour"
         * @param dureeTraitement 10 jours
         * @param instructionsParticulieres "Ne pas dépasser 3g/jour"
         */
        gestionOrdonnances.ajouterPrescription(
                ordo1.getId(), med2, 3,"1 comprimé 3 fois/jour", 10, "Ne pas dépasser 3g/jour");

        /** * On cree une deuxieme ordonnance pour Claire Moreau faite par le Dr Martin.
         * @param medecin dr2
         * @param patient p2
         * @param dateExpiration Date du jour + 6 mois
         * @param instructions "Contrôle dans 1 mois"
         */
        Ordonnance ordo2 = gestionOrdonnances.creerOrdonnance(
                dr2, p2, LocalDate.now().plusMonths(6), "Contrôle dans 1 mois");

        // On ajoute un medicament sur cette deuxieme ordonnance
        /**
         * @param idOrdonnance ordo2.getId()
         * @param medicament med4 (Lisinopril)
         * @param quantite 1
         * @param posologie "1 comprimé le matin"
         * @param dureeTraitement 30 jours
         * @param instructionsParticulieres "Surveillance tension artérielle"
         */
        gestionOrdonnances.ajouterPrescription(
                ordo2.getId(), med4, 1,"1 comprimé le matin", 30, "Surveillance tension artérielle");

        // On affiche les details des ordonnances dans la console
        LOGGER.info("\n--- Détail des ordonnances ---");
        LOGGER.info(ordo1);
        LOGGER.info(ordo2);

        // ============================================================
        // 7. Tests de recherche
        // ============================================================
        LOGGER.info("\n=== MODULE DE RECHERCHE ===");

        // Recherche d'un patient par son nom de famille
        LOGGER.info("\n>> Recherche patient par nom 'leblanc':");
        List<Patient> patientsParNom = recherche.rechercherPatientParNom("leblanc");
        LOGGER.info("--- Patients trouvés pour \"leblanc\" (" + patientsParNom.size() + " résultat(s)) ---");
        for (Patient p : patientsParNom) {
            LOGGER.info("  " + p);
        }

        // Recherche des medecins qui ont la specialite Cardiologue
        LOGGER.info("\n>> Recherche médecin par spécialité CARDIOLOGUE:");
        List<Medecin> medecinsParSpec = recherche.rechercherMedecinParSpecialite(Specialite.CARDIOLOGUE);
        LOGGER.info("--- Médecins de spécialité \"Cardiologue\" (" + medecinsParSpec.size() + " résultat(s)) ---");
        for (Medecin m : medecinsParSpec) {
            LOGGER.info("  " + m);
        }

        // Recherche d'un medecin par son nom de famille
        LOGGER.info("\n>> Recherche médecin par nom 'martin':");
        List<Medecin> medecinsParNom = recherche.rechercherMedecinParNom("martin");
        LOGGER.info("--- Médecins trouvés pour \"martin\" (" + medecinsParNom.size() + " résultat(s)) ---");
        for (Medecin m : medecinsParNom) {
            LOGGER.info("  " + m);
        }

        // Recherche d'un patient avec son numero de securite sociale
        LOGGER.info("\n>> Recherche patient par n° sécu:");
        Patient patientSecu = recherche.rechercherPatientParSecu("185036562012345");
        if (patientSecu != null) {
            LOGGER.info("Patient trouvé : " + patientSecu);
        }

        // ============================================================
        // 8. Tests des statistiques
        // ============================================================
        LOGGER.info("\n=== MODULE DE STATISTIQUES ===");

        // On affiche le texte des statistiques globales
        LOGGER.info(stats.getStatistiques());

        // On cherche quel medecin a fait le plus de rendez-vous
        Medecin actif = stats.getMedecinLePlusActif();
        if (actif != null) {
            LOGGER.info("Médecin le plus actif : Dr " + actif.getNomComplet());
        } else {
            LOGGER.debug("Aucun médecin actif trouvé.");
        }

        // ============================================================
        // 9. Tests de modification et suppression (CRUD)
        // ============================================================
        LOGGER.info("\n=== MISE À JOUR ET SUPPRESSION ===");

        // Modifier le prix de consultation du medecin 1
        /**
         * @param id dr1.getId()
         * @param nom "Dupont"
         * @param prenom "Jean"
         * @param tarif 33.45 (nouveau prix)
         * @param telephone "0312345678"
         * @param email "j.dupont@cabinet.fr"
         * @param specialite Specialite.GENERALISTE
         */
        gestionMedecins.mettreAJourMedecin(dr1.getId(),
                "Dupont", "Jean", 33.45, "0312345678",
                "j.dupont@cabinet.fr",  Specialite.GENERALISTE);

        // Modifier le nom et l'adresse du patient 1
        /**
         * @param id p1.getId()
         * @param nom "Leblanc"
         * @param prenom "Paul-Henri" (nouveau prenom)
         * @param telephone "0699887766" (nouveau telephone)
         * @param email "p.leblanc@newmail.fr" (nouvel email)
         * @param adresse "20 rue des Roses, Arras" (nouvelle adresse)
         * @param tauxPriseEnCharge 0.80 (nouveau taux)
         */
        gestionPatients.mettreAJourPatient(p1.getId(),
                "Leblanc", "Paul-Henri", "0699887766", "p.leblanc@newmail.fr",
                "20 rue des Roses, Arras", .80);

        // Supprimer definitivement le patient 3 de la liste
        /** @param id p3.getId() (Identifiant d'Antoine Petit) */
        gestionPatients.supprimerPatient(p3.getId());

        // ============================================================
        // 10. Affichage de controle final
        // ============================================================
        LOGGER.info("\n=== ÉTAT FINAL ===");

        // Afficher tous les patients qui restent dans la liste
        List<Patient> patients = gestionPatients.getPatients();
        if (patients.isEmpty()) {
            LOGGER.debug("Aucun patient enregistré.");
            return;
        }
        LOGGER.info("=== LISTE DES PATIENTS (" + patients.size() + ") ===");
        for (Patient p : patients) {
            LOGGER.info(p);
        }

        // Afficher tous les medecins de la liste
        List<Medecin> medecins = gestionMedecins.listerTousLesMedecins();
        if (medecins.isEmpty()) {
            LOGGER.debug("Aucun médecin enregistré.");
            return;
        }
        LOGGER.info("=== LISTE DES MÉDECINS (" + medecins.size() + ") ===");
        for (Medecin m : medecins) {
            LOGGER.info(m);
        }

        // Afficher tous les medicaments du catalogue
        List<Medicament> medicaments = gestionMedicaments.listerTousLesMedicaments();
        if (medicaments.isEmpty()) {
            LOGGER.debug("Aucun médicament enregistré.");
            return;
        }
        LOGGER.info("=== LISTE DES MÉDICAMENTS (" + medicaments.size() + ") ===");
        for (Medicament m : medicaments) {
            LOGGER.info(m);
        }

        // Afficher tous les rendez-vous enregistres
        List<RendezVous> rendezVous = gestionRdv.listerTousLesRendezVous();
        if (rendezVous.isEmpty()) {
            LOGGER.info("Aucun rendez-vous enregistré.");
            return;
        }
        LOGGER.info("=== LISTE DES RENDEZ-VOUS (" + rendezVous.size() + ") ===");
        for (RendezVous rdv : rendezVous) {
            LOGGER.info(rdv);
        }
    }
}
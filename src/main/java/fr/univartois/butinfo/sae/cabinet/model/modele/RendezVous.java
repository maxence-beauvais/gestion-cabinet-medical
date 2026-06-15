package fr.univartois.butinfo.sae.cabinet.model.modele;

import fr.univartois.butinfo.sae.cabinet.model.enumeration.StatutRendezVous;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe représentant un rendez-vous entre un patient et un médecin.
 * <p>
 * Un rendez-vous possède un identifiant unique, une date et une heure précises,
 * une durée, un motif médical, ainsi qu'un statut permettant de suivre son évolution.
 * </p>
 *
 * @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public class RendezVous {

  /** L'identifiant (ID) numérique unique du rendez-vous. */
  private final int id;

  /** Le patient concerné par la consultation. */
  private final Patient patient;

  /** Le médecin qui assure la consultation. */
  private final Medecin medecin;

  /** Le jour et l'heure prévus pour le rendez-vous. */
  private LocalDateTime dateHeure;

  /** La durée du rendez-vous exprimée en minutes. */
  private int dureeMinutes;

  /** Le statut actuel du rendez-vous (Ex: PLANIFIE, CONFIRME, ANNULE, TERMINE). */
  private StatutRendezVous statut;

  /** La raison ou le motif de la consultation. */
  private String motif;

  /** Compteur statique partagé pour attribuer automatiquement des identifiants uniques séquentiels. */
  private static int compteurId = 1;

  /** Format d'affichage personnalisé pour la date et l'heure dans la console. */
  private static final DateTimeFormatter FORMATTER =
          DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");

  /**
   * Constructeur complet d'un RendezVous. Le statut est initialisé à PLANIFIE par défaut.
   *
   * @param patient Le patient qui prend le rendez-vous.
   * @param medecin Le médecin choisi pour la consultation.
   * @param dateHeure Le jour et l'heure du créneau.
   * @param dureeMinutes La durée totale prévue de la consultation en minutes.
   * @param motif La raison médicale du rendez-vous.
   */
  public RendezVous(Patient patient, Medecin medecin, LocalDateTime dateHeure,
                    int dureeMinutes, String motif) {
    this.id = compteurId++;
    this.patient = patient;
    this.medecin = medecin;
    this.dateHeure = dateHeure;
    this.dureeMinutes = dureeMinutes;
    this.statut = StatutRendezVous.PLANIFIE;
    this.motif = motif;
  }

  /**
   * Constructeur simplifié créant un rendez-vous avec une durée par défaut de 30 minutes.
   *
   * @param patient Le patient qui prend le rendez-vous.
   * @param medecin Le médecin choisi pour la consultation.
   * @param dateHeure Le jour et l'heure du créneau.
   * @param motif La raison médicale du rendez-vous.
   */
  public RendezVous(Patient patient, Medecin medecin, LocalDateTime dateHeure, String motif) {
    this(patient, medecin, dateHeure, 30, motif);
  }

  // -------------------------
  // Actions métier
  // -------------------------

  /**
   * Confirme le rendez-vous en faisant passer son statut à CONFIRME.
   * Cette action n'est possible que si le rendez-vous est actuellement à l'état PLANIFIE.
   */
  public void confirmer() {
    if (this.statut == StatutRendezVous.PLANIFIE) {
      this.statut = StatutRendezVous.CONFIRME;
    }
  }

  /**
   * Annule le rendez-vous en faisant passer son statut à ANNULE.
   * Cette action est impossible si la consultation est déjà notée comme terminée.
   */
  public void annuler() {
    if (this.statut != StatutRendezVous.TERMINE) {
      this.statut = StatutRendezVous.ANNULE;
    }
  }

  /**
   * Marque le rendez-vous comme terminé en faisant passer son statut à TERMINE.
   */
  public void terminer() {
    this.statut = StatutRendezVous.TERMINE;
  }

  // -------------------------
  // Getters et Setters
  // -------------------------

  /**
   * Récupère l'identifiant unique du rendez-vous.
   *
   * @return L'identifiant (ID) numérique.
   */
  public int getId() {
    return id;
  }

  /**
   * Récupère le patient associé à ce rendez-vous.
   *
   * @return L'objet Patient correspondant.
   */
  public Patient getPatient() {
    return patient;
  }

  /**
   * Récupère le médecin associé à ce rendez-vous.
   *
   * @return L'objet Medecin correspondant.
   */
  public Medecin getMedecin() {
    return medecin;
  }

  /**
   * Récupère le jour et l'heure de la consultation.
   *
   * @return Un objet LocalDateTime contenant la date et l'heure.
   */
  public LocalDateTime getDateHeure() {
    return dateHeure;
  }

  /**
   * Modifie le jour et l'heure du rendez-vous.
   *
   * @param dateHeure La nouvelle date et heure de la consultation.
   */
  public void setDateHeure(LocalDateTime dateHeure) {
    this.dateHeure = dateHeure;
  }

  /**
   * Récupère la durée en minutes de la consultation.
   *
   * @return Le nombre de minutes du créneau.
   */
  public int getDureeMinutes() {
    return dureeMinutes;
  }

  /**
   * Modifie la durée en minutes de la consultation.
   *
   * @param dureeMinutes Le nouveau nombre de minutes prévu.
   */
  public void setDureeMinutes(int dureeMinutes) {
    this.dureeMinutes = dureeMinutes;
  }

  /**
   * Récupère le statut actuel du rendez-vous.
   *
   * @return Le statut sous forme d'une valeur de l'énumération StatutRendezVous.
   */
  public StatutRendezVous getStatut() {
    return statut;
  }

  /**
   * Modifie directement le statut du rendez-vous.
   *
   * @param statut Le nouveau statut à appliquer au rendez-vous.
   */
  public void setStatut(StatutRendezVous statut) {
    this.statut = statut;
  }

  /**
   * Récupère le motif médical saisi pour la consultation.
   *
   * @return Une chaîne de caractères décrivant la raison du rendez-vous.
   */
  public String getMotif() {
    return motif;
  }

  /**
   * Modifie le motif de la consultation.
   *
   * @param motif Le nouveau texte décrivant la raison du rendez-vous.
   */
  public void setMotif(String motif) {
    this.motif = motif;
  }

  /**
   * Calcule le prix restant à la charge du patient pour la consultation.
   * <p>
   * Le calcul prend en compte le tarif horaire du médecin (divisé pour obtenir un coût à la minute),
   * le nombre de minutes réelles de la consultation, ainsi que le pourcentage de prise en charge du patient.
   * </p>
   *
   * @return Le montant en euros restant à payer par le patient après remboursement.
   */
  public double prixConsultation() {
    double tarifHoraire = medecin.getTarifHoraire();
    double tauxRemboursement = patient.getTauxRemboursement();
    return (tarifHoraire / 60.0) * dureeMinutes * (1.0 - tauxRemboursement);
  }

  /**
   * Calcule le code de hachage (hashCode) unique de l'objet basé sur le patient, le médecin et la date.
   *
   * @return Le code de hachage sous forme d'un entier.
   */
  @Override
  public int hashCode() {
    return patient.hashCode() * 31 + medecin.hashCode() * 17 + dateHeure.hashCode();
  }

  /**
   * Vérifie si l'objet actuel est égal à un autre objet.
   * Deux rendez-vous sont considérés comme parfaitement égaux s'ils concernent le même patient,
   * le même médecin et se déroulent exactement à la même date et heure.
   *
   * @param obj L'objet avec lequel faire la comparaison.
   * @return true si les deux rendez-vous sont équivalents, false sinon.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    RendezVous autre = (RendezVous) obj;
    return this.patient.equals(autre.patient) &&
            this.medecin.equals(autre.medecin) &&
            this.dateHeure.equals(autre.dateHeure);
  }

  /**
   * Renvoie une chaîne de caractères lisible contenant toutes les informations du rendez-vous.
   * Elle inclut les noms du patient et du médecin, la date formatée, la durée, le motif, le statut ainsi que le coût final calculé.
   *
   * @return Le descriptif textuel complet du rendez-vous.
   */
  @Override
  public String toString() {
    return "RDV      { [ID=" + id + "] " +
            patient.getNomComplet() + " avec Dr " + medecin.getNomComplet() +
            " | " + dateHeure.format(FORMATTER) +
            " (" + dureeMinutes + " min)" +
            " | Motif: " + motif +
            " | Statut: " + statut.getLibelle() +
            " | Coût : " + prixConsultation() + " }";
  }
}
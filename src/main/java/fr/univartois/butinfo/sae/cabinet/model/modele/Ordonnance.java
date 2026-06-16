package fr.univartois.butinfo.sae.cabinet.model.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une ordonnance médicale.
 * <p>
 * Une ordonnance est émise par un médecin pour un patient donné. Elle contient une date
 * d'émission, une date de validité, d'éventuelles remarques textuelles ainsi qu'une
 * liste de lignes de prescription détaillant les médicaments à délivrer.
 * </p>
 *
 * @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public class Ordonnance {

  /** Identifiant numérique unique de l'ordonnance. */
  private final int id;

  /** Le médecin ayant rédigé et délivré l'ordonnance. */
  private final Medecin medecin;

  /** Le patient à qui est destinée l'ordonnance. */
  private final Patient patient;

  /** La date à laquelle l'ordonnance a été créée. */
  private final LocalDate dateEmission;

  /** La date limite de validité de l'ordonnance. */
  private LocalDate dateValidite;

  /** La liste interne des médicaments et prescriptions rattachés à cette ordonnance. */
  private final ObservableList<LignePrescription> prescriptions;

  /** Remarques ou consignes particulières écrites par le médecin. */
  private String remarques;

  /** Compteur statique partagé pour attribuer automatiquement un identifiant unique à chaque création. */
  private static int compteurId = 1;

  /**
   * Constructeur complet d'une Ordonnance.
   * La date d'émission est automatiquement définie à la date du jour et la liste des prescriptions est initialisée vide.
   *
   * @param medecin Le médecin prescripteur.
   * @param patient Le patient bénéficiaire.
   * @param dateValidite La date maximale de validité.
   * @param remarques Les consignes ou précisions textuelles.
   */
  public Ordonnance(Medecin medecin, Patient patient, LocalDate dateValidite, String remarques) {
    this.id = compteurId++;
    this.medecin = medecin;
    this.patient = patient;
    this.dateEmission = LocalDate.now();
    this.dateValidite = dateValidite;
    this.prescriptions = FXCollections.observableArrayList();
    this.remarques = remarques;
  }

  /**
   * Constructeur simplifié d'une Ordonnance.
   * Il applique une durée de validité par défaut de 3 mois à partir d'aujourd'hui et laisse les remarques vides.
   *
   * @param medecin Le médecin prescripteur.
   * @param patient Le patient bénéficiaire.
   */
  public Ordonnance(Medecin medecin, Patient patient) {
    this(medecin, patient, LocalDate.now().plusMonths(3), "");
  }

  // -------------------------
  // Gestion des prescriptions
  // -------------------------

  /**
   * Ajoute une nouvelle ligne de prescription (un médicament avec ses détails) à l'ordonnance.
   *
   * @param ligne La ligne de prescription à ajouter.
   */
  public void ajouterPrescription(LignePrescription ligne) {
    prescriptions.add(ligne);
  }

  /**
   * Supprime une ligne de prescription de l'ordonnance en cherchant le médicament associé.
   *
   * @param medicament Le médicament dont on veut retirer la prescription.
   * @return true si une prescription correspondante a été trouvée et supprimée, false sinon.
   */
  public boolean supprimerPrescription(Medicament medicament) {
    return prescriptions.removeIf(l -> l.getMedicament().equals(medicament));
  }

  // -------------------------
  // Getters et Setters
  // -------------------------



  /**
   * Récupère l'identifiant unique de l'ordonnance.
   *
   * @return L'identifiant (ID) numérique.
   */
  public int getId() {
    return id;
  }

  /**
   * Récupère le médecin ayant rédigé cette ordonnance.
   *
   * @return L'objet Medecin correspondant.
   */
  public Medecin getMedecin() {
    return medecin;
  }

  /**
   * Récupère le patient concerné par cette ordonnance.
   *
   * @return L'objet Patient correspondant.
   */
  public Patient getPatient() {
    return patient;
  }

  /**
   * Récupère la date à laquelle l'ordonnance a été émise.
   *
   * @return La date d'émission sous forme de LocalDate.
   */
  public LocalDate getDateEmission() {
    return dateEmission;
  }

  /**
   * Récupère la date de fin de validité de l'ordonnance.
   *
   * @return La date de validité maximale.
   */
  public LocalDate getDateValidite() {
    return dateValidite;
  }

  /**
   * Modifie la date de fin de validité de l'ordonnance.
   *
   * @param dateValidite La nouvelle date limite de validité.
   */
  public void setDateValidite(LocalDate dateValidite) {
    this.dateValidite = dateValidite;
  }

  /**
   * Récupère l'ensemble des prescriptions de l'ordonnance.
   * Une copie défensive est renvoyée afin de protéger la liste interne contre les modifications non contrôlées.
   *
   * @return Une nouvelle liste contenant toutes les lignes de prescription.
   */
  public List<LignePrescription> getPrescriptions() {
    return new ArrayList<>(prescriptions);
  }

  /**
   * Récupère les remarques écrites sur l'ordonnance.
   *
   * @return Les remarques ou consignes sous forme de texte.
   */
  public String getRemarques() {
    return remarques;
  }

  /**
   * Modifie ou ajoute des remarques particulières sur l'ordonnance.
   *
   * @param remarques Le nouveau texte de consignes.
   */
  public void setRemarques(String remarques) {
    this.remarques = remarques;
  }

  /**
   * Vérifie si l'ordonnance est toujours valide par rapport à la date du jour.
   *
   * @return true si la date d'aujourd'hui n'a pas dépassé la date de validité, false sinon.
   */
  public boolean estValide() {
    return !LocalDate.now().isAfter(dateValidite);
  }

  /**
   * Vérifie si l'objet actuel est égal à un autre objet.
   * Deux ordonnances sont considérées comme parfaitement égales si elles possèdent le même identifiant unique.
   *
   * @param obj L'objet avec lequel faire la comparaison.
   * @return true si les deux ordonnances partagent le même ID, false sinon.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Ordonnance o = (Ordonnance) obj;
    return id == o.id;
  }

  /**
   * Calcule le code de hachage (hashCode) de l'ordonnance à partir de son identifiant numérique unique.
   *
   * @return Le code de hachage sous forme d'un entier.
   */
  @Override
  public int hashCode() {
    return Integer.hashCode(id);
  }

  /**
   * Calcule le coût total estimé restant à la charge du patient pour l'intégralité de l'ordonnance.
   * <p>
   * La méthode calcule la somme du prix de chaque médicament multiplié par sa quantité prescrite,
   * puis applique le pourcentage de reste à charge dépendant du taux de remboursement du patient.
   * </p>
   *
   * @return Le montant total en euros non remboursé par la sécurité sociale pour cette ordonnance.
   */
  public double getPrixTotal() {
    double prixTotal = 0.0;
    for (LignePrescription lp : prescriptions) {
      prixTotal += lp.getMedicament().getPrix() * lp.getQuantite();
    }
    return prixTotal * (1.0 - patient.getTauxRemboursement());
  }

  /**
   * Renvoie un bilan textuel complet et formaté de l'ordonnance.
   * Il comprend l'ID, l'identité et la spécialité du médecin, le nom du patient, les dates d'émission et de validité,
   * le détail ligne par ligne des médicaments prescrits, le coût total estimé ainsi que les remarques éventuelles.
   *
   * @return Une chaîne de caractères représentant visuellement l'ordonnance médicale.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("=== ORDONNANCE N°").append(id).append(" ===\n");
    sb.append("  Médecin  : Dr ").append(medecin.getNomComplet())
            .append(" (").append(medecin.getSpecialite().getLibelle()).append(")\n");
    sb.append("  Patient  : ").append(patient.getNomComplet()).append("\n");
    sb.append("  Émise le : ").append(dateEmission).append("\n");
    sb.append("  Valide jusqu'au : ").append(dateValidite).append("\n");
    sb.append("  Médicaments prescrits :\n");
    for (LignePrescription lp : prescriptions) {
      sb.append(lp.toString()).append("\n");
    }
    sb.append("  Prix total estimé : ").append(String.format("%.2f €", getPrixTotal())).append("\n");
    if (!remarques.isEmpty()) {
      sb.append("  Remarques : ").append(remarques).append("\n");
    }
    return sb.toString();
  }
}
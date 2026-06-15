package fr.univartois.butinfo.sae.cabinet.model.modele;

/**
 * Classe représentant une ligne de prescription dans une ordonnance.
 * <p>
 * Une ligne de prescription associe un médicament précis à une quantité à délivrer,
 * une posologie, une durée globale de traitement ainsi que d'éventuelles consignes spécifiques.
 * </p>
 *
 * @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public class LignePrescription {

  /** Le médicament faisant l'objet de la prescription. */
  private final Medicament medicament;

  /** La quantité totale de boîtes ou d'unités de médicament à délivrer. */
  private int quantite;

  /** La posologie du traitement (Ex : "1 comprimé matin et soir"). */
  private String posologie;

  /** La durée totale du traitement exprimée en jours. */
  private int dureeTraitement;

  /** Consignes ou instructions particulières liées à la prise du traitement. */
  private String instructions;

  /**
   * Constructeur complet d'une ligne de prescription.
   *
   * @param medicament Le médicament prescrit.
   * @param quantite Le nombre d'unités ou boîtes à délivrer.
   * @param posologie La description des moments et doses de prise.
   * @param dureeTraitement Le nombre de jours pendant lesquels le traitement doit être suivi.
   * @param instructions Les consignes spécifiques de prise.
   */
  public LignePrescription(Medicament medicament, int quantite, String posologie,
                           int dureeTraitement, String instructions) {
    this.medicament = medicament;
    this.quantite = quantite;
    this.posologie = posologie;
    this.dureeTraitement = dureeTraitement;
    this.instructions = instructions;
  }

  /**
   * Constructeur simplifié d'une ligne de prescription.
   * Il applique une quantité par défaut de 1 et indique qu'il n'y a aucune instruction particulière.
   *
   * @param medicament Le médicament prescrit.
   * @param posologie La description des moments et doses de prise.
   * @param dureeTraitement Le nombre de jours pendant lesquels le traitement doit être suivi.
   */
  public LignePrescription(Medicament medicament, String posologie, int dureeTraitement) {
    this(medicament, 1, posologie, dureeTraitement, "Aucune instruction particulière");
  }

  // -------------------------
  // Getters et Setters
  // -------------------------

  /**
   * Récupère le médicament lié à cette ligne de prescription.
   *
   * @return L'objet Medicament concerné.
   */
  public Medicament getMedicament() {
    return medicament;
  }

  /**
   * Récupère la posologie du traitement.
   *
   * @return Une chaîne de caractères décrivant la posologie.
   */
  public String getPosologie() {
    return posologie;
  }

  /**
   * Modifie la posologie du traitement.
   *
   * @param posologie La nouvelle description des prises.
   */
  public void setPosologie(String posologie) {
    this.posologie = posologie;
  }

  /**
   * Récupère la durée totale prévue pour le traitement.
   *
   * @return Le nombre de jours du traitement.
   */
  public int getDureeTraitement() {
    return dureeTraitement;
  }

  /**
   * Modifie la durée totale du traitement.
   *
   * @param dureeTraitement Le nouveau nombre de jours.
   */
  public void setDureeTraitement(int dureeTraitement) {
    this.dureeTraitement = dureeTraitement;
  }

  /**
   * Récupère les instructions spécifiques liées à la prescription.
   *
   * @return Le texte des consignes particulières.
   */
  public String getInstructions() {
    return instructions;
  }

  /**
   * Modifie les instructions spécifiques du traitement.
   *
   * @param instructions Le nouveau texte de consignes.
   */
  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  /**
   * Récupère la quantité totale prescrite.
   *
   * @return Le nombre d'unités ou boîtes de médicament.
   */
  public int getQuantite() {
    return quantite;
  }

  /**
   * Modifie la quantité totale prescrite.
   *
   * @param quantite Le nouveau nombre d'unités ou boîtes.
   */
  public void setQuantite(int quantite) {
    this.quantite = quantite;
  }

  /**
   * Renvoie une chaîne de caractères lisible contenant les détails de la prescription.
   * Elle inclut le nom du médicament, la quantité, la posologie, la durée en jours ainsi que les instructions.
   *
   * @return Le descriptif textuel complet de la ligne de prescription.
   */
  @Override
  public String toString() {
    return "  → " + medicament.getNom() +
            " | Quantité: " + quantite +
            " | Posologie: " + posologie +
            " | Durée: " + dureeTraitement + " jours" +
            " | Instructions: " + instructions;
  }
}
package fr.univartois.butinfo.sae.cabinet.model.modele;

/**
 * Classe représentant un médicament disponible dans le système.
 * <p>
 * Un médicament possède un identifiant unique généré automatiquement, un nom commercial,
 * une description textuelle ainsi qu'un prix de vente unitaire en euros.
 * </p>
 *
 * @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public class Medicament {

  /** Identifiant numérique unique du médicament. */
  private final int id;

  /** Le nom commercial du médicament. */
  private String nom;

  /** La description du médicament (indications, effets ou composition). */
  private String description;

  /** Le prix de vente unitaire du médicament en euros. */
  private double prix;

  /** Compteur statique partagé pour attribuer automatiquement un identifiant unique à chaque création. */
  private static int compteurId = 1;

  /**
   * Constructeur complet d'un Médicament.
   *
   * @param nom Le nom commercial du médicament.
   * @param description La description ou composition du produit.
   * @param prix Le prix unitaire en euros.
   */
  public Medicament(String nom, String description, double prix) {
    this.id = compteurId++;
    this.nom = nom;
    this.description = description;
    this.prix = prix;
  }

  // -------------------------
  // Getters et Setters
  // -------------------------

  /**
   * Récupère l'identifiant unique du médicament.
   *
   * @return L'identifiant (ID) numérique.
   */
  public int getId() {
    return id;
  }

  /**
   * Récupère le nom commercial du médicament.
   *
   * @return Le nom du médicament.
   */
  public String getNom() {
    return nom;
  }

  /**
   * Modifie le nom commercial du médicament.
   *
   * @param nom Le nouveau nom commercial.
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Récupère la description du médicament.
   *
   * @return Le texte de description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Modifie la description du médicament.
   *
   * @param description La nouvelle description textuelle.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Récupère le prix unitaire du médicament.
   *
   * @return Le prix en euros.
   */
  public double getPrix() {
    return prix;
  }

  /**
   * Modifie le prix unitaire du médicament.
   *
   * @param prix Le nouveau prix en euros.
   */
  public void setPrix(double prix) {
    this.prix = prix;
  }

  /**
   * Vérifie si l'objet actuel est égal à un autre objet.
   * Deux médicaments sont considérés comme parfaitement égaux s'ils ont le même nom
   * (sans tenir compte des majuscules/minuscules) et exactement le même prix.
   *
   * @param obj L'objet avec lequel faire la comparaison.
   * @return true si les médicaments possèdent le même nom et le même prix, false sinon.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Medicament autre = (Medicament) obj;
    return this.nom.equalsIgnoreCase(autre.nom) &&
            this.prix == autre.prix;
  }

  /**
   * Calcule le code de hachage (hashCode) unique de l'objet basé sur son nom et son prix.
   *
   * @return Le code de hachage sous forme d'un entier.
   */
  @Override
  public int hashCode() {
    int result = nom.toLowerCase().hashCode();
    result = 31 * result + Double.hashCode(prix);
    return result;
  }

  /**
   * Renvoie une chaîne de caractères lisible contenant toutes les informations du médicament.
   * Elle inclut l'ID, le nom commercial, le prix en euros ainsi que sa description.
   *
   * @return Le descriptif textuel complet du médicament.
   */
  @Override
  public String toString() {
    return "Médicament { [ID=" + id + "] " + nom +
            " | Prix: " + prix +
            " | " + description + " }";
  }
}
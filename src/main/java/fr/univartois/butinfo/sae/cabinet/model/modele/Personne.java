package fr.univartois.butinfo.sae.cabinet.model.modele;

/**
 * Classe abstraite représentant une personne.
 * <p>
 * Elle sert de base commune pour les classes {@code Patient} et {@code Medecin}.
 * Elle applique le principe d'encapsulation et regroupe les attributs partagés
 * comme l'identifiant, le nom, le prénom, le téléphone et l'adresse e-mail.
 * </p>
 *
 * @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public abstract class Personne {

    /** Identifiant numérique unique de la personne. */
    private final int id;

    /** Le nom de famille de la personne. */
    protected String nom;

    /** Le prénom de la personne. */
    protected String prenom;

    /** Le numéro de téléphone de contact. */
    protected String telephone;

    /** L'adresse e-mail de contact. */
    protected String email;

    /** Compteur statique partagé pour attribuer automatiquement un identifiant unique à chaque création. */
    private static int compteurId = 1;

    /**
     * Constructeur complet de la classe Personne.
     *
     * @param nom Le nom de famille de la personne.
     * @param prenom Le prénom de la personne.
     * @param telephone Le numéro de téléphone de contact.
     * @param email L'adresse e-mail de contact.
     */
    public Personne(String nom, String prenom, String telephone, String email) {
        this.id = compteurId++;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
    }

    // -------------------------
    // Getters et Setters
    // -------------------------

    /**
     * Récupère l'identifiant unique de la personne.
     *
     * @return L'identifiant (ID) numérique.
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère le nom de famille de la personne.
     *
     * @return Le nom de famille.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom de famille de la personne.
     *
     * @param nom Le nouveau nom de famille.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le prénom de la personne.
     *
     * @return Le prénom.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie le prénom de la personne.
     *
     * @param prenom Le nouveau prénom.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Récupère le numéro de téléphone de la personne.
     *
     * @return Le numéro de téléphone.
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Modifie le numéro de téléphone de la personne.
     *
     * @param telephone Le nouveau numéro de téléphone.
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Récupère l'adresse e-mail de la personne.
     *
     * @return L'adresse e-mail.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifie l'adresse e-mail de la personne.
     *
     * @param email La nouvelle adresse e-mail.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retourne le nom complet de la personne en combinant son prénom et son nom.
     *
     * @return Une chaîne de caractères contenant le prénom suivi d'un espace et du nom.
     */
    public String getNomComplet() {
        return prenom + " " + nom;
    }

    /**
     * Vérifie si l'objet actuel est égal à un autre objet.
     * Deux personnes sont considérées comme parfaitement égales si elles possèdent le même identifiant numérique unique.
     *
     * @param obj L'objet avec lequel faire la comparaison.
     * @return true si les deux personnes possèdent le même ID, false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Personne autre = (Personne) obj;
        return this.id == autre.id;
    }

    /**
     * Calcule le code de hachage (hashCode) unique de l'objet basé sur son identifiant.
     *
     * @return Le code de hachage sous forme d'un entier.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    /**
     * Renvoie une chaîne de caractères lisible affichant les informations principales de la personne.
     * Elle inclut l'ID, le prénom, le nom, le téléphone et l'adresse e-mail.
     *
     * @return Le descriptif textuel de la personne.
     */
    @Override
    public String toString() {
        return "[ID=" + id + "] " + prenom + " " + nom +
                " | Tél: " + telephone +
                " | Email: " + email;
    }
}
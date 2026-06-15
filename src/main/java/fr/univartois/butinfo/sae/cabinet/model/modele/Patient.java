package fr.univartois.butinfo.sae.cabinet.model.modele;

import java.time.LocalDate;
import java.time.Period;

/**
 * Classe représentant un patient du cabinet médical.
 * <p>
 * Elle hérite de la classe abstraite {@code Personne} et y ajoute des informations
 * spécifiques comme l'adresse postale, la date de naissance, le numéro de sécurité sociale
 * ainsi que le taux de remboursement pour les soins reçus.
 * </p>
 *
 * @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public class Patient extends Personne {

    /** L'adresse postale de résidence du patient. */
    private String adresse;

    /** La date de naissance du patient. */
    private LocalDate dateNaissance;

    /** Le numéro de sécurité sociale du patient (identifiant immuable auprès de l'assurance maladie). */
    private final String numeroSecu;

    /** Le taux de prise en charge (remboursement) par la sécurité sociale, exprimé sous forme de valeur décimale. */
    private double tauxRemboursement;

    /**
     * Constructeur complet d'un Patient.
     *
     * @param nom Le nom de famille du patient.
     * @param prenom Le prénom du patient.
     * @param telephone Le numéro de téléphone de contact.
     * @param email L'adresse e-mail de contact.
     * @param adresse L'adresse postale du domicile.
     * @param dateNaissance La date de naissance du patient.
     * @param numeroSecu Le numéro de sécurité sociale (immuable).
     * @param tauxRemboursement Le taux de remboursement des soins (valeur décimale).
     */
    public Patient(String nom, String prenom, String telephone, String email,
                   String adresse, LocalDate dateNaissance, String numeroSecu, double tauxRemboursement) {
        super(nom, prenom, telephone, email);
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.numeroSecu = numeroSecu;
        this.tauxRemboursement = tauxRemboursement;
    }

    /**
     * Constructeur simplifié d'un Patient.
     * Il initialise automatiquement un taux de remboursement par défaut de 75% (0.75).
     *
     * @param nom Le nom de famille du patient.
     * @param prenom Le prénom du patient.
     * @param telephone Le numéro de téléphone de contact.
     * @param email L'adresse e-mail de contact.
     * @param adresse L'adresse postale du domicile.
     * @param dateNaissance La date de naissance du patient.
     * @param numeroSecu Le numéro de sécurité sociale (immuable).
     */
    public Patient(String nom, String prenom, String telephone, String email,
                   String adresse, LocalDate dateNaissance, String numeroSecu) {
        this(nom, prenom, telephone, email, adresse, dateNaissance, numeroSecu, .75);
    }

    // -------------------------
    // Getters et Setters
    // -------------------------

    /**
     * Récupère l'adresse de résidence du patient.
     *
     * @return L'adresse postale actuelle.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Modifie l'adresse de résidence du patient.
     *
     * @param adresse La nouvelle adresse postale.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Récupère la date de naissance du patient.
     *
     * @return Un objet LocalDate contenant la date de naissance.
     */
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Modifie la date de naissance du patient.
     *
     * @param dateNaissance La nouvelle date de naissance.
     */
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Récupère le numéro de sécurité sociale du patient.
     * Il n'existe pas de méthode "setter" associé afin de garantir qu'il reste immuable.
     *
     * @return Le numéro de sécurité sociale.
     */
    public String getNumeroSecu() {
        return numeroSecu;
    }

    /**
     * Récupère le taux de remboursement de la sécurité sociale appliqué au patient.
     *
     * @return Le taux sous forme de nombre décimal.
     */
    public double getTauxRemboursement() {
        return tauxRemboursement;
    }

    /**
     * Modifie le taux de remboursement de la sécurité sociale du patient.
     *
     * @param tauxRemboursement Le nouveau taux (valeur décimale).
     */
    public void setTauxRemboursement(double tauxRemboursement) {
        this.tauxRemboursement = tauxRemboursement;
    }

    /**
     * Calcule et retourne l'âge actuel du patient.
     * L'âge est calculé dynamiquement par rapport à sa date de naissance et la date du jour.
     *
     * @return L'âge du patient exprimé en années.
     */
    public int getAge() {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    /**
     * Vérifie si l'objet actuel est égal à un autre objet.
     * Deux patients sont considérés comme égaux si leur identifiant général (via la classe parente) est identique
     * et s'ils possèdent exactement le même numéro de sécurité sociale.
     *
     * @param obj L'objet avec lequel effectuer la comparaison.
     * @return true si les deux patients possèdent le même ID et numéro de sécu, false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Patient autre = (Patient) obj;
        return this.numeroSecu.equals(autre.numeroSecu);
    }

    /**
     * Calcule le code de hachage (hashCode) unique de l'objet basé sur celui de la superclasse et le numéro de sécurité sociale.
     *
     * @return Le code de hachage sous forme d'un entier.
     */
    @Override
    public int hashCode() {
        return super.hashCode() * 31 + numeroSecu.hashCode();
    }

    /**
     * Renvoie une chaîne de caractères lisible contenant toutes les informations détaillées du patient.
     * Elle combine les informations de base de la personne avec sa date de naissance, son âge calculé,
     * son adresse postale, son numéro de sécurité sociale et son taux de prise en charge.
     *
     * @return Le descriptif textuel complet du patient.
     */
    @Override
    public String toString() {
        return "Patient  { " + super.toString() +
                " | Naissance: " + dateNaissance +
                " (" + getAge() + " ans)" +
                " | Adresse: " + adresse +
                " | Sécu: " + numeroSecu +
                " | Taux: " + tauxRemboursement + " }";
    }
}
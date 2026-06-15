package fr.univartois.butinfo.sae.cabinet.enumeration;

/**
 * Énumération des spécialités médicales disponibles dans le cabinet.
 * <p>
 * Chaque constante de cette énumération représente une discipline médicale et est
 * associée à un libellé textuel lisible destiné à l'affichage dans l'application.
 * </p>
 *
 * @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public enum Specialite {

    /** Spécialité en médecine générale. */
    GENERALISTE("Médecin généraliste"),

    /** Spécialité en cardiologie. */
    CARDIOLOGUE("Cardiologie"),

    /** Spécialité en dermatologie. */
    DERMATOLOGUE("Dermatologie"),

    /** Spécialité en gynécologie. */
    GYNECOLOGUE("Gynécologie"),

    /** Spécialité en neurologie. */
    NEUROLOGUE("Neurologie"),

    /** Spécialité en ophtalmologie. */
    OPHTALMOLOGUE("Ophtalmologie"),

    /** Spécialité en pédiatrie. */
    PEDIATRE("Pédiatrie"),

    /** Spécialité en psychiatrie. */
    PSYCHIATRE("Psychiatrie"),

    /** Spécialité en radiologie. */
    RADIOLOGUE("Radiologie"),

    /** Spécialité en médecine d'urgence. */
    URGENTISTE("Urgences");

    /** Le libellé lisible en français de la spécialité médicale. */
    private final String libelle;

    /**
     * Constructeur interne de l'énumération Specialite.
     *
     * @param libelle Le nom complet et affichable de la spécialité.
     */
    Specialite(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Récupère le libellé textuel de la spécialité médicale.
     *
     * @return Le nom de la spécialité sous forme de chaîne de caractères.
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Renvoie le libellé de la spécialité pour représenter textuellement la constante.
     *
     * @return Le libellé de la spécialité.
     */
    @Override
    public String toString() {
        return libelle;
    }
}
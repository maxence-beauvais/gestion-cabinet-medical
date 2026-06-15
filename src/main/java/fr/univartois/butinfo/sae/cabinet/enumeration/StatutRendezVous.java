package fr.univartois.butinfo.sae.cabinet.enumeration;

/**
 * Énumération des statuts possibles d'un rendez-vous.
 * <p>
 * Chaque constante de cette énumération représente une étape ou un état dans le cycle
 * de vie d'un rendez-vous au sein du cabinet médical, et y associe un libellé textuel.
 * </p>
 *
 * @author Groupe 2 / BARLOY Thomas, BEAUVAIS Maxence, DUBREUCQ Kévin, VANSTRACEELE Lucas
 * @version 1.0
 */
public enum StatutRendezVous {

    /** Le rendez-vous est enregistré dans l'agenda mais attend encore d'être validé ou confirmé. */
    PLANIFIE("Planifié"),

    /** Le rendez-vous a été validé et confirmé, par le secrétariat ou le patient. */
    CONFIRME("Confirmé"),

    /** Le rendez-vous a été décommandé par le patient ou annulé par le médecin. */
    ANNULE("Annulé"),

    /** La consultation a eu lieu et le rendez-vous est maintenant clôturé. */
    TERMINE("Terminé");

    /** Le libellé lisible en français du statut du rendez-vous. */
    private final String libelle;

    /**
     * Constructeur interne de l'énumération StatutRendezVous.
     *
     * @param libelle Le nom complet et affichable du statut.
     */
    StatutRendezVous(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Récupère le libellé textuel associé au statut du rendez-vous.
     *
     * @return Le libellé du statut sous forme de chaîne de caractères.
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Renvoie le libellé du statut pour représenter textuellement la constante.
     *
     * @return Le libellé du statut.
     */
    @Override
    public String toString() {
        return libelle;
    }
}
package fr.univartois.butinfo.sae.cabinet.model.modele;

import fr.univartois.butinfo.sae.cabinet.model.service.*;


public class CabinetFacade {
    private final GestionPatients gestionPatients;
    private final GestionMedecins gestionMedecins;
    private final GestionMedicaments gestionMedicaments;
    private final GestionRendezVous gestionRendezVous;
    private final GestionOrdonnances gestionOrdonnances;

    public CabinetFacade() {
        this.gestionPatients = new GestionPatients();
        this.gestionMedecins = new GestionMedecins();
        this.gestionMedicaments = new GestionMedicaments();
        this.gestionRendezVous = new GestionRendezVous();
        this.gestionOrdonnances = new GestionOrdonnances();
    }

    public GestionPatients getGestionPatients() { return gestionPatients; }
    public GestionMedecins getGestionMedecins() { return gestionMedecins; }
    public GestionMedicaments getGestionMedicaments() { return gestionMedicaments; }
    public GestionRendezVous getGestionRendezVous() { return gestionRendezVous; }
    public GestionOrdonnances getGestionOrdonnances() { return gestionOrdonnances; }
}
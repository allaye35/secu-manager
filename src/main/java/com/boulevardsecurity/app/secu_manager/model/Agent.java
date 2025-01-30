package com.boulevardsecurity.app.secu_manager.model;


import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Agent extends Utilisateur {
    private boolean disponibilite;

    @ElementCollection
    private List<String> certifications;

    private String statut;

    public boolean estDisponible() {
        // Implémentation
        return disponibilite;
    }

    public void pointerEntree(Date date) {
        // Implémentation
    }

    public void pointerSortie(Date date) {
        // Implémentation
    }

    public void enregistrerAbsence(Date date, String motif) {
        // Implémentation
    }

    public boolean verifierDisponibilite(Date dateDebut, Date dateFin) {
        // Implémentation
        return true;
    }

    // Getters et setters
}

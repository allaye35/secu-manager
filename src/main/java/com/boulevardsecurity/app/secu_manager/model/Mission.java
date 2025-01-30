package com.boulevardsecurity.app.secu_manager.model;


import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date dateDebut;
    private Date dateFin;
    private String description;

    @ManyToOne
    private Agent agentAffecte;

    public void affecterAgent(Agent agent) {
        this.agentAffecte = agent;
    }

    public void planifier(StrategiePlanification strategie) {
        // Implémentation
    }

    public boolean verifierChevauchement(Date dateDebut, Date dateFin) {
        // Implémentation
        return true;
    }

    // Getters et setters
}


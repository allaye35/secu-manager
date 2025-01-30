package com.boulevardsecurity.app.secu_manager.model;


import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date dateDebut;
    private Date dateFin;
    private String contenu;

    @ManyToOne
    private Agent agent;

    public void associerAgent(Agent agent) {
        this.agent = agent;
    }

    // Getters et setters
}

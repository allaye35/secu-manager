package com.boulevardsecurity.app.secu_manager.model;



import jakarta.persistence.*;

@Entity
public class TableauDeBord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String informations;

    public void afficher() {
        // Implémentation
    }

    // Getters et setters
}


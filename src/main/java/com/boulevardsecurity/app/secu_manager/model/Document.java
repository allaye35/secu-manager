package com.boulevardsecurity.app.secu_manager.model;



import jakarta.persistence.*;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    private String contenu;

    public void telecharger() {
        // Implémentation
    }

    // Getters et setters
}


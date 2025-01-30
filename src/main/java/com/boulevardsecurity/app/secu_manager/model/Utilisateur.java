package com.boulevardsecurity.app.secu_manager.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void connexion() {
        // Implémentation de la connexion
    }

    public void deconnexion() {
        // Implémentation de la déconnexion
    }

    // Getters et setters
}

package com.boulevardsecurity.app.secu_manager.dto;



import lombok.Data;

import java.util.List;

@Data
public class UtilisateurDTO {
    private int id; // Identifiant unique
    private String nom; // Nom de l'utilisateur
    private String email; // Adresse e-mail
    private String role; // Rôle (ADMINISTRATEUR ou AGENT)
    private boolean disponibilite; // Disponibilité (pour les agents)
    private List<String> certifications; // Certifications (pour les agents)
    private String statut; // Statut de l'utilisateur (par exemple : disponible, en mission, absent)
    private List<String> permissions; // Permissions spécifiques
}

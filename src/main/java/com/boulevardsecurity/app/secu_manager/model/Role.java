package com.boulevardsecurity.app.secu_manager.model;

public enum Role {
    ADMIN,     // Administrateur : accès complet à la plateforme
    AGENT,     // Agent : peut être affecté à des missions
    CLIENT,    // Client : demande des services
    MANAGER    // Manager : gère les affectations et planifications
}

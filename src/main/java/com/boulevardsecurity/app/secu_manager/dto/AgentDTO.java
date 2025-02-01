package com.boulevardsecurity.app.secu_manager.dto;

import com.boulevardsecurity.app.secu_manager.model.Role;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentDTO {

    private int id;
    private String nom;
    private String email;
    private boolean disponibilite;
    private String statut; // Actif, Suspendu, etc.
    private Role role; // Rôle de l'agent (ADMIN, SUPERVISEUR, AGENT)
    private List<String> permissions; // Liste des permissions de l'agent

    // Relations simplifiées avec des identifiants ou noms
    private List<String> certifications; // Stocke uniquement les noms des certifications
    private List<Integer> absencesIds; // Stocke les IDs des absences associées
    private List<Integer> missionsIds; // Stocke les IDs des missions assignées
    private List<Integer> pointagesIds; // Stocke les IDs des pointages associés
}

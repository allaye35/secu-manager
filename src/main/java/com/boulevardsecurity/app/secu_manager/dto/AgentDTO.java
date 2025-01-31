package com.boulevardsecurity.app.secu_manager.dto;

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
    private String statut;
    private List<String> certifications; // Stocke uniquement les noms des certifications
    private List<Integer> absencesIds; // Stocke les IDs des absences associées
    private List<Integer> missionsIds; // Stocke les IDs des missions assignées
    private List<Integer> pointagesIds; // Stocke les IDs des pointages associés
}

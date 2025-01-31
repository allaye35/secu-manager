package com.boulevardsecurity.app.secu_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointeDTO {
    private int id;
    private Date dateDebut;
    private String type; // Type de pointe (ex: "entrée", "sortie")
    private int agentId; // ID de l'agent associé
    private String agentNom; // Nom de l'agent (pour simplifier la vue)
}

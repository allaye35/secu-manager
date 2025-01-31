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
public class MissionDTO {
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private String description;
    private String statut;
    private int agentId; // ID de l'agent affecté
}

package com.boulevardsecurity.app.secu_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // Ajout du Builder pour activer son utilisation
public class AbsenceDTO {
    private int id;
    private Date date;
    private String motif;
    private int agentId; // ID de l'agent associé
}

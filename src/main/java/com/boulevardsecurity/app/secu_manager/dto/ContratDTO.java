package com.boulevardsecurity.app.secu_manager.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratDTO {
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private String contenu;
    private int agentId;
    private String agentNom;
    private String agentEmail;
    private String agentStatut;
}

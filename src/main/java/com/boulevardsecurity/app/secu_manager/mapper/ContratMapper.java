package com.boulevardsecurity.app.secu_manager.mapper;

import com.boulevardsecurity.app.secu_manager.dto.ContratDTO;
import com.boulevardsecurity.app.secu_manager.model.Agent;
import com.boulevardsecurity.app.secu_manager.model.Contrat;
import org.springframework.stereotype.Component;

@Component
public class ContratMapper {
    public ContratDTO toDto(Contrat contrat) {
        ContratDTO dto = new ContratDTO();
        dto.setId(contrat.getId());
        dto.setDateDebut(contrat.getDateDebut());
        dto.setDateFin(contrat.getDateFin());
        dto.setContenu(contrat.getContenu());
        if (contrat.getAgent() != null) {
            dto.setAgentId(contrat.getAgent().getId());
            dto.setAgentNom(contrat.getAgent().getNom());
            dto.setAgentEmail(contrat.getAgent().getEmail());
            dto.setAgentStatut(contrat.getAgent().getStatut());
        }
        return dto;
    }

    public Contrat toEntity(ContratDTO dto, Agent agent) {
        return Contrat.builder()
                .id(dto.getId())
                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .contenu(dto.getContenu())
                .agent(agent)
                .build();
    }
}
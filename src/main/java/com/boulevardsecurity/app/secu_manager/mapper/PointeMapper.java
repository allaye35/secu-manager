package com.boulevardsecurity.app.secu_manager.mapper;

import com.boulevardsecurity.app.secu_manager.dto.PointeDTO;
import com.boulevardsecurity.app.secu_manager.model.Pointe;
import org.springframework.stereotype.Component;

@Component
public class PointeMapper {

    // Convertir une entité Pointe en DTO
    public PointeDTO toDto(Pointe pointe) {
        return PointeDTO.builder()
                .id(pointe.getId())
                .dateDebut(pointe.getDateDebut())
                .type(pointe.getType())
                .agentId(pointe.getAgent() != null ? pointe.getAgent().getId() : null)
                .agentNom(pointe.getAgent() != null ? pointe.getAgent().getNom() : null)
                .build();
    }

    // Convertir un DTO Pointe en entité
    public Pointe toEntity(PointeDTO dto) {
        Pointe pointe = Pointe.builder()
                .id(dto.getId())
                .dateDebut(dto.getDateDebut())
                .type(dto.getType())
                .build();
        // L'association avec l'agent sera faite ultérieurement
        return pointe;
    }
}

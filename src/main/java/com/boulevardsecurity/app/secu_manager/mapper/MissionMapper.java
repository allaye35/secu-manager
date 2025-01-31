package com.boulevardsecurity.app.secu_manager.mapper;

import com.boulevardsecurity.app.secu_manager.dto.MissionDTO;
import com.boulevardsecurity.app.secu_manager.model.Mission;
import org.springframework.stereotype.Component;

@Component
public class MissionMapper {

    public MissionDTO toDto(Mission mission) {
        return MissionDTO.builder()
                .id(mission.getId())
                .dateDebut(mission.getDateDebut())
                .dateFin(mission.getDateFin())
                .description(mission.getDescription())
                .statut(mission.getStatut())
                .agentId(mission.getAgentAffecte() != null ? mission.getAgentAffecte().getId() : 0)
                .build();
    }

    public Mission toEntity(MissionDTO dto) {
        return Mission.builder()
                .id(dto.getId())
                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .description(dto.getDescription())
                .statut(dto.getStatut())
                .build();
    }
}

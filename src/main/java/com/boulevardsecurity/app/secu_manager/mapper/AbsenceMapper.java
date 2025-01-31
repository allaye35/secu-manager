package com.boulevardsecurity.app.secu_manager.mapper;

import com.boulevardsecurity.app.secu_manager.dto.AbsenceDTO;
import com.boulevardsecurity.app.secu_manager.model.Absence;
import org.springframework.stereotype.Component;

@Component
public class AbsenceMapper {

    // Convertir une entité en DTO
    public AbsenceDTO toDto(Absence absence) {
        return AbsenceDTO.builder()
                .id(absence.getId())
                .date(absence.getDate())
                .motif(absence.getMotif())
                .agentId(absence.getAgent() != null ? absence.getAgent().getId() : 0)
                .build();
    }

    // Convertir un DTO en entité
    public Absence toEntity(AbsenceDTO absenceDTO) {
        return Absence.builder()
                .id(absenceDTO.getId())
                .date(absenceDTO.getDate())
                .motif(absenceDTO.getMotif())
                .build();
    }
}

package com.boulevardsecurity.app.secu_manager.mapper;

import com.boulevardsecurity.app.secu_manager.dto.AgentDTO;
import com.boulevardsecurity.app.secu_manager.model.Agent;
import com.boulevardsecurity.app.secu_manager.model.Certification;
import com.boulevardsecurity.app.secu_manager.model.Absence;
import com.boulevardsecurity.app.secu_manager.model.Mission;
import com.boulevardsecurity.app.secu_manager.model.Pointe;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AgentMapper {

    /**
     * Convertit une entité `Agent` en `AgentDTO`
     */
    public AgentDTO toDto(Agent agent) {
        return AgentDTO.builder()
                .id(agent.getId())
                .nom(agent.getNom())
                .email(agent.getEmail())
                .disponibilite(agent.isDisponibilite())
                .statut(agent.getStatut())
                .certifications(agent.getCertifications().stream().map(Certification::getNom).collect(Collectors.toList()))
                .absencesIds(agent.getAbsences().stream().map(Absence::getId).collect(Collectors.toList()))
                .missionsIds(agent.getMissions().stream().map(Mission::getId).collect(Collectors.toList()))
                .pointagesIds(agent.getPointages().stream().map(Pointe::getId).collect(Collectors.toList()))
                .build();
    }

    /**
     * Convertit un `AgentDTO` en entité `Agent` (sans gestion des relations)
     */
    public Agent toEntity(AgentDTO dto) {
        return Agent.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .email(dto.getEmail())
                .disponibilite(dto.isDisponibilite())
                .statut(dto.getStatut())
                .build();
    }
}

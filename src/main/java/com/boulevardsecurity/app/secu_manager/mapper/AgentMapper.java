package com.boulevardsecurity.app.secu_manager.mapper;

import com.boulevardsecurity.app.secu_manager.dto.AgentDTO;
import com.boulevardsecurity.app.secu_manager.model.Agent;
import com.boulevardsecurity.app.secu_manager.model.Certification;
import com.boulevardsecurity.app.secu_manager.model.Absence;
import com.boulevardsecurity.app.secu_manager.model.Mission;
import com.boulevardsecurity.app.secu_manager.model.Pointe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AgentMapper {

    /**
     * Convertit un Agent en AgentDTO.
     *
     * @param agent l'entité Agent
     * @return AgentDTO
     */
    public AgentDTO toDTO(Agent agent) {
        if (agent == null) {
            return null;
        }

        return AgentDTO.builder()
                .id(agent.getId())
                .nom(agent.getNom())
                .email(agent.getEmail())
                .disponibilite(agent.isDisponibilite())
                .statut(agent.getStatut())
                .role(agent.getRole())
                .permissions(agent.getPermissions())
                .certifications(mapCertifications(agent.getCertifications())) // Utilise mapCertifications
                .absencesIds(mapAbsences(agent.getAbsences())) // Utilise mapAbsences
                .missionsIds(mapMissions(agent.getMissions())) // Utilise mapMissions
                .pointagesIds(mapPointages(agent.getPointages())) // Utilise mapPointages
                .build();
    }

    /**
     * Convertit un AgentDTO en Agent.
     *
     * @param agentDTO le DTO Agent
     * @return Agent
     */
    public Agent toEntity(AgentDTO agentDTO) {
        if (agentDTO == null) {
            return null;
        }

        return Agent.builder()
                .id(agentDTO.getId())
                .nom(agentDTO.getNom())
                .email(agentDTO.getEmail())
                .disponibilite(agentDTO.isDisponibilite())
                .statut(agentDTO.getStatut())
                .role(agentDTO.getRole())
                .permissions(agentDTO.getPermissions())
                .build();
    }

    /**
     * Mappe les certifications en une liste de noms.
     *
     * @param certifications liste des certifications
     * @return liste des noms des certifications
     */
    private List<String> mapCertifications(List<Certification> certifications) {
        if (certifications == null || certifications.isEmpty()) {
            return List.of();
        }
        return certifications.stream()
                .map(Certification::getNom) // Suppose que Certification a un champ "nom"
                .collect(Collectors.toList());
    }

    /**
     * Mappe les absences en une liste d'identifiants.
     *
     * @param absences liste des absences
     * @return liste des IDs des absences
     */
    private List<Integer> mapAbsences(List<Absence> absences) {
        if (absences == null || absences.isEmpty()) {
            return List.of();
        }
        return absences.stream()
                .map(Absence::getId) // Suppose que Absence a un champ "id"
                .collect(Collectors.toList());
    }

    /**
     * Mappe les missions en une liste d'identifiants.
     *
     * @param missions liste des missions
     * @return liste des IDs des missions
     */
    private List<Integer> mapMissions(List<Mission> missions) {
        if (missions == null || missions.isEmpty()) {
            return List.of();
        }
        return missions.stream()
                .map(Mission::getId) // Suppose que Mission a un champ "id"
                .collect(Collectors.toList());
    }

    /**
     * Mappe les pointages en une liste d'identifiants.
     *
     * @param pointages liste des pointages
     * @return liste des IDs des pointages
     */
    private List<Integer> mapPointages(List<Pointe> pointages) {
        if (pointages == null || pointages.isEmpty()) {
            return List.of();
        }
        return pointages.stream()
                .map(Pointe::getId) // Suppose que Pointe a un champ "id"
                .collect(Collectors.toList());
    }
}

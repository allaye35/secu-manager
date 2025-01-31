package com.boulevardsecurity.app.secu_manager.mapper;

import com.boulevardsecurity.app.secu_manager.dto.PlanificationAutomatiqueDTO;
import com.boulevardsecurity.app.secu_manager.model.PlanificationAutomatique;
import com.boulevardsecurity.app.secu_manager.model.Agent;
import com.boulevardsecurity.app.secu_manager.model.Mission;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlanificationAutomatiqueMapper {
    public PlanificationAutomatiqueDTO toDto(PlanificationAutomatique planification) {
        PlanificationAutomatiqueDTO dto = new PlanificationAutomatiqueDTO();
        dto.setId(planification.getId());
        dto.setMissionId(planification.getMission().getId());
        dto.setAgentIds(planification.getAgents().stream().map(Agent::getId).collect(Collectors.toList()));
        return dto;
    }

    public PlanificationAutomatique toEntity(PlanificationAutomatiqueDTO dto, List<Agent> agents, Mission mission) {
        return PlanificationAutomatique.builder()
                .id(dto.getId())
                .agents(agents)
                .mission(mission)
                .build();
    }
}

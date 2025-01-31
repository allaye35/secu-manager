package com.boulevardsecurity.app.secu_manager.mapper;
import com.boulevardsecurity.app.secu_manager.dto.PlanificationManuelleDTO;
import com.boulevardsecurity.app.secu_manager.model.PlanificationManuelle;
import com.boulevardsecurity.app.secu_manager.model.Agent;
import com.boulevardsecurity.app.secu_manager.model.Mission;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlanificationManuelleMapper {

    public PlanificationManuelleDTO toDto(PlanificationManuelle planification) {
        PlanificationManuelleDTO dto = new PlanificationManuelleDTO();
        dto.setId(planification.getId());
        dto.setMissionId(planification.getMission().getId());
        dto.setAgentIds(planification.getAgents().stream().map(Agent::getId).collect(Collectors.toList()));
        return dto;
    }

    public PlanificationManuelle toEntity(PlanificationManuelleDTO dto, Mission mission, List<Agent> agents) {
        return PlanificationManuelle.builder()
                .id(dto.getId())
                .mission(mission)
                .agents(agents)
                .build();
    }
}
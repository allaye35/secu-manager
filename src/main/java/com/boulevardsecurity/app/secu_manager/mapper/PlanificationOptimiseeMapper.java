package com.boulevardsecurity.app.secu_manager.mapper;

import com.boulevardsecurity.app.secu_manager.dto.PlanificationOptimiseeDTO;
import com.boulevardsecurity.app.secu_manager.model.PlanificationOptimisee;
import com.boulevardsecurity.app.secu_manager.model.Agent;
import com.boulevardsecurity.app.secu_manager.model.Mission;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlanificationOptimiseeMapper {

    public PlanificationOptimiseeDTO toDto(PlanificationOptimisee planification) {
        PlanificationOptimiseeDTO dto = new PlanificationOptimiseeDTO();
        dto.setId(planification.getId());
        dto.setMissionId(planification.getMission().getId());
        dto.setAgentIds(planification.getAgents().stream().map(Agent::getId).collect(Collectors.toList()));
        return dto;
    }

    public PlanificationOptimisee toEntity(PlanificationOptimiseeDTO dto, Mission mission, List<Agent> agents) {
        return PlanificationOptimisee.builder()
                .id(dto.getId())
                .mission(mission)
                .agents(agents)
                .build();
    }
}

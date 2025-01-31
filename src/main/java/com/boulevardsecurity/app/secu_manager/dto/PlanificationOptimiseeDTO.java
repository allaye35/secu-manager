package com.boulevardsecurity.app.secu_manager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanificationOptimiseeDTO {
    private int id;
    private int missionId;
    private List<Integer> agentIds;
}

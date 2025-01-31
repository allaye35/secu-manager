package com.boulevardsecurity.app.secu_manager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanificationAutomatiqueDTO {
    private int id;
    private List<Integer> agentIds;
    private int missionId;
}
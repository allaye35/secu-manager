package com.boulevardsecurity.app.secu_manager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanificationManuelleDTO {
    private int id;
    private int missionId;
    private List<Integer> agentIds;
}

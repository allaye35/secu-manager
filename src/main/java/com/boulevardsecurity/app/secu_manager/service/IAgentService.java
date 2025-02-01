package com.boulevardsecurity.app.secu_manager.service;

import com.boulevardsecurity.app.secu_manager.dto.AgentDTO;

import java.util.Date;
import java.util.List;

public interface IAgentService {

    List<AgentDTO> getAllAgents();

    AgentDTO getAgentById(int id);

    AgentDTO saveAgent(AgentDTO agentDTO);

    void deleteAgent(int id);

    List<AgentDTO> getAvailableAgents();

    List<AgentDTO> getAgentsByStatut(String statut);

    List<AgentDTO> getAgentsByCertification(String certification);

    List<AgentDTO> getAgentsByDateEmbaucheAfter(Date date);
}

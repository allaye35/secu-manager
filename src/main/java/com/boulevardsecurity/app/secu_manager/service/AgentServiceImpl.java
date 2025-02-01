package com.boulevardsecurity.app.secu_manager.service;
import com.boulevardsecurity.app.secu_manager.dto.AgentDTO;
import com.boulevardsecurity.app.secu_manager.mapper.AgentMapper;
import com.boulevardsecurity.app.secu_manager.model.Agent;
import com.boulevardsecurity.app.secu_manager.repository.AgentRepository;
import com.boulevardsecurity.app.secu_manager.service.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements IAgentService {

    private final AgentRepository agentRepository;
    private final AgentMapper agentMapper;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, AgentMapper agentMapper) {
        this.agentRepository = agentRepository;
        this.agentMapper = agentMapper;
    }

    /**
     * Récupère tous les agents sous forme de DTO.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AgentDTO> getAllAgents() {
        return agentRepository.findAll().stream()
                .map(agentMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Récupère un agent par son ID.
     */
    @Override
    @Transactional(readOnly = true)
    public AgentDTO getAgentById(int id) {
        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent avec ID " + id + " non trouvé"));
        return agentMapper.toDTO(agent);
    }

    /**
     * Sauvegarde un agent (création ou mise à jour).
     */
    @Override
    @Transactional
    public AgentDTO saveAgent(AgentDTO agentDTO) {
        Agent agent = agentMapper.toEntity(agentDTO);
        Agent savedAgent = agentRepository.save(agent);
        return agentMapper.toDTO(savedAgent);
    }

    /**
     * Supprime un agent par son ID.
     */
    @Override
    @Transactional
    public void deleteAgent(int id) {
        if (!agentRepository.existsById(id)) {
            throw new RuntimeException("Agent avec ID " + id + " non trouvé");
        }
        agentRepository.deleteById(id);
    }

    /**
     * Récupère les agents disponibles.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AgentDTO> getAvailableAgents() {
        return agentRepository.findByDisponibiliteTrue().stream()
                .map(agentMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Recherche des agents par statut.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AgentDTO> getAgentsByStatut(String statut) {
        return agentRepository.findByStatut(statut).stream()
                .map(agentMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Recherche d'agents ayant une certification spécifique.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AgentDTO> getAgentsByCertification(String certification) {
        return agentRepository.findByCertificationsContaining(certification).stream()
                .map(agentMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Recherche des agents embauchés après une date spécifique.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AgentDTO> getAgentsByDateEmbaucheAfter(Date date) {
        return agentRepository.findByDateEmbaucheAfter(date).stream()
                .map(agentMapper::toDTO)
                .collect(Collectors.toList());
    }
}

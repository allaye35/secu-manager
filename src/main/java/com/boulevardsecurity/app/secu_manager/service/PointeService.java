package com.boulevardsecurity.app.secu_manager.service;

import com.boulevardsecurity.app.secu_manager.dto.PointeDTO;
import com.boulevardsecurity.app.secu_manager.mapper.PointeMapper;
import com.boulevardsecurity.app.secu_manager.model.Agent;
import com.boulevardsecurity.app.secu_manager.model.Pointe;
import com.boulevardsecurity.app.secu_manager.repository.AgentRepository;
import com.boulevardsecurity.app.secu_manager.repository.PointeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointeService {

    private final PointeRepository pointeRepository;
    private final AgentRepository agentRepository;
    private final PointeMapper pointeMapper;

    public PointeService(PointeRepository pointeRepository, AgentRepository agentRepository, PointeMapper pointeMapper) {
        this.pointeRepository = pointeRepository;
        this.agentRepository = agentRepository;
        this.pointeMapper = pointeMapper;
    }

    // 1. Récupérer toutes les pointes
    public List<PointeDTO> getAllPointes() {
        List<Pointe> pointes = pointeRepository.findAll();
        return pointes.stream()
                .map(pointeMapper::toDto)
                .collect(Collectors.toList());
    }

    // 2. Récupérer une pointe par ID
    public PointeDTO getPointeById(int id) {
        Pointe pointe = pointeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pointe introuvable avec l'ID : " + id));
        return pointeMapper.toDto(pointe);
    }

    // 3. Ajouter ou mettre à jour une pointe
    public PointeDTO saveOrUpdatePointe(PointeDTO pointeDTO) {
        Pointe pointe = pointeMapper.toEntity(pointeDTO);

        // Associer l'agent si un agentId est présent
        if (pointeDTO.getAgentId() != 0) {
            Agent agent = agentRepository.findById(pointeDTO.getAgentId())
                    .orElseThrow(() -> new IllegalArgumentException("Agent introuvable avec l'ID : " + pointeDTO.getAgentId()));
            pointe.setAgent(agent);
        }

        Pointe savedPointe = pointeRepository.save(pointe);
        return pointeMapper.toDto(savedPointe);
    }

    // 4. Supprimer une pointe
    public void deletePointe(int id) {
        if (!pointeRepository.existsById(id)) {
            throw new IllegalArgumentException("Pointe introuvable avec l'ID : " + id);
        }
        pointeRepository.deleteById(id);
    }

    // 5. Vérifier la présence d'un agent à une pointe
    public boolean verifierPresence(int pointeId) {
        Pointe pointe = pointeRepository.findById(pointeId)
                .orElseThrow(() -> new IllegalArgumentException("Pointe introuvable avec l'ID : " + pointeId));

        // Vérifier si un agent est associé
        return pointe.getAgent() != null;
    }

    // 6. Associer un agent à une pointe
    public PointeDTO associerAgent(int pointeId, int agentId) {
        Pointe pointe = pointeRepository.findById(pointeId)
                .orElseThrow(() -> new IllegalArgumentException("Pointe introuvable avec l'ID : " + pointeId));
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new IllegalArgumentException("Agent introuvable avec l'ID : " + agentId));

        pointe.setAgent(agent);
        Pointe updatedPointe = pointeRepository.save(pointe);

        return pointeMapper.toDto(updatedPointe);
    }
}

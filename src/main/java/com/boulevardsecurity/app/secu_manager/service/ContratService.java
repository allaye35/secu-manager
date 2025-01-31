package com.boulevardsecurity.app.secu_manager.service;
import com.boulevardsecurity.app.secu_manager.dto.ContratDTO;
import com.boulevardsecurity.app.secu_manager.mapper.ContratMapper;
import com.boulevardsecurity.app.secu_manager.model.Agent;
import com.boulevardsecurity.app.secu_manager.model.Contrat;
import com.boulevardsecurity.app.secu_manager.repository.AgentRepository;
import com.boulevardsecurity.app.secu_manager.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContratService {

    private final ContratRepository contratRepository;
    private final AgentRepository agentRepository;
    private final ContratMapper contratMapper;

    @Autowired
    public ContratService(ContratRepository contratRepository, AgentRepository agentRepository, ContratMapper contratMapper) {
        this.contratRepository = contratRepository;
        this.agentRepository = agentRepository;
        this.contratMapper = contratMapper;
    }

    /**
     * Récupérer tous les contrats.
     */
    @Transactional(readOnly = true)
    public List<ContratDTO> getAllContrats() {
        return contratRepository.findAll().stream()
                .map(contratMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer un contrat par son ID.
     */
    @Transactional(readOnly = true)
    public ContratDTO getContratById(int id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat avec ID " + id + " non trouvé"));
        return contratMapper.toDto(contrat);
    }

    /**
     * Créer ou mettre à jour un contrat.
     */
    @Transactional
    public ContratDTO saveContrat(ContratDTO dto) {
        Agent agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new RuntimeException("Agent avec ID " + dto.getAgentId() + " non trouvé"));
        Contrat contrat = contratMapper.toEntity(dto, agent);
        Contrat savedContrat = contratRepository.save(contrat);
        return contratMapper.toDto(savedContrat);
    }

    /**
     * Supprimer un contrat par son ID.
     */
    @Transactional
    public void deleteContrat(int id) {
        if (!contratRepository.existsById(id)) {
            throw new RuntimeException("Contrat avec ID " + id + " non trouvé");
        }
        contratRepository.deleteById(id);
    }

    /**
     * Récupérer les contrats d'un agent donné.
     */
    @Transactional(readOnly = true)
    public List<ContratDTO> getContratsByAgentId(int agentId) {
        return contratRepository.findByAgentId(agentId).stream()
                .map(contratMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les contrats actifs (en cours).
     */
    @Transactional(readOnly = true)
    public List<ContratDTO> getActiveContrats() {
        Date today = new Date();
        return contratRepository.findByDateDebutBeforeAndDateFinAfter(today, today).stream()
                .map(contratMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les contrats terminés.
     */
    @Transactional(readOnly = true)
    public List<ContratDTO> getFinishedContrats() {
        Date today = new Date();
        return contratRepository.findByDateFinBefore(today).stream()
                .map(contratMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les contrats signés après une date donnée.
     */
    @Transactional(readOnly = true)
    public List<ContratDTO> getContratsAfterDate(Date date) {
        return contratRepository.findByDateDebutAfter(date).stream()
                .map(contratMapper::toDto)
                .collect(Collectors.toList());
    }
}


package com.boulevardsecurity.app.secu_manager.service;

import com.boulevardsecurity.app.secu_manager.dto.PlanificationAutomatiqueDTO;
import com.boulevardsecurity.app.secu_manager.mapper.PlanificationAutomatiqueMapper;
import com.boulevardsecurity.app.secu_manager.model.Agent;
import com.boulevardsecurity.app.secu_manager.model.Mission;
import com.boulevardsecurity.app.secu_manager.model.PlanificationAutomatique;
import com.boulevardsecurity.app.secu_manager.repository.AgentRepository;
import com.boulevardsecurity.app.secu_manager.repository.MissionRepository;
import com.boulevardsecurity.app.secu_manager.repository.PlanificationAutomatiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanificationAutomatiqueService {

    private final PlanificationAutomatiqueRepository planificationRepository;
    private final AgentRepository agentRepository;
    private final MissionRepository missionRepository;
    private final PlanificationAutomatiqueMapper planificationMapper;

    @Autowired
    public PlanificationAutomatiqueService(PlanificationAutomatiqueRepository planificationRepository,
                                           AgentRepository agentRepository,
                                           MissionRepository missionRepository,
                                           PlanificationAutomatiqueMapper planificationMapper) {
        this.planificationRepository = planificationRepository;
        this.agentRepository = agentRepository;
        this.missionRepository = missionRepository;
        this.planificationMapper = planificationMapper;
    }

    /**
     * Récupérer toutes les planifications automatiques.
     */
    @Transactional(readOnly = true)
    public List<PlanificationAutomatiqueDTO> getAllPlanifications() {
        return planificationRepository.findAll().stream()
                .map(planificationMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer une planification par son ID.
     */
    @Transactional(readOnly = true)
    public PlanificationAutomatiqueDTO getPlanificationById(int id) {
        PlanificationAutomatique planification = planificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planification avec ID " + id + " non trouvée"));
        return planificationMapper.toDto(planification);
    }

    /**
     * Créer une planification automatique pour une mission et ses agents.
     */
    @Transactional
    public PlanificationAutomatiqueDTO savePlanification(PlanificationAutomatiqueDTO dto) {
        Mission mission = missionRepository.findById(dto.getMissionId())
                .orElseThrow(() -> new RuntimeException("Mission avec ID " + dto.getMissionId() + " non trouvée"));
        List<Agent> agents = agentRepository.findAllById(dto.getAgentIds());

        PlanificationAutomatique planification = planificationMapper.toEntity(dto, agents, mission);
        PlanificationAutomatique savedPlanification = planificationRepository.save(planification);
        return planificationMapper.toDto(savedPlanification);
    }

    /**
     * Supprimer une planification par son ID.
     */
    @Transactional
    public void deletePlanification(int id) {
        if (!planificationRepository.existsById(id)) {
            throw new RuntimeException("Planification avec ID " + id + " non trouvée");
        }
        planificationRepository.deleteById(id);
    }

    /**
     * Récupérer les planifications associées à une mission spécifique.
     */
    @Transactional(readOnly = true)
    public List<PlanificationAutomatiqueDTO> getPlanificationsByMissionId(int missionId) {
        return planificationRepository.findByMissionId(missionId).stream()
                .map(planificationMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les planifications impliquant un agent spécifique.
     */
    @Transactional(readOnly = true)
    public List<PlanificationAutomatiqueDTO> getPlanificationsByAgentId(int agentId) {
        return planificationRepository.findByAgents_Id(agentId).stream()
                .map(planificationMapper::toDto)
                .collect(Collectors.toList());
    }
}

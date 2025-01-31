package com.boulevardsecurity.app.secu_manager.service;

import com.boulevardsecurity.app.secu_manager.dto.PlanificationManuelleDTO;
import com.boulevardsecurity.app.secu_manager.mapper.PlanificationManuelleMapper;
import com.boulevardsecurity.app.secu_manager.model.Agent;
import com.boulevardsecurity.app.secu_manager.model.Mission;
import com.boulevardsecurity.app.secu_manager.model.PlanificationManuelle;
import com.boulevardsecurity.app.secu_manager.repository.AgentRepository;
import com.boulevardsecurity.app.secu_manager.repository.MissionRepository;
import com.boulevardsecurity.app.secu_manager.repository.PlanificationManuelleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanificationManuelleService {

    private final PlanificationManuelleRepository planificationRepository;
    private final MissionRepository missionRepository;
    private final AgentRepository agentRepository;
    private final PlanificationManuelleMapper planificationMapper;

    @Autowired
    public PlanificationManuelleService(PlanificationManuelleRepository planificationRepository,
                                        MissionRepository missionRepository,
                                        AgentRepository agentRepository,
                                        PlanificationManuelleMapper planificationMapper) {
        this.planificationRepository = planificationRepository;
        this.missionRepository = missionRepository;
        this.agentRepository = agentRepository;
        this.planificationMapper = planificationMapper;
    }

    /**
     * Récupérer toutes les planifications manuelles.
     */
    @Transactional(readOnly = true)
    public List<PlanificationManuelleDTO> getAllPlanifications() {
        return planificationRepository.findAll().stream()
                .map(planificationMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer une planification manuelle par son ID.
     */
    @Transactional(readOnly = true)
    public PlanificationManuelleDTO getPlanificationById(int id) {
        PlanificationManuelle planification = planificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planification avec ID " + id + " non trouvée"));
        return planificationMapper.toDto(planification);
    }

    /**
     * Créer ou mettre à jour une planification manuelle.
     */
    @Transactional
    public PlanificationManuelleDTO savePlanification(PlanificationManuelleDTO dto) {
        Mission mission = missionRepository.findById(dto.getMissionId())
                .orElseThrow(() -> new RuntimeException("Mission avec ID " + dto.getMissionId() + " non trouvée"));

        List<Agent> agents = agentRepository.findAllById(dto.getAgentIds());
        if (agents.isEmpty()) {
            throw new RuntimeException("Aucun agent valide trouvé pour les IDs fournis");
        }

        PlanificationManuelle planification = planificationMapper.toEntity(dto, mission, agents);
        PlanificationManuelle savedPlanification = planificationRepository.save(planification);
        return planificationMapper.toDto(savedPlanification);
    }

    /**
     * Supprimer une planification manuelle par son ID.
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
    public List<PlanificationManuelleDTO> getPlanificationsByMissionId(int missionId) {
        return planificationRepository.findByMissionId(missionId).stream()
                .map(planificationMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les planifications impliquant un agent spécifique.
     */
    @Transactional(readOnly = true)
    public List<PlanificationManuelleDTO> getPlanificationsByAgentId(int agentId) {
        return planificationRepository.findByAgents_Id(agentId).stream()
                .map(planificationMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Supprimer toutes les planifications associées à une mission spécifique.
     */
    @Transactional
    public void deletePlanificationsByMissionId(int missionId) {
        planificationRepository.deleteByMissionId(missionId);
    }

    /**
     * Supprimer toutes les planifications associées à un agent spécifique.
     */
    @Transactional
    public void deletePlanificationsByAgentId(int agentId) {
        planificationRepository.deleteByAgents_Id(agentId);
    }
}

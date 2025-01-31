package com.boulevardsecurity.app.secu_manager.service;

import com.boulevardsecurity.app.secu_manager.dto.MissionDTO;
import com.boulevardsecurity.app.secu_manager.mapper.MissionMapper;
import com.boulevardsecurity.app.secu_manager.model.Agent;
import com.boulevardsecurity.app.secu_manager.model.Mission;
import com.boulevardsecurity.app.secu_manager.repository.AgentRepository;
import com.boulevardsecurity.app.secu_manager.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final AgentRepository agentRepository;
    private final MissionMapper missionMapper;

    @Autowired
    public MissionService(MissionRepository missionRepository, AgentRepository agentRepository, MissionMapper missionMapper) {
        this.missionRepository = missionRepository;
        this.agentRepository = agentRepository;
        this.missionMapper = missionMapper;
    }

    /**
     * Récupérer toutes les missions.
     */
    @Transactional(readOnly = true)
    public List<MissionDTO> getAllMissions() {
        return missionRepository.findAll().stream()
                .map(missionMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer une mission par son ID.
     */
    @Transactional(readOnly = true)
    public MissionDTO getMissionById(int id) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mission avec ID " + id + " non trouvée"));
        return missionMapper.toDto(mission);
    }

    /**
     * Créer ou mettre à jour une mission.
     */
    @Transactional
    public MissionDTO saveOrUpdateMission(MissionDTO dto) {
        Mission mission = missionMapper.toEntity(dto);

        // Vérifier si un agent est associé
        if (dto.getAgentId() != 0) {
            Agent agent = agentRepository.findById(dto.getAgentId())
                    .orElseThrow(() -> new RuntimeException("Agent avec ID " + dto.getAgentId() + " non trouvé"));
            mission.setAgentAffecte(agent);
        }

        Mission savedMission = missionRepository.save(mission);
        return missionMapper.toDto(savedMission);
    }

    /**
     * Supprimer une mission par son ID.
     */
    @Transactional
    public void deleteMission(int id) {
        if (!missionRepository.existsById(id)) {
            throw new RuntimeException("Mission avec ID " + id + " non trouvée");
        }
        missionRepository.deleteById(id);
    }

    /**
     * Récupérer les missions d'un agent donné.
     */
    @Transactional(readOnly = true)
    public List<MissionDTO> getMissionsByAgentId(int agentId) {
        return missionRepository.findByAgentAffecteId(agentId).stream()
                .map(missionMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les missions en cours.
     */
    @Transactional(readOnly = true)
    public List<MissionDTO> getActiveMissions() {
        Date today = new Date();
        return missionRepository.findByDateDebutBeforeAndDateFinAfter(today, today).stream()
                .map(missionMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les missions terminées.
     */
    @Transactional(readOnly = true)
    public List<MissionDTO> getFinishedMissions() {
        Date today = new Date();
        return missionRepository.findByDateFinBefore(today).stream()
                .map(missionMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les missions planifiées après une date donnée.
     */
    @Transactional(readOnly = true)
    public List<MissionDTO> getMissionsAfterDate(Date date) {
        return missionRepository.findByDateDebutAfter(date).stream()
                .map(missionMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Vérifier si une mission chevauche une autre mission existante.
     */
    @Transactional(readOnly = true)
    public boolean checkMissionOverlap(Date dateDebut, Date dateFin) {
        return !missionRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(dateFin, dateDebut).isEmpty();
    }
}

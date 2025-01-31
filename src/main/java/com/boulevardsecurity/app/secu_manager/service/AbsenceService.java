package com.boulevardsecurity.app.secu_manager.service;

import com.boulevardsecurity.app.secu_manager.dto.AbsenceDTO;
import com.boulevardsecurity.app.secu_manager.mapper.AbsenceMapper;
import com.boulevardsecurity.app.secu_manager.model.Absence;
import com.boulevardsecurity.app.secu_manager.model.Agent;
import com.boulevardsecurity.app.secu_manager.repository.AbsenceRepository;
import com.boulevardsecurity.app.secu_manager.repository.AgentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbsenceService {

    private final AbsenceRepository absenceRepository;
    private final AgentRepository agentRepository;
    private final AbsenceMapper absenceMapper;

    public AbsenceService(AbsenceRepository absenceRepository, AgentRepository agentRepository, AbsenceMapper absenceMapper) {
        this.absenceRepository = absenceRepository;
        this.agentRepository = agentRepository;
        this.absenceMapper = absenceMapper;
    }

    // 1. Récupérer toutes les absences
    public List<AbsenceDTO> getAllAbsences() {
        List<Absence> absences = absenceRepository.findAll();
        return absences.stream()
                .map(absenceMapper::toDto)
                .collect(Collectors.toList());
    }

    // 2. Ajouter ou mettre à jour une absence
    public AbsenceDTO saveOrUpdateAbsence(AbsenceDTO absenceDTO) {
        Absence absence = absenceMapper.toEntity(absenceDTO);

        // Associer un agent si l'agentId est fourni
        if (absenceDTO.getAgentId() != 0) {
            Agent agent = agentRepository.findById(absenceDTO.getAgentId())
                    .orElseThrow(() -> new IllegalArgumentException("Agent introuvable avec l'ID : " + absenceDTO.getAgentId()));
            absence.setAgent(agent);
        }

        Absence savedAbsence = absenceRepository.save(absence);
        return absenceMapper.toDto(savedAbsence);
    }

    // 3. Supprimer une absence
    public void deleteAbsence(int id) {
        if (!absenceRepository.existsById(id)) {
            throw new IllegalArgumentException("Absence introuvable avec l'ID : " + id);
        }
        absenceRepository.deleteById(id);
    }
}

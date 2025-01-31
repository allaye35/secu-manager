package com.boulevardsecurity.app.secu_manager.controller;

import com.boulevardsecurity.app.secu_manager.dto.MissionDTO;
import com.boulevardsecurity.app.secu_manager.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/missions")
@CrossOrigin(origins = "*") // Autorise l'accès depuis le frontend Angular
public class MissionController {

    private final MissionService missionService;

    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    /**
     * Récupérer toutes les missions
     */
    @GetMapping
    public ResponseEntity<List<MissionDTO>> getAllMissions() {
        return ResponseEntity.ok(missionService.getAllMissions());
    }

    /**
     * Récupérer une mission par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MissionDTO> getMissionById(@PathVariable int id) {
        return ResponseEntity.ok(missionService.getMissionById(id));
    }

    /**
     * Ajouter ou mettre à jour une mission
     */
    @PostMapping
    public ResponseEntity<MissionDTO> saveMission(@RequestBody MissionDTO missionDTO) {
        return ResponseEntity.ok(missionService.saveOrUpdateMission(missionDTO));
    }

    /**
     * Supprimer une mission par son ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMission(@PathVariable int id) {
        missionService.deleteMission(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupérer les missions d'un agent donné
     */
    @GetMapping("/agent/{agentId}")
    public ResponseEntity<List<MissionDTO>> getMissionsByAgentId(@PathVariable int agentId) {
        return ResponseEntity.ok(missionService.getMissionsByAgentId(agentId));
    }

    /**
     * Récupérer les missions en cours
     */
    @GetMapping("/actives")
    public ResponseEntity<List<MissionDTO>> getActiveMissions() {
        return ResponseEntity.ok(missionService.getActiveMissions());
    }

    /**
     * Récupérer les missions terminées
     */
    @GetMapping("/terminees")
    public ResponseEntity<List<MissionDTO>> getFinishedMissions() {
        return ResponseEntity.ok(missionService.getFinishedMissions());
    }

    /**
     * Récupérer les missions planifiées après une date donnée
     */
    @GetMapping("/date-debut")
    public ResponseEntity<List<MissionDTO>> getMissionsAfterDate(@RequestParam Date date) {
        return ResponseEntity.ok(missionService.getMissionsAfterDate(date));
    }

    /**
     * Vérifier si une mission chevauche une autre mission existante
     */
    @GetMapping("/chevauchement")
    public ResponseEntity<Boolean> checkMissionOverlap(@RequestParam Date dateDebut, @RequestParam Date dateFin) {
        return ResponseEntity.ok(missionService.checkMissionOverlap(dateDebut, dateFin));
    }
}

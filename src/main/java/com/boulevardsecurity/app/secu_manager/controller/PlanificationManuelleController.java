package com.boulevardsecurity.app.secu_manager.controller;

import com.boulevardsecurity.app.secu_manager.dto.PlanificationManuelleDTO;
import com.boulevardsecurity.app.secu_manager.service.PlanificationManuelleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planifications-manuelles")
@CrossOrigin(origins = "*") // Permet l'accès depuis le frontend Angular
public class PlanificationManuelleController {

    private final PlanificationManuelleService planificationService;

    @Autowired
    public PlanificationManuelleController(PlanificationManuelleService planificationService) {
        this.planificationService = planificationService;
    }

    /**
     * Récupérer toutes les planifications manuelles.
     */
    @GetMapping
    public ResponseEntity<List<PlanificationManuelleDTO>> getAllPlanifications() {
        return ResponseEntity.ok(planificationService.getAllPlanifications());
    }

    /**
     * Récupérer une planification manuelle par son ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlanificationManuelleDTO> getPlanificationById(@PathVariable int id) {
        return ResponseEntity.ok(planificationService.getPlanificationById(id));
    }

    /**
     * Ajouter ou mettre à jour une planification manuelle.
     */
    @PostMapping
    public ResponseEntity<PlanificationManuelleDTO> savePlanification(@RequestBody PlanificationManuelleDTO planificationDTO) {
        return ResponseEntity.ok(planificationService.savePlanification(planificationDTO));
    }

    /**
     * Supprimer une planification manuelle par son ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanification(@PathVariable int id) {
        planificationService.deletePlanification(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupérer les planifications associées à une mission spécifique.
     */
    @GetMapping("/mission/{missionId}")
    public ResponseEntity<List<PlanificationManuelleDTO>> getPlanificationsByMissionId(@PathVariable int missionId) {
        return ResponseEntity.ok(planificationService.getPlanificationsByMissionId(missionId));
    }

    /**
     * Récupérer les planifications impliquant un agent spécifique.
     */
    @GetMapping("/agent/{agentId}")
    public ResponseEntity<List<PlanificationManuelleDTO>> getPlanificationsByAgentId(@PathVariable int agentId) {
        return ResponseEntity.ok(planificationService.getPlanificationsByAgentId(agentId));
    }

    /**
     * Supprimer toutes les planifications associées à une mission spécifique.
     */
    @DeleteMapping("/mission/{missionId}")
    public ResponseEntity<Void> deletePlanificationsByMissionId(@PathVariable int missionId) {
        planificationService.deletePlanificationsByMissionId(missionId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Supprimer toutes les planifications associées à un agent spécifique.
     */
    @DeleteMapping("/agent/{agentId}")
    public ResponseEntity<Void> deletePlanificationsByAgentId(@PathVariable int agentId) {
        planificationService.deletePlanificationsByAgentId(agentId);
        return ResponseEntity.noContent().build();
    }
}

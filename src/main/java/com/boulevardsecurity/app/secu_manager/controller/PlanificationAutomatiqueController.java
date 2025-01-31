package com.boulevardsecurity.app.secu_manager.controller;

import com.boulevardsecurity.app.secu_manager.dto.PlanificationAutomatiqueDTO;
import com.boulevardsecurity.app.secu_manager.service.PlanificationAutomatiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planifications")
@CrossOrigin(origins = "*") // Permet l'accès depuis le frontend Angular
public class PlanificationAutomatiqueController {

    private final PlanificationAutomatiqueService planificationService;

    @Autowired
    public PlanificationAutomatiqueController(PlanificationAutomatiqueService planificationService) {
        this.planificationService = planificationService;
    }

    /**
     * Récupérer toutes les planifications automatiques.
     */
    @GetMapping
    public ResponseEntity<List<PlanificationAutomatiqueDTO>> getAllPlanifications() {
        return ResponseEntity.ok(planificationService.getAllPlanifications());
    }

    /**
     * Récupérer une planification par son ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlanificationAutomatiqueDTO> getPlanificationById(@PathVariable int id) {
        return ResponseEntity.ok(planificationService.getPlanificationById(id));
    }

    /**
     * Ajouter ou mettre à jour une planification automatique.
     */
    @PostMapping
    public ResponseEntity<PlanificationAutomatiqueDTO> savePlanification(@RequestBody PlanificationAutomatiqueDTO planificationDTO) {
        return ResponseEntity.ok(planificationService.savePlanification(planificationDTO));
    }

    /**
     * Supprimer une planification par son ID.
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
    public ResponseEntity<List<PlanificationAutomatiqueDTO>> getPlanificationsByMissionId(@PathVariable int missionId) {
        return ResponseEntity.ok(planificationService.getPlanificationsByMissionId(missionId));
    }

    /**
     * Récupérer les planifications impliquant un agent spécifique.
     */
    @GetMapping("/agent/{agentId}")
    public ResponseEntity<List<PlanificationAutomatiqueDTO>> getPlanificationsByAgentId(@PathVariable int agentId) {
        return ResponseEntity.ok(planificationService.getPlanificationsByAgentId(agentId));
    }
}

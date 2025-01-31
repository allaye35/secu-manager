package com.boulevardsecurity.app.secu_manager.controller;

import com.boulevardsecurity.app.secu_manager.dto.PlanificationOptimiseeDTO;
import com.boulevardsecurity.app.secu_manager.service.PlanificationOptimiseeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planifications-optimisees")
@CrossOrigin(origins = "*")
public class PlanificationOptimiseeController {

    private final PlanificationOptimiseeService planificationService;

    @Autowired
    public PlanificationOptimiseeController(PlanificationOptimiseeService planificationService) {
        this.planificationService = planificationService;
    }

    /**
     * Récupérer toutes les planifications optimisées.
     */
    @GetMapping
    public ResponseEntity<List<PlanificationOptimiseeDTO>> getAllPlanifications() {
        return ResponseEntity.ok(planificationService.getAllPlanifications());
    }

    /**
     * Récupérer une planification optimisée par son ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlanificationOptimiseeDTO> getPlanificationById(@PathVariable int id) {
        return ResponseEntity.ok(planificationService.getPlanificationById(id));
    }

    /**
     * Ajouter ou mettre à jour une planification optimisée.
     */
    @PostMapping
    public ResponseEntity<PlanificationOptimiseeDTO> savePlanification(@RequestBody PlanificationOptimiseeDTO dto) {
        return ResponseEntity.ok(planificationService.savePlanification(dto));
    }

    /**
     * Supprimer une planification optimisée par son ID.
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
    public ResponseEntity<List<PlanificationOptimiseeDTO>> getPlanificationsByMissionId(@PathVariable int missionId) {
        return ResponseEntity.ok(planificationService.getPlanificationsByMissionId(missionId));
    }

    /**
     * Récupérer les planifications impliquant un agent spécifique.
     */
    @GetMapping("/agent/{agentId}")
    public ResponseEntity<List<PlanificationOptimiseeDTO>> getPlanificationsByAgentId(@PathVariable int agentId) {
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

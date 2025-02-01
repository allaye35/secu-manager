package com.boulevardsecurity.app.secu_manager.controller;

import com.boulevardsecurity.app.secu_manager.dto.AgentDTO;
import com.boulevardsecurity.app.secu_manager.service.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/agents")
@CrossOrigin(origins = "*") // Permet les appels depuis un frontend (ex: Angular, React)
public class AgentController {

    private final IAgentService agentService;

    @Autowired
    public AgentController(IAgentService agentService) {
        this.agentService = agentService;
    }

    /**
     * Récupérer tous les agents.
     */
    @GetMapping
    public ResponseEntity<List<AgentDTO>> getAllAgents() {
        return ResponseEntity.ok(agentService.getAllAgents());
    }

    /**
     * Récupérer un agent par son ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AgentDTO> getAgentById(@PathVariable int id) {
        return ResponseEntity.ok(agentService.getAgentById(id));
    }

    /**
     * Ajouter ou mettre à jour un agent.
     */
    @PostMapping
    public ResponseEntity<AgentDTO> saveAgent(@RequestBody AgentDTO agentDTO) {
        AgentDTO savedAgent = agentService.saveAgent(agentDTO);
        return new ResponseEntity<>(savedAgent, HttpStatus.CREATED);
    }

    /**
     * Supprimer un agent par son ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable int id) {
        agentService.deleteAgent(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupérer les agents disponibles.
     */
    @GetMapping("/disponibles")
    public ResponseEntity<List<AgentDTO>> getAvailableAgents() {
        return ResponseEntity.ok(agentService.getAvailableAgents());
    }

    /**
     * Recherche des agents par statut.
     */
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<AgentDTO>> getAgentsByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(agentService.getAgentsByStatut(statut));
    }

    /**
     * Recherche d'agents ayant une certification spécifique.
     */
    @GetMapping("/certification/{certification}")
    public ResponseEntity<List<AgentDTO>> getAgentsByCertification(@PathVariable String certification) {
        return ResponseEntity.ok(agentService.getAgentsByCertification(certification));
    }

    /**
     * Recherche des agents embauchés après une date spécifique.
     */
    @GetMapping("/date-embauche")
    public ResponseEntity<List<AgentDTO>> getAgentsByDateEmbaucheAfter(@RequestParam Date date) {
        return ResponseEntity.ok(agentService.getAgentsByDateEmbaucheAfter(date));
    }
}

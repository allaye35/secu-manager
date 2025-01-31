package com.boulevardsecurity.app.secu_manager.controller;
import com.boulevardsecurity.app.secu_manager.dto.ContratDTO;
import com.boulevardsecurity.app.secu_manager.service.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@CrossOrigin(origins = "*") // Permet l'accès depuis le frontend Angular
public class ContratController {

    private final ContratService contratService;

    @Autowired
    public ContratController(ContratService contratService) {
        this.contratService = contratService;
    }

    /**
     * Récupérer tous les contrats
     */
    @GetMapping
    public ResponseEntity<List<ContratDTO>> getAllContrats() {
        return ResponseEntity.ok(contratService.getAllContrats());
    }

    /**
     * Récupérer un contrat par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContratDTO> getContratById(@PathVariable int id) {
        return ResponseEntity.ok(contratService.getContratById(id));
    }

    /**
     * Ajouter ou mettre à jour un contrat
     */
    @PostMapping
    public ResponseEntity<ContratDTO> saveContrat(@RequestBody ContratDTO contratDTO) {
        return ResponseEntity.ok(contratService.saveContrat(contratDTO));
    }

    /**
     * Supprimer un contrat par son ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrat(@PathVariable int id) {
        contratService.deleteContrat(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupérer les contrats d'un agent donné
     */
    @GetMapping("/agent/{agentId}")
    public ResponseEntity<List<ContratDTO>> getContratsByAgentId(@PathVariable int agentId) {
        return ResponseEntity.ok(contratService.getContratsByAgentId(agentId));
    }

    /**
     * Récupérer les contrats actifs (en cours)
     */
    @GetMapping("/actifs")
    public ResponseEntity<List<ContratDTO>> getActiveContrats() {
        return ResponseEntity.ok(contratService.getActiveContrats());
    }

    /**
     * Récupérer les contrats terminés
     */
    @GetMapping("/termines")
    public ResponseEntity<List<ContratDTO>> getFinishedContrats() {
        return ResponseEntity.ok(contratService.getFinishedContrats());
    }

    /**
     * Récupérer les contrats signés après une date donnée
     */
    @GetMapping("/date-debut")
    public ResponseEntity<List<ContratDTO>> getContratsAfterDate(@RequestParam Date date) {
        return ResponseEntity.ok(contratService.getContratsAfterDate(date));
    }
}

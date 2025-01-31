package com.boulevardsecurity.app.secu_manager.controller;

import com.boulevardsecurity.app.secu_manager.dto.AdministrateurDTO;
import com.boulevardsecurity.app.secu_manager.service.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/administrateurs")
@CrossOrigin(origins = "*")
public class AdministrateurController {

    private final AdministrateurService administrateurService;

    @Autowired
    public AdministrateurController(AdministrateurService administrateurService) {
        this.administrateurService = administrateurService;
    }

    /**
     * Récupérer tous les administrateurs.
     */
    @GetMapping
    public ResponseEntity<List<AdministrateurDTO>> getAllAdministrateurs() {
        return ResponseEntity.ok(administrateurService.getAllAdministrateurs());
    }

    /**
     * Récupérer un administrateur par son ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AdministrateurDTO> getAdministrateurById(@PathVariable int id) {
        return ResponseEntity.ok(administrateurService.getAdministrateurById(id));
    }

    /**
     * Récupérer un administrateur par son email.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<AdministrateurDTO>> getAdministrateurByEmail(@PathVariable String email) {
        return ResponseEntity.ok(administrateurService.getAdministrateurByEmail(email));
    }

    /**
     * Ajouter ou mettre à jour un administrateur.
     */
    @PostMapping
    public ResponseEntity<AdministrateurDTO> saveAdministrateur(@RequestBody AdministrateurDTO administrateurDTO) {
        return ResponseEntity.ok(administrateurService.saveAdministrateur(administrateurDTO));
    }

    /**
     * Supprimer un administrateur par son ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable int id) {
        administrateurService.deleteAdministrateur(id);
        return ResponseEntity.noContent().build();
    }
}

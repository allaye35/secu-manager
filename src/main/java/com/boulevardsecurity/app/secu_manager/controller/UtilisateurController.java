package com.boulevardsecurity.app.secu_manager.controller;
import com.boulevardsecurity.app.secu_manager.dto.UtilisateurDTO;
import com.boulevardsecurity.app.secu_manager.model.Role;
import com.boulevardsecurity.app.secu_manager.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "*") // Permet les appels depuis le frontend Angular
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired // Injection de dépendance via le constructeur
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Récupérer tous les utilisateurs
     */
    @GetMapping
    public ResponseEntity<List<UtilisateurDTO>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs());
    }

    /**
     * Récupérer un utilisateur par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurById(@PathVariable int id) {
        return ResponseEntity.ok(utilisateurService.getUtilisateurById(id));
    }

    /**
     * Ajouter ou mettre à jour un utilisateur
     */
    @PostMapping
    public ResponseEntity<UtilisateurDTO> saveUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        return ResponseEntity.ok(utilisateurService.saveUtilisateur(utilisateurDTO));
    }

    /**
     * Supprimer un utilisateur par son ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable int id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupérer les utilisateurs par rôle
     */
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UtilisateurDTO>> getUtilisateursByRole(@PathVariable Role role) {
        return ResponseEntity.ok(utilisateurService.getUtilisateursByRole(role));
    }

    /**
     * Récupérer les agents disponibles
     */
    @GetMapping("/disponibles")
    public ResponseEntity<List<UtilisateurDTO>> getUtilisateursDisponibles() {
        return ResponseEntity.ok(utilisateurService.getUtilisateursDisponibles());
    }

    /**
     * Recherche d'un utilisateur par nom
     */
    @GetMapping("/search")
    public ResponseEntity<List<UtilisateurDTO>> searchUtilisateursByNom(@RequestParam String nom) {
        return ResponseEntity.ok(utilisateurService.searchUtilisateursByNom(nom));
    }
}



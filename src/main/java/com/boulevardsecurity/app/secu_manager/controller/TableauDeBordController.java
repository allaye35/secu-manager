package com.boulevardsecurity.app.secu_manager.controller;
import com.boulevardsecurity.app.secu_manager.dto.TableauDeBordDTO;
import com.boulevardsecurity.app.secu_manager.service.TableauDeBordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tableaux-de-bord")
@CrossOrigin(origins = "*")
public class TableauDeBordController {

    private final TableauDeBordService tableauDeBordService;

    @Autowired
    public TableauDeBordController(TableauDeBordService tableauDeBordService) {
        this.tableauDeBordService = tableauDeBordService;
    }

    /**
     * Récupérer tous les tableaux de bord.
     */
    @GetMapping
    public ResponseEntity<List<TableauDeBordDTO>> getAllTableauxDeBord() {
        return ResponseEntity.ok(tableauDeBordService.getAllTableauxDeBord());
    }

    /**
     * Récupérer un tableau de bord par son ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TableauDeBordDTO> getTableauDeBordById(@PathVariable int id) {
        return ResponseEntity.ok(tableauDeBordService.getTableauDeBordById(id));
    }

    /**
     * Ajouter ou mettre à jour un tableau de bord.
     */
    @PostMapping
    public ResponseEntity<TableauDeBordDTO> saveTableauDeBord(@RequestBody TableauDeBordDTO tableauDeBordDTO) {
        return ResponseEntity.ok(tableauDeBordService.saveTableauDeBord(tableauDeBordDTO));
    }

    /**
     * Supprimer un tableau de bord par son ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableauDeBord(@PathVariable int id) {
        tableauDeBordService.deleteTableauDeBord(id);
        return ResponseEntity.noContent().build();
    }
}

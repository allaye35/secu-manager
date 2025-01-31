package com.boulevardsecurity.app.secu_manager.controller;

import com.boulevardsecurity.app.secu_manager.dto.PointeDTO;
import com.boulevardsecurity.app.secu_manager.service.PointeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pointes")
public class PointeController {

    private final PointeService pointeService;

    public PointeController(PointeService pointeService) {
        this.pointeService = pointeService;
    }

    @GetMapping
    public List<PointeDTO> getAllPointes() {
        return pointeService.getAllPointes();
    }

    @GetMapping("/{id}")
    public PointeDTO getPointeById(@PathVariable int id) {
        return pointeService.getPointeById(id);
    }

    @PostMapping
    public PointeDTO savePointe(@RequestBody PointeDTO pointeDTO) {
        return pointeService.saveOrUpdatePointe(pointeDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePointe(@PathVariable int id) {
        pointeService.deletePointe(id);
    }

    @PostMapping("/{pointeId}/associer-agent/{agentId}")
    public PointeDTO associerAgent(@PathVariable int pointeId, @PathVariable int agentId) {
        return pointeService.associerAgent(pointeId, agentId);
    }

    @GetMapping("/{id}/verifier-presence")
    public boolean verifierPresence(@PathVariable int id) {
        return pointeService.verifierPresence(id);
    }
}

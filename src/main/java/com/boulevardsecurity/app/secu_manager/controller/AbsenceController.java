package com.boulevardsecurity.app.secu_manager.controller;

import com.boulevardsecurity.app.secu_manager.dto.AbsenceDTO;
import com.boulevardsecurity.app.secu_manager.service.AbsenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/absences")
public class AbsenceController {

    private final AbsenceService absenceService;

    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @GetMapping
    public List<AbsenceDTO> getAllAbsences() {
        return absenceService.getAllAbsences();
    }

    @PostMapping
    public AbsenceDTO saveAbsence(@RequestBody AbsenceDTO absenceDTO) {
        return absenceService.saveOrUpdateAbsence(absenceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAbsence(@PathVariable int id) {
        absenceService.deleteAbsence(id);
    }
}

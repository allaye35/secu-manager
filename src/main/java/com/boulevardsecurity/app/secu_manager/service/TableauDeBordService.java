package com.boulevardsecurity.app.secu_manager.service;
import com.boulevardsecurity.app.secu_manager.dto.TableauDeBordDTO;
import com.boulevardsecurity.app.secu_manager.mapper.TableauDeBordMapper;
import com.boulevardsecurity.app.secu_manager.model.TableauDeBord;
import com.boulevardsecurity.app.secu_manager.repository.TableauDeBordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableauDeBordService {

    private final TableauDeBordRepository tableauDeBordRepository;
    private final TableauDeBordMapper tableauDeBordMapper;

    @Autowired
    public TableauDeBordService(TableauDeBordRepository tableauDeBordRepository, TableauDeBordMapper tableauDeBordMapper) {
        this.tableauDeBordRepository = tableauDeBordRepository;
        this.tableauDeBordMapper = tableauDeBordMapper;
    }

    /**
     * Récupérer tous les tableaux de bord.
     */
    @Transactional(readOnly = true)
    public List<TableauDeBordDTO> getAllTableauxDeBord() {
        return tableauDeBordRepository.findAll().stream()
                .map(tableauDeBordMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer un tableau de bord par son ID.
     */
    @Transactional(readOnly = true)
    public TableauDeBordDTO getTableauDeBordById(int id) {
        TableauDeBord tableauDeBord = tableauDeBordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tableau de bord avec ID " + id + " non trouvé"));
        return tableauDeBordMapper.toDto(tableauDeBord);
    }

    /**
     * Créer ou mettre à jour un tableau de bord.
     */
    @Transactional
    public TableauDeBordDTO saveTableauDeBord(TableauDeBordDTO dto) {
        TableauDeBord tableauDeBord = tableauDeBordMapper.toEntity(dto);
        TableauDeBord savedTableauDeBord = tableauDeBordRepository.save(tableauDeBord);
        return tableauDeBordMapper.toDto(savedTableauDeBord);
    }

    /**
     * Supprimer un tableau de bord par son ID.
     */
    @Transactional
    public void deleteTableauDeBord(int id) {
        if (!tableauDeBordRepository.existsById(id)) {
            throw new RuntimeException("Tableau de bord avec ID " + id + " non trouvé");
        }
        tableauDeBordRepository.deleteById(id);
    }
}

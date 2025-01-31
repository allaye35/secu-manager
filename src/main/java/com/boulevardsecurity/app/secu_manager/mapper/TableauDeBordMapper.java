package com.boulevardsecurity.app.secu_manager.mapper;

import com.boulevardsecurity.app.secu_manager.dto.TableauDeBordDTO;
import com.boulevardsecurity.app.secu_manager.model.TableauDeBord;
import org.springframework.stereotype.Component;

@Component
public class TableauDeBordMapper {
    public TableauDeBordDTO toDto(TableauDeBord tableauDeBord) {
        return new TableauDeBordDTO(tableauDeBord.getId(), tableauDeBord.getInformations());
    }

    public TableauDeBord toEntity(TableauDeBordDTO dto) {
        return TableauDeBord.builder()
                .id(dto.getId())
                .informations(dto.getInformations())
                .build();
    }
}
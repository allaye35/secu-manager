package com.boulevardsecurity.app.secu_manager.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private int id;
    private String type;
    private String contenu;
}

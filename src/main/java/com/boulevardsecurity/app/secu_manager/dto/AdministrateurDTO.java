package com.boulevardsecurity.app.secu_manager.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministrateurDTO {
    private int id;
    private String nom;
    private String email;
}
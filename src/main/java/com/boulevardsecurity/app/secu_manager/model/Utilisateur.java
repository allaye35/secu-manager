package com.boulevardsecurity.app.secu_manager.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ElementCollection
    private List<String> permissions;
}

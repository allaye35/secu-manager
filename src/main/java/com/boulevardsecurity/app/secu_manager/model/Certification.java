package com.boulevardsecurity.app.secu_manager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    private String organisme;

    private String dateObtention;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent; // Relation inverse
}

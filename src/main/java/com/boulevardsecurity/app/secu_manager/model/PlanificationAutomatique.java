package com.boulevardsecurity.app.secu_manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanificationAutomatique implements StrategiePlanification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private List<Agent> agents;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Override
    public void planifierMission(List<Agent> agents, Mission mission) {
        // Implémentation de l'algorithme de planification automatique
        System.out.println("Planification automatique de la mission " + mission.getId() + " avec " + agents.size() + " agents.");
    }
}

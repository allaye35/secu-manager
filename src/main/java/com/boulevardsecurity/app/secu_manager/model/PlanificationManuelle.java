package com.boulevardsecurity.app.secu_manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanificationManuelle implements StrategiePlanification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "agent_id")
    private List<Agent> agents;

    @Override
    public void planifierMission(List<Agent> agents, Mission mission) {
        System.out.println("Planification manuelle pour la mission : " + mission.getId());
    }
}

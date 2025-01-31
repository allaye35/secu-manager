package com.boulevardsecurity.app.secu_manager.model;

import java.util.List;

public interface StrategiePlanification {
    /**
     * Planifier une mission en fonction de la stratégie définie.
     *
     * @param agents  Liste des agents disponibles
     * @param mission Mission à planifier
     */
    void planifierMission(List<Agent> agents, Mission mission);
}

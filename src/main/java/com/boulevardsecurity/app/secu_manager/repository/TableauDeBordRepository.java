package com.boulevardsecurity.app.secu_manager.repository;

import com.boulevardsecurity.app.secu_manager.model.TableauDeBord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableauDeBordRepository extends JpaRepository<TableauDeBord, Integer> {
}

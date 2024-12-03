package org.project.stockqueen.repository;

import org.project.stockqueen.entity.SaleHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesHistoryJpaRepository extends JpaRepository<SaleHistory, Long> {

}

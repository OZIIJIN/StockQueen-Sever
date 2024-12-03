package org.project.stockqueen.repository.sales_history;

import org.project.stockqueen.entity.SaleHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesHistoryJpaRepository extends JpaRepository<SaleHistory, Long> {

}

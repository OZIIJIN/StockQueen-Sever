package org.project.stockqueen.repository.sales_history;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.project.stockqueen.entity.QSaleHistory;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SalesHistoryQueryImpl implements SalesHistoryQuery {

  private final JPAQueryFactory queryFactory;

  @Override
  public Integer sumSalesByMonth(Long menuId) {

    QSaleHistory qSaleHistory = QSaleHistory.saleHistory;

    return queryFactory.select(qSaleHistory.salesQuantity.sum())
        .from(qSaleHistory)
        .where(qSaleHistory.menu.id.eq(menuId))
        .where(qSaleHistory.date.between("2024-10-01", "2024-10-31"))
        .fetchOne();
  }
}

package org.project.stockqueen.service;

import lombok.RequiredArgsConstructor;
import org.project.stockqueen.dto.DemandForecastingResponse;
import org.project.stockqueen.entity.Menu;
import org.project.stockqueen.repository.DemandJpaRepository;
import org.project.stockqueen.repository.MenuJpaRepository;
import org.project.stockqueen.repository.SalesHistoryJpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

  private final DemandJpaRepository demandJpaRepository;
  private final SalesHistoryJpaRepository salesHistoryJpaRepository;
  private final MenuJpaRepository menuJpaRepository;

  public DemandForecastingResponse getDemandForecasting(String menuName) {

    Menu menu = menuJpaRepository.findByMenuName(menuName)
        .orElseThrow(() -> new IllegalArgumentException("해당 메뉴는 존재하지 않습니다."));

    //flask로 수요 예측 결과를 받아와야함

    //받아온 결과를 db에 저장하고

    //response를 만들어서(현재 월/수량, 예측 월/수량) 전달

    return new DemandForecastingResponse(
        9, 194, 10, 245, "딸기라떼"
    );
  }
}

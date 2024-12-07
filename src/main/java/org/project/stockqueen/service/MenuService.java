package org.project.stockqueen.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.project.stockqueen.dto.DemandForecastingResponse;
import org.project.stockqueen.dto.LstmResponse;
import org.project.stockqueen.dto.MenuNameResponseDto;
import org.project.stockqueen.entity.Menu;
import org.project.stockqueen.repository.DemandJpaRepository;
import org.project.stockqueen.repository.MenuJpaRepository;
import org.project.stockqueen.repository.sales_history.SalesHistoryJpaRepository;
import org.project.stockqueen.repository.sales_history.SalesHistoryQuery;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MenuService {

  private final DemandJpaRepository demandJpaRepository;
  private final SalesHistoryJpaRepository salesHistoryJpaRepository;
  private final SalesHistoryQuery salesHistoryQuery;
  private final MenuJpaRepository menuJpaRepository;
  private final RestTemplate restTemplate;

  public DemandForecastingResponse getDemandForecasting(String menuName) {

    Menu menu = menuJpaRepository.findByMenuName(menuName)
        .orElseThrow(() -> new IllegalArgumentException("해당 메뉴는 존재하지 않습니다."));

    //flask로 수요 예측 결과를 받아와야함
    String url = "https://5808-122-36-149-213.ngrok-free.app/menu-predictions";

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");

    String requestBody = String.format("{\"menu_id\": %d}", menu.getId());

    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    LstmResponse response = restTemplate.postForObject(
        url,
        requestEntity,
        LstmResponse.class
    );

    //받아온 결과를 db에 저장하고

    //response를 만들어서(현재 월/수량, 예측 월/수량) 전달

    int previousSum = salesHistoryQuery.sumSalesByMonth(menu.getId());

    long predictedSum = Math.round(
        response.getPrediction().stream().mapToDouble(Double::doubleValue).sum());

    return new DemandForecastingResponse(
        11, previousSum, 12, (int) predictedSum, menu.getMenuName()
    );
  }

  public MenuNameResponseDto getMenuNames() {
    List<Menu> menus = menuJpaRepository.findAll();
    List<String> menuNameList = new ArrayList<>();

    for (Menu m : menus) {
      menuNameList.add(m.getMenuName());
    }
    return new MenuNameResponseDto(menuNameList);
  }
}

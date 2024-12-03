package org.project.stockqueen.controller;

import lombok.RequiredArgsConstructor;
import org.project.stockqueen.dto.DemandForecastingResponse;
import org.project.stockqueen.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
public class MenuController {

  private final MenuService menuService;

  @GetMapping("/{menuName}/predictions")
  public DemandForecastingResponse getDemandForecasting(@PathVariable String menuName) {
    return menuService.getDemandForecasting(menuName);
  }


}

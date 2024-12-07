package org.project.stockqueen.controller;

import lombok.RequiredArgsConstructor;
import org.project.stockqueen.dto.DemandForecastingResponse;
import org.project.stockqueen.dto.MenuNameResponseDto;
import org.project.stockqueen.service.IngredientService;
import org.project.stockqueen.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
public class MenuController {

  private final MenuService menuService;
  private final IngredientService ingredientService;

  @GetMapping("/{menuName}/predictions")
  public DemandForecastingResponse getDemandForecasting(@PathVariable String menuName) {
    return menuService.getDemandForecasting(menuName);
  }

  @PatchMapping("/{menuName}/sales")
  public void updateIngredientOnSale(@PathVariable String menuName) {
    ingredientService.updateIngredientOnSale(menuName);
  }

  @GetMapping("/menunames")
  public MenuNameResponseDto getMenuNames() {
    return menuService.getMenuNames();
  }

}

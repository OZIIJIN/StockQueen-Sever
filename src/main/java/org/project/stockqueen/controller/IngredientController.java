package org.project.stockqueen.controller;

import lombok.RequiredArgsConstructor;
import org.project.stockqueen.dto.IngredientListResponse;
import org.project.stockqueen.service.IngredientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ingredients")
public class IngredientController {

  private final IngredientService ingredientService;

  @GetMapping()
  public IngredientListResponse getIngredientList() {

    return ingredientService.getIngredientList();

  }

  @PostMapping()
  public void createIngredient() {
    ingredientService.createIngredient();
  }

}

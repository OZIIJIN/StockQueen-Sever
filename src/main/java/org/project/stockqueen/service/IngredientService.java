package org.project.stockqueen.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.project.stockqueen.dto.IngredientListResponse;
import org.project.stockqueen.dto.IngredientResponse;
import org.project.stockqueen.entity.Ingredient;
import org.project.stockqueen.entity.Menu;
import org.project.stockqueen.entity.MenuRecipe;
import org.project.stockqueen.repository.IngredientJpaRepository;
import org.project.stockqueen.repository.MenuJpaRepository;
import org.project.stockqueen.repository.menurecipe.MenuRecipeJpaRepository;
import org.project.stockqueen.repository.menurecipe.MenuRecipeQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IngredientService {

  private final IngredientJpaRepository ingredientJpaRepository;
  private final MenuJpaRepository menuJpaRepository;
  private final MenuRecipeQuery menuRecipeQuery;
  private final MenuRecipeJpaRepository menuRecipeJpaRepository;

  @Transactional(readOnly = true)
  public IngredientListResponse getIngredientList() {
    List<Ingredient> list = ingredientJpaRepository.findAll();
    List<IngredientResponse> responses = new ArrayList<>();

    for (Ingredient i : list) {
      double remain = (double) i.getRemain() / i.getMax();

      IngredientResponse ingredientResponse = new IngredientResponse(i.getIngredientName(),
          i.getExpiryDate(), String.valueOf(remain) + " " + String.valueOf(i.getUnit()), false);

      responses.add(ingredientResponse);
    }
    return new IngredientListResponse(responses);
  }

  @Transactional
  public void updateIngredientOnSale(String menuName) {
    Menu menu = menuJpaRepository.findByMenuName(menuName)
        .orElseThrow(() -> new IllegalArgumentException("해당 메뉴는 존재하지 않습니다."));

    List<MenuRecipe> menuRecipes = menuRecipeJpaRepository.findByMenu(menu);

    for (MenuRecipe m : menuRecipes) {
      Ingredient usedIngredient = m.getIngredient();
      int usedAmount = m.getUsedAmount();

      usedIngredient.updateAmount(usedAmount);
    }

  }
}

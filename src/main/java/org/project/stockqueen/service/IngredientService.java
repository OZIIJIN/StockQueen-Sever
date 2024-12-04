package org.project.stockqueen.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.project.stockqueen.dto.IngredientListResponse;
import org.project.stockqueen.dto.IngredientResponse;
import org.project.stockqueen.entity.Ingredient;
import org.project.stockqueen.repository.IngredientJpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService {

  private final IngredientJpaRepository ingredientJpaRepository;

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

}

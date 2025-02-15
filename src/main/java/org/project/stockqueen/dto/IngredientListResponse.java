package org.project.stockqueen.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientListResponse {

  List<IngredientResponse> ingredientResponseList;
}

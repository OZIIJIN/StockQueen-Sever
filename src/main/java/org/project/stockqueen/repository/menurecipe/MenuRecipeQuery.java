package org.project.stockqueen.repository.menurecipe;

import java.util.List;
import org.project.stockqueen.entity.Ingredient;

public interface MenuRecipeQuery {

  List<Ingredient> findIngredientByMenuRecipe(Long menuId);

}

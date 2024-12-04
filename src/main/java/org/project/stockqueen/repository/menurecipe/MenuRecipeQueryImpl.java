package org.project.stockqueen.repository.menurecipe;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.project.stockqueen.entity.Ingredient;
import org.project.stockqueen.entity.QIngredient;
import org.project.stockqueen.entity.QMenuRecipe;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MenuRecipeQueryImpl implements MenuRecipeQuery {

  private final JPAQueryFactory queryFactory;

  @Override
  public List<Ingredient> findIngredientByMenuRecipe(Long menuId) {
    QMenuRecipe qMenuRecipe = QMenuRecipe.menuRecipe;
    QIngredient qIngredient = QIngredient.ingredient;

    return queryFactory
        .select(qIngredient)
        .from(qMenuRecipe)
        .join(qMenuRecipe.ingredient, qIngredient)
        .where(qMenuRecipe.menu.id.eq(menuId))
        .fetch();
  }
}

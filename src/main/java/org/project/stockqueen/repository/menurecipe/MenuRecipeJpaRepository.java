package org.project.stockqueen.repository.menurecipe;

import java.util.List;
import org.project.stockqueen.entity.Menu;
import org.project.stockqueen.entity.MenuRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRecipeJpaRepository extends JpaRepository<MenuRecipe, Long> {

  List<MenuRecipe> findByMenu(Menu menu);

}

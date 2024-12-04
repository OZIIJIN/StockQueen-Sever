package org.project.stockqueen.repository;

import org.project.stockqueen.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientJpaRepository extends JpaRepository<Ingredient, Long> {

}

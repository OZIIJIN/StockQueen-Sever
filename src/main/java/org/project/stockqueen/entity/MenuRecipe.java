package org.project.stockqueen.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class MenuRecipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private int usedAmount;

  @JoinColumn(name = "menu_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private Menu menu;

  @JoinColumn(name = "ingredient_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private Ingredient ingredient;
}

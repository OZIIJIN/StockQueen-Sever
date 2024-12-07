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

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String ingredientName;

  @Column
  private Integer remain;

  @Column
  private Integer reorderPoint;

  @Column
  private String unit;

  @Column
  private String expiryDate;

  @Column
  private Integer max;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ingredientGroup_id")
  private IngredientGroup ingredientGroup;

  public void subtractAmount(int usedAmount) {
    this.remain -= usedAmount;
  }

  public void addAmount() {
    this.remain += max;
  }
}

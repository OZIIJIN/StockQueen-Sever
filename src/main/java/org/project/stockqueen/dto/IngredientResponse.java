package org.project.stockqueen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientResponse {

  private String name;
  private String expiryDate;
  private String quantity;
  private boolean isExpired;
}

package org.project.stockqueen.dto;

import lombok.Getter;

@Getter
public class DemandForecastingResponse {

  private String previousMonth;

  private double previousSales;

  private String predictedMonth;

  private double predictedSales;

  private String menuName;

  public DemandForecastingResponse(
      int previousMonth,
      int previousSales,
      int predictedMonth,
      int predictedSales,
      String menuName
  ) {
    this.previousMonth = String.valueOf(previousMonth) + "월";
    this.previousSales = previousSales;
    this.predictedMonth = String.valueOf(predictedMonth) + "월";
    this.predictedSales = predictedSales;
    this.menuName = menuName;
  }

}

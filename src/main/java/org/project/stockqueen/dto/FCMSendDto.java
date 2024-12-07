package org.project.stockqueen.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder(toBuilder = true)
public class FCMSendDto {

  private String token;

  private String title;

  private String body;

  public FCMSendDto(String token, String title, String body) {
    this.token = token;
    this.title = title;
    this.body = body;
  }

}

package org.project.stockqueen.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private role role;

  @Column
  private String name;

  @Column
  private int password;

  @Column
  private String fcmToken;

  public User(org.project.stockqueen.entity.user.role role, String nickname, int password,
      String fcmtoken) {
    this.role = role;
    this.name = nickname;
    this.password = password;
    this.fcmToken = fcmtoken;
  }
}

package org.project.stockqueen.service;

import static org.project.stockqueen.entity.user.role.STAFF;

import lombok.RequiredArgsConstructor;
import org.project.stockqueen.dto.UserSignUpRequestDto;
import org.project.stockqueen.entity.user.User;
import org.project.stockqueen.repository.UserJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserJpaRepository userJpaRepository;

  @Transactional
  public void signUp(UserSignUpRequestDto requestDto) {
    User user = userJpaRepository.findByName(requestDto.getNickname());
    if (user == null) {
      User newUser = new User(
          STAFF,
          requestDto.getNickname(),
          1518,
          requestDto.getFcmtoken()
      );

      userJpaRepository.save(newUser);
    }
  }
}

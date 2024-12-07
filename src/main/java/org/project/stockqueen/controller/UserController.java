package org.project.stockqueen.controller;

import lombok.RequiredArgsConstructor;
import org.project.stockqueen.dto.UserSignUpRequestDto;
import org.project.stockqueen.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @PostMapping("/sign-up")
  public void signUp(@RequestBody UserSignUpRequestDto requestDto) {
    userService.signUp(requestDto);
  }

}

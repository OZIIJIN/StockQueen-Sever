package org.project.stockqueen.service;

import lombok.RequiredArgsConstructor;
import org.project.stockqueen.repository.menurecipe.MenuRecipeQuery;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuRecipeService {

  private final MenuRecipeQuery menuRecipeQuery;


}

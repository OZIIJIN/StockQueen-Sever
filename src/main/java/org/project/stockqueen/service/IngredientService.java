package org.project.stockqueen.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.project.stockqueen.dto.FCMSendDto;
import org.project.stockqueen.dto.IngredientListResponse;
import org.project.stockqueen.dto.IngredientResponse;
import org.project.stockqueen.entity.Ingredient;
import org.project.stockqueen.entity.Menu;
import org.project.stockqueen.entity.MenuRecipe;
import org.project.stockqueen.entity.user.User;
import org.project.stockqueen.repository.IngredientJpaRepository;
import org.project.stockqueen.repository.MenuJpaRepository;
import org.project.stockqueen.repository.UserJpaRepository;
import org.project.stockqueen.repository.menurecipe.MenuRecipeJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IngredientService {

  private final IngredientJpaRepository ingredientJpaRepository;
  private final MenuJpaRepository menuJpaRepository;
  private final MenuRecipeJpaRepository menuRecipeJpaRepository;
  private final FcmService fcmService;
  private final UserJpaRepository userJpaRepository;

  @Transactional(readOnly = true)
  public IngredientListResponse getIngredientList() {
    List<Ingredient> list = ingredientJpaRepository.findAll();
    List<IngredientResponse> responses = new ArrayList<>();
    LocalDate now = LocalDate.now();

    for (Ingredient i : list) {
      double remain = (double) i.getRemain() / i.getMax();

      LocalDate parsedDate = LocalDate.parse(i.getExpiryDate());
      boolean isPast = now.isAfter(parsedDate);

      IngredientResponse ingredientResponse = new IngredientResponse(i.getIngredientName(),
          i.getExpiryDate(), String.valueOf(remain) + " " + String.valueOf(i.getUnit()), isPast);

      responses.add(ingredientResponse);
    }
    return new IngredientListResponse(responses);
  }

  @Transactional
  public void updateIngredientOnSale(String menuName) {
    Menu menu = menuJpaRepository.findByMenuName(menuName)
        .orElseThrow(() -> new IllegalArgumentException("해당 메뉴는 존재하지 않습니다."));

    User user = userJpaRepository.findByName("1518");

    List<MenuRecipe> menuRecipes = menuRecipeJpaRepository.findByMenu(menu);

    for (MenuRecipe m : menuRecipes) {
      Ingredient usedIngredient = m.getIngredient();
      int usedAmount = m.getUsedAmount();

      usedIngredient.subtractAmount(usedAmount);

      // 재고 현황이 재주문점보다 작으면 fcm 메세지 전송
      if (usedIngredient.getRemain() <= usedIngredient.getReorderPoint()) {
        FCMSendDto fcmSendDto = FCMSendDto.builder()
            .token(user.getFcmToken())
            .title(usedIngredient.getIngredientName() + " 재주문하세요!")
            .body("지금 재주문해야 품절을 방지할 수 있어요.")
            .build();
        sendNotification(fcmSendDto);
      }

      // 유통기한 일주일전이면 fcm 메세지 전송
      LocalDate now = LocalDate.now();
      LocalDate parsedDate = LocalDate.parse(usedIngredient.getExpiryDate());
      boolean isExpiringSoon = !now.plusDays(7).isBefore(parsedDate);
    }

  }

  private void sendNotification(FCMSendDto fcmSendDto) {

    int retryCount = 3;
    int attempt = 0;
    boolean success = false;

    while (attempt < retryCount && !success) {
      attempt++;
      try {
        fcmService.sendMessageTo(fcmSendDto);
        success = true;
      } catch (FirebaseMessagingException | IOException e) {
        throw new RuntimeException("FCM 메시지 전송 실패", e);
      }
    }
  }

  @Transactional
  public void createIngredient() {
    Ingredient ingredient = ingredientJpaRepository.findByIngredientName("얼그레이 파우더")
        .orElseThrow(() -> new IllegalArgumentException("해당 재고는 존재하지 않습니다."));

    User user = userJpaRepository.findByName("1518");

    FCMSendDto fcmSendDto = FCMSendDto.builder()
        .token(user.getFcmToken())
        .title(ingredient.getIngredientName() + " 등록되었습니다!")
        .build();
    sendNotification(fcmSendDto);

    ingredient.addAmount();
  }
}

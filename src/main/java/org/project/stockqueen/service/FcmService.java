package org.project.stockqueen.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.stockqueen.dto.FCMSendDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FcmService {

  private final FirebaseMessaging firebaseMessaging;
  private static final Logger logger = LoggerFactory.getLogger(FcmService.class);

  @Async
  public void sendMessageTo(FCMSendDto fcmSendDto) throws IOException, FirebaseMessagingException {

    long sendStartTime = System.currentTimeMillis();
    logger.info("FCM 메시지 전송 시작: {}", sendStartTime);

    Message message = Message.builder()
        .setToken(fcmSendDto.getToken())
        .setNotification(Notification.builder()
            .setTitle(fcmSendDto.getTitle())
            .setBody(fcmSendDto.getBody())
            .build())
        .build();
    firebaseMessaging.send(message);
  }

}

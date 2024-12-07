package org.project.stockqueen.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.FileInputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FirebaseConfig {

  @Value("${Firebase_Key}")
  String fcmKeyPath;

  @Bean
  public FirebaseApp firebaseApp() throws IOException {
    // 로그로 경로 출력
    log.info("FIREBASE_KEY_PATH: " + fcmKeyPath);

    // 실제 파일 경로를 통해 Firebase 키 파일 읽기
    try (FileInputStream serviceAccount = new FileInputStream(fcmKeyPath)) {
      FirebaseOptions options = FirebaseOptions.builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .build();

      return FirebaseApp.initializeApp(options);
    }
  }

  @Bean
  public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
    return FirebaseMessaging.getInstance(firebaseApp);
  }

}

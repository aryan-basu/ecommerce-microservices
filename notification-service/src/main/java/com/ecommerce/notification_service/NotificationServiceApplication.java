package com.ecommerce.notification_service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationServiceApplication {

	static {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		String appPassword = dotenv.get("GMAIL_APP_PASSWORD");
		if (appPassword != null) {
			System.setProperty("GMAIL_APP_PASSWORD", appPassword);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}

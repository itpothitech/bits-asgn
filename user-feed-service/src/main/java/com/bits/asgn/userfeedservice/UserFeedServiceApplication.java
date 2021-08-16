package com.bits.asgn.userfeedservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserFeedServiceApplication {
	private static final Logger logger = LoggerFactory.getLogger(UserFeedServiceApplication.class);

	@Autowired
	UserFeedService userFeedService;

	public static void main(String[] args) {
		SpringApplication.run(UserFeedServiceApplication.class, args);
	}

	@KafkaListener(topics = "TutorialTopic", groupId = "group_id")
	public void consume(String message) {
		logger.info("Consumed message: " + message);
		// Build user feed for followers
		userFeedService.buildUserFeedForFollowers(message);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}

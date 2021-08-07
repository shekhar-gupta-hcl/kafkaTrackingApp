package com.tracker.kafkaConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.tracker.kafkaConsumer.service.StreamConsumer;

@SpringBootApplication
public class KafkaConsumerApplication {
	
	@Autowired
	private StreamConsumer consumer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void consumeData() {
		consumer.consumeMessage();
	}

}

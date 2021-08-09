package com.tracker.kafkaProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.tracker.kafkaProducer.service.Producer;


@SpringBootApplication
public class KafkaProducerApplication {
	
	@Autowired
	private Producer producer;


	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}
	
	/* Calling producer method to publish data on application start */
	@EventListener(ApplicationReadyEvent.class)
	public void publishData() {
		producer.publishToTopic();
	}
}

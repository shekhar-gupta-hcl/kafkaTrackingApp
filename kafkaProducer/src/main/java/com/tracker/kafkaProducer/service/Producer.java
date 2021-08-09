package com.tracker.kafkaProducer.service;

import java.io.BufferedReader;
import java.io.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tracker.kafkaProducer.config.AppConfig;

@Service
public class Producer {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	/* Reads GPS data from CSV and publishes it to a topic */
	public void publishToTopic() {
		try {			
			String filePath = System.getProperty("user.dir") + AppConfig.filePath;

	        // reading CSV file
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	            	System.out.println("Publishing data = " + line);
	            	
	            	// publishing data to Kafka topic
	            	this.kafkaTemplate.send(AppConfig.topicName, line);
	            	
	            	// waiting for some time before publishing next set of data
	            	Thread.sleep(100);
	            }
	        }
			
		}catch(Exception ex) {
			System.out.println("Exception " + ex.getMessage());
		}		
	}
}

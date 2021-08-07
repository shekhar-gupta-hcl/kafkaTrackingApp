package com.tracker.kafkaProducer.service;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	public static final String topic = "tracker";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemp;
	
	public void publishToTopic() {
		try {			
			String filePath = System.getProperty("user.dir") + "/data/cycle_gpx.csv";

	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	            	System.out.println("Publishing data = " + line);
	            	this.kafkaTemp.send(topic, line);
	            	 Thread.sleep(100);
	            }
	        }
			
		}catch(Exception ex) {
			System.out.println("Exception " + ex.getMessage());
		}		
	}
	
	@Bean
    public NewTopic createKafkaTopic() {
         return new NewTopic(topic, 1, (short) 1);
    }
	

}

package com.tracker.kafkaConsumer.service;

import java.util.Map;
import java.util.Properties;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.kafkaConsumer.config.AppConfig;
import com.tracker.kafkaConsumer.helper.Utility;

@Service
public class StreamConsumer {
	
	private boolean startPoint = true;
	private double totalDistanceCovered;
	private long totalTravelTime;
	private String initialDateTime;
	private double initialLatitude;
	private double initialLongitude;
	
	@Autowired
	private Utility utility;
	
	public void consumeMessage() {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.applicationID);
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.bootstrapServers);
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		
		StreamsBuilder streamBuilder = new StreamsBuilder();
		KStream<Integer, String> kstream = streamBuilder.stream(AppConfig.topicName);
		kstream.foreach((key,trackingData) -> {
			CalculateDistanceAndTime(trackingData);
		});
		
		Topology topology = streamBuilder.build();
		KafkaStreams streams = new KafkaStreams(topology, props);
		
		System.out.println("Starting the stream");
		streams.start();
		
		Runtime.getRuntime().addShutdownHook(new Thread( () -> {
			System.out.println("Shutting down stream");
			streams.close();
		}));
				
	}
	
	public void CalculateDistanceAndTime(String data) {
		Map<String, String> trackingDataValues  = utility.cleanData(data);
		
		Double latitude = Double.valueOf(trackingDataValues.get("latitude"));
		Double longitude = Double.valueOf(trackingDataValues.get("longitude"));
		String time = trackingDataValues.get("time");
		
		if (startPoint) {
			initialLatitude = latitude;
			initialLongitude = longitude;
			initialDateTime = time;
			startPoint = false;
			totalDistanceCovered = 0;
		} else {
			totalTravelTime = utility.calculateTravelTime(time, initialDateTime);
			double distanceCovered = utility.calculateDistance(latitude, longitude, initialLatitude, initialLongitude);
			initialLatitude = latitude;
			initialLongitude = longitude;

			totalDistanceCovered = totalDistanceCovered + distanceCovered;
			System.out.println("Total Travel time is :" + totalTravelTime + " seconds and Total Distance Covered is : "
					+ totalDistanceCovered + " km");
		}
		
	}
}

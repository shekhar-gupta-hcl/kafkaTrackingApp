package com.tracker.kafkaConsumer.service;
import java.util.Properties;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.tracker.kafkaConsumer.config.AppConfig;
import com.tracker.kafkaConsumer.helper.Utility;
import com.tracker.kafkaConsumer.model.LocationData;

@Service
public class StreamConsumer {

	private LocationData prevLocationData;
	
	@Autowired
	private Utility utility;
	
	/* Consume data from kafka topic and processing the same using kstream to calculate
	 * time and distance traveled.
	 * @param void
	 * @return void
	 */
	public void consumeMessage() {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.applicationID);
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.bootstrapServers);
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		
		StreamsBuilder streamBuilder = new StreamsBuilder();
		KStream<Integer, String> kstream = streamBuilder.stream(AppConfig.topicName);
		
        // Mapping the stream to transformed result data object         
		KStream<Integer, LocationData> kstreamValues = kstream.map((k, v) -> new KeyValue<>(k, transformTrackingData(v)));
		
		kstreamValues.foreach((k, v) -> System.out.print("Total Travel time is :" + v.getTravelTime()
				+ " seconds and Total Distance Covered is : " + v.getDistanceTraveled() + " km \n"));

		Topology topology = streamBuilder.build();
		KafkaStreams streams = new KafkaStreams(topology, props);
		
		System.out.println("Starting the stream");
		streams.start();
		
		Runtime.getRuntime().addShutdownHook(new Thread( () -> {
			System.out.println("Shutting down stream");
			streams.close();
		}));
				
	}
	
	/* Transform the tracking coordinate data to object with distance & time calculation
	 * 
	 * @param String data
	 * @return LocationData LocationData
	 */
	public LocationData transformTrackingData(String data) {
		LocationData locationData = utility.getLocationData(data);
		
		if(prevLocationData == null) {
			locationData.setDistanceTraveled(0);
			locationData.setTravelTime(0);
		}else {
			long travelTime = utility.calculateTravelTime(locationData, prevLocationData);				
			double distanceCovered = utility.calculateDistance(locationData, prevLocationData);
			locationData.setDistanceTraveled(distanceCovered);
			locationData.setTravelTime(travelTime);
		}
		
		prevLocationData = locationData;	
		return locationData;
		
	}
	
	/* Method to create new kafka topic */
	@Bean
    public NewTopic createKafkaTopic() {
         return new NewTopic(AppConfig.topicName, 1, (short) 1);
    }
}

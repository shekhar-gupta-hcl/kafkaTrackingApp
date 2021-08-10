package com.tracker.kafkaConsumer.seredes;

import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracker.kafkaConsumer.model.LocationData;

public class LocationDeserializer implements Deserializer<LocationData> {

    @Override 
    public void configure(Map<String, ?> arg0, boolean arg1) {
    	
    }
    @Override
    public LocationData deserialize(String topic, byte[] arg1) {
    	ObjectMapper mapper = new ObjectMapper();
    	LocationData locationData = null;
        try {
        	locationData = mapper.readValue(arg1, LocationData.class);
        } catch (Exception e) {

          e.printStackTrace();
        }
        return locationData;
    }

    @Override
    public void close() {
        // nothing to do
    }
}

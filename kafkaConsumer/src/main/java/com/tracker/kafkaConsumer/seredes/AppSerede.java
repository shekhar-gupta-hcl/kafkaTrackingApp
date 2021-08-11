package com.tracker.kafkaConsumer.seredes;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes.WrapperSerde;

import com.tracker.kafkaConsumer.model.LocationData;
import com.tracker.kafkaConsumer.model.TravelData;

public class AppSerede {

    static final class LocationDataSerde extends WrapperSerde<LocationData> {
    	LocationDataSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<LocationData> LocationData() {
    	LocationDataSerde serde = new LocationDataSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, LocationData.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class TravelDataSerde extends WrapperSerde<TravelData> {
    	TravelDataSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<TravelData> TravelData() {
    	TravelDataSerde serde = new TravelDataSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, TravelData.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

}

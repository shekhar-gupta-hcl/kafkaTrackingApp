package com.tracker.kafkaConsumer.seredes;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import com.tracker.kafkaConsumer.model.LocationData;

public final class SeredeFactory {
	
	static public final class LocationSerde extends Serdes.WrapperSerde<LocationData> {
		public LocationSerde() {
			super(new LocationSerializer(), new LocationDeserializer());
		}
	}

	public static Serde<LocationData> TrackLocation() {
		return new SeredeFactory.LocationSerde();
	}


}

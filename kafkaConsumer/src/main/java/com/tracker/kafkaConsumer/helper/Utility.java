package com.tracker.kafkaConsumer.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class Utility {
	public long calculateTravelTime(String currentDateTime, String initialDateTime) {
		long totalTimeTravelled = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date initialDate = sdf.parse(initialDateTime);
			Date currentDate = sdf.parse(currentDateTime);
			
			totalTimeTravelled = currentDate.getTime() - initialDate.getTime();
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());	
		}
		
		return TimeUnit.MILLISECONDS.toSeconds(totalTimeTravelled);
	}


	@SuppressWarnings("serial")
	public Map<String, String> cleanData(String trackingData) {
		String[] data = trackingData.split(",");
		
		Map<String, String> trackingDataValues = new HashMap<String, String>() {{
	        put("latitude", data[0].trim());
	        put("longitude", data[1].trim());
	        put("time", data[2].trim());
	    }};

		return trackingDataValues;

	}
	
	public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		lon1 = Math.toRadians(lon1);
		lon2 = Math.toRadians(lon2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

		double c = 2 * Math.asin(Math.sqrt(a));

		double r = 6371;
		return (c * r);
	}

}

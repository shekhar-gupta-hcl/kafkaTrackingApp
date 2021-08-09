package com.tracker.kafkaConsumer.helper;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.tracker.kafkaConsumer.model.LocationData;

@Service
public class Utility {
	
	/* Calculates total time traveled between two latitude and longitude points
	 * @param LocationData locationData
	 * @param LocationData prevLocationData
	 * @return double totalTravelTime 
	 */
	public long calculateTravelTime(LocationData locationData, LocationData prevLocationData) {
		long timeDifference = locationData.getTime() - prevLocationData.getTime();
		timeDifference = TimeUnit.MILLISECONDS.toSeconds(timeDifference);
		long totalTravelTime = prevLocationData.getTravelTime() + timeDifference ;
		return totalTravelTime;
		
	}
	
	/* Calculates total distance covered between two latitude and longitude points
	 * @param LocationData locationData
	 * @param LocationData prevLocationData
	 * @return double totalDistanceTraveled 
	 */
	public double calculateDistance(LocationData locationData, LocationData prevLocationData) {
		double lon1 = Math.toRadians(locationData.getLongitude());
		double lon2 = Math.toRadians(prevLocationData.getLongitude());
		double lat1 = Math.toRadians(locationData.getLatitude());
		double lat2 = Math.toRadians(prevLocationData.getLatitude());

		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

		double c = 2 * Math.asin(Math.sqrt(a));

		double r = 6371;
		
		double totalDistanceTraveled = prevLocationData.getDistanceTraveled() + (c * r);
		return totalDistanceTraveled;
	}

	/* Utility method to convert date time into epoch milliseconds
	 * @param String dateTime
	 * @return long timeInMillis 
	 */
	public long convertTimeToMillis(String dateTime) {
		long timeInMillis = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = sdf.parse(dateTime);			
			timeInMillis = date.getTime();
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());	
		}	
		return timeInMillis;
	}
	
	/* Method to build location data model object
	 * @param String data
	 * @return LocationData locationData 
	 */
	public LocationData getLocationData(String data) {
		LocationData locationData = new LocationData();
		
		try {
			List<String> trackingDataPoints = Arrays.asList(data.split(","));
			
			Double latitude = Double.valueOf(trackingDataPoints.get(0).trim());
			Double longitude = Double.valueOf(trackingDataPoints.get(1).trim());
			long time = convertTimeToMillis(trackingDataPoints.get(2).trim());
			
			locationData.setLatitude(latitude);
			locationData.setLongitude(longitude);
			locationData.setTime(time);
			
		}catch(Exception ex) {
			System.out.println(ex.getStackTrace());
		}
		return locationData;
	}


}

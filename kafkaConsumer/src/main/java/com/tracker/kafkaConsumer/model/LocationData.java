package com.tracker.kafkaConsumer.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "travelTime",
    "distanceTraveled",
    "latitude",
    "longitude",
    "time"
})

public class LocationData {
	@JsonProperty("travelTime")
	private long travelTime;
	
	@JsonProperty("distanceTraveled")
	private double distanceTraveled;
	
	@JsonProperty("latitude")
	private double latitude;
	
	@JsonProperty("longitude")
	private double longitude;
	
	@JsonProperty("time")
	private long time;
	
	public long getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(long travelTime) {
		this.travelTime = travelTime;
	}
	public double getDistanceTraveled() {
		return distanceTraveled;
	}
	public void setDistanceTraveled(double distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	 @Override public String toString() {
		   return "Location(" + travelTime + ", " + distanceTraveled + ")";
		 }
	
	public void update(LocationData latestLocationData) {
		this.distanceTraveled = latestLocationData.getDistanceTraveled();
		this.travelTime = latestLocationData.getTravelTime();
	}
	
    public LocationData withTotalTime(long time) {
        this.travelTime = time;
        return this;
    }
    
    public LocationData withTotalDistance(double distance) {
        this.distanceTraveled = distance;
        return this;
    }
   
}

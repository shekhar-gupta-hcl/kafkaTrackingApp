package com.tracker.kafkaConsumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "travelTime",
    "distanceTraveled"
})
public class TravelData {
	@JsonProperty("travelTime")
	private long totalTravelTime;
	
	@JsonProperty("distanceTraveled")
	private double totalDistanceTraveled;
		
    public long getTotalTravelTime() {
		return totalTravelTime;
	}

	public void setTotalTravelTime(long totalTravelTime) {
		this.totalTravelTime = totalTravelTime;
	}

	public double getTotalDistanceTraveled() {
		return totalDistanceTraveled;
	}

	public void setTotalDistanceTraveled(double totalDistanceTraveled) {
		this.totalDistanceTraveled = totalDistanceTraveled;
	}

	public TravelData withTotalTime(long time) {
        this.totalTravelTime = time;
        return this;
    }
    
    public TravelData withTotalDistance(double distance) {
        this.totalDistanceTraveled = distance;
        return this;
    }
    
	 @Override 
	 public String toString() {
		   return "total time in seconds : " + totalTravelTime + ", total distance in km : " + totalDistanceTraveled ;
	  }
}

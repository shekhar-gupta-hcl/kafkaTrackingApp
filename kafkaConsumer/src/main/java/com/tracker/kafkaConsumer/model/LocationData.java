package com.tracker.kafkaConsumer.model;

public class LocationData {
	private long travelTime;
	private double distanceTraveled;
	private double latitude;
	private double longitude;
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
}

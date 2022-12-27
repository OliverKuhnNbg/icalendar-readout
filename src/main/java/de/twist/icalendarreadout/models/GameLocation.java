package de.twist.icalendarreadout.models;

public class GameLocation {
	
	private String street;
	private String zip;
	private String city;
	
	public GameLocation() {
	}
	
	public GameLocation(String street, String zip, String city) {
		this.street = street;
		this.zip = zip;
		this.city = city;
	}
	
	
	public String getStreet() {
		return street;
	}
	public String getZip() {
		return zip;
	}
	public String getCity() {
		return city;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public void setCity(String city) {
		this.city = city;
	}
}

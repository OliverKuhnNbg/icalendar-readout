package de.twist.icalendarreadout.models;

public class Game {

	private String homeTeam;
	private String guestTeam;
	private String location;

	public String getHomeTeam() {
		return homeTeam;
	}

	public String getGuestTeam() {
		return guestTeam;
	}

	public String getLocation() {
		return location;
	}

	
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}

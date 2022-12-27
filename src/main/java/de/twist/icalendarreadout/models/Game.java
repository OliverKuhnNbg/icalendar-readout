package de.twist.icalendarreadout.models;

public class Game {

	private String homeTeam;
	private String guestTeam;
	private GameLocation location;

	public String getHomeTeam() {
		return homeTeam;
	}

	public String getGuestTeam() {
		return guestTeam;
	}

	public GameLocation getLocation() {
		return location;
	}

	
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}

	public void setLocation(GameLocation location) {
		this.location = location;
	}
}

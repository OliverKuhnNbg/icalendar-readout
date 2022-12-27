package de.twist.icalendarreadout.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import de.twist.icalendarreadout.models.Game;
import de.twist.icalendarreadout.models.GameData;
import de.twist.icalendarreadout.models.GameLocation;

@Service
public class ICSCalendarParseService {

	public List<GameData> parseCalendarEventsToList(File file) {

		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String currentLine = "";

			int j = 0;
			for (int i = 0; (currentLine = br.readLine()) != null; i++) {
				j++;
				if (currentLine.equals("BEGIN:VEVENT")) {
					j = 0;
					System.out.println("\n\teventStart");
				}

				// --- all lines without single shedule start line "BEGIN:VEVENT" ---
				if (i > 4 && j > 0) {
					// filter out single data
					parseData(j, currentLine);
				}
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void parseData(int row, String currentLine) {
		String dataString = "";
		GameLocation gameLocation = new GameLocation();
		Game game = new Game();
		
		// parse data
		if (row == 1) { // STAMP - updated date of file
			dataString = splitDataString(currentLine);
			
			LocalDate date = getLocalDateFromString(dataString);
			LocalTime time = getLocalTimeFromString(dataString);
			
			System.out.println(row + " date: " + date + "\ttime: " + time);
		} else if (row == 2) { // START
			dataString = splitDataString(currentLine);
			
			LocalDate date = getLocalDateFromString(dataString);
			LocalTime time = getLocalTimeFromString(dataString);
			
			LocalDateTime ldt = LocalDateTime.of(date, time);
			Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
			
			System.out.println(row + " date: " + date + "\ttime: " + time);
			System.out.println(out);
		} else if (row == 3) { // END
			dataString = splitDataString(currentLine);
			
			LocalDate date = getLocalDateFromString(dataString);
			LocalTime time = getLocalTimeFromString(dataString);
			
			System.out.println(row + " date: " + date + "\ttime: " + time);
		} else if (row == 4) { // SUMMERY -- team data
			dataString = splitDataString(currentLine);
			System.out.println(row + " " + dataString);
			
			String[] dataStringArr = dataString.split("-");
			String homeTeam = dataStringArr[0];
			String guestTeam = dataStringArr[1].split(",")[0].replace("\\","");
			
			game.setHomeTeam(homeTeam);
			game.setGuestTeam(guestTeam);
			
			System.out.println("\t homeTeam: " + game.getHomeTeam());
			System.out.println("\tguestTeam: " + game.getGuestTeam());
			
		} else if (row == 5) { // LOCATION -- adress data
			System.out.println(row + " " + currentLine);
			dataString = splitDataString(currentLine);
			
			if (!dataString.equals("LOCATION")) {
				String[] stringLocationArr = dataString.split(",");
				String street = stringLocationArr[0].replace("\\", "").trim();
				String zip = stringLocationArr[1].replace("\\", "").trim();
				String city = stringLocationArr[2].trim();
				
				gameLocation = new GameLocation(street, zip, city);
				game.setLocation(gameLocation);
				
				System.out.println("\t street: "+ game.getLocation().getStreet());
				System.out.println("\t    plz: "+ game.getLocation().getZip());
				System.out.println("\t   city: "+ game.getLocation().getCity());
			}
		} else if (row == 6) { // UID
//			System.out.println(row + " " + currentLine);
		}
	}
		
	private LocalDate getLocalDateFromString(String dataString) {
		String[] dateStringArr = dataString.split("T");
		String dateString = dateStringArr[0];
		LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.BASIC_ISO_DATE);
		
		return date;
	}
	
	private LocalTime getLocalTimeFromString(String dataString) {
		String[] dateStringArr = dataString.split("T");
		String timeString = dateStringArr[1].replace("Z", "");
		
		timeString = timeString.substring(0, 2) + ":" + timeString.substring(2, 4) + ":" + timeString.substring(4, 6);
		LocalTime time = LocalTime.parse(timeString);
		
		return time;
	}
	
	
	private String splitDataString(String currentLine) {
		String [] arr = currentLine.split(":");
		if (arr.length > 1) {
			currentLine = currentLine.split(":")[1];
		} else {
			currentLine = currentLine.split(":")[0];
		}
		return currentLine;
	}

	public File getSystemFile() {
		Resource resource = new ClassPathResource("Spielplan_N-PMS_21-12-2022_bis_26-03-2023.ics");
		File file = new File("");

		try {
			file = resource.getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}
}

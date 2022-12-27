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
import java.util.ArrayList;
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
	
	private GameData gameData = new GameData();

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
	
	public List<GameData> parseCalendarEventsToList(File file) {
		List<GameData> eventList = new ArrayList<>();

		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String currentLine = "";

			int j = 0;
			for (int i = 0; (currentLine = br.readLine()) != null; i++) {
				j++;
				if (currentLine.equals("BEGIN:VEVENT")) {
					j = 0;
					gameData = new GameData();
					System.out.println("\n\teventStart");
				}

				// --- all lines without single shedule start line "BEGIN:VEVENT" ---
				if (i > 4 && j > 0) {
					// filter out single data
					gameData = parseData(j, currentLine, gameData);
				}

				if (currentLine.equals("END:VEVENT")) {
					System.out.println("\n\t GameDate-Obj");
					System.out.println(gameData.getDtStamp());
					System.out.println(gameData.getDtStart());
					System.out.println(gameData.getDtEnd());
					
					System.out.println("\n\t summery");
					System.out.println(gameData.getSummery().getHomeTeam());
					System.out.println(gameData.getSummery().getGuestTeam());
					System.out.println("\n\t location");
					System.out.println(gameData.getSummery().getLocation().getCity());
					System.out.println(gameData.getSummery().getLocation().getZip());
					System.out.println(gameData.getSummery().getLocation().getStreet());
					eventList.add(gameData);
				}
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return eventList.size() > 1 ? eventList : null;
	}

	private GameData parseData(int row, String currentLine, GameData gameData) {
		String dataString = "";
		GameLocation gameLocation = new GameLocation();
		Game game = new Game();

		// parse data
		if (row == 1) { // STAMP - updated date of file
			dataString = splitDataString(currentLine);
			
			Date date = getDateFromString(dataString);
			gameData.setDtStamp(date);
		} else if (row == 2) { // START
			dataString = splitDataString(currentLine);
			
			Date date = getDateFromString(dataString);
			gameData.setDtStart(date);
		} else if (row == 3) { // END
			dataString = splitDataString(currentLine);
			
			Date date = getDateFromString(dataString);
			gameData.setDtEnd(date);
		} else if (row == 4) { // SUMMERY -- team data
			dataString = splitDataString(currentLine);
			
			String[] dataStringArr = dataString.split("-");
			String homeTeam = dataStringArr[0];
			String guestTeam = dataStringArr[1].split(",")[0].replace("\\","");
			
			game.setHomeTeam(homeTeam);
			game.setGuestTeam(guestTeam);
			
			gameData.setSummery(game);
		} else if (row == 5) { // LOCATION -- adress data
			dataString = splitDataString(currentLine);
			
			if (!dataString.equals("LOCATION")) {
				String[] stringLocationArr = dataString.split(",");
				String street = stringLocationArr[0].replace("\\", "").trim();
				String zip = stringLocationArr[1].replace("\\", "").trim();
				String city = stringLocationArr[2].trim();
				
				gameLocation = new GameLocation(street, zip, city);
				gameData.getSummery().setLocation(gameLocation);
			} else {
				gameLocation = new GameLocation();
				gameData.getSummery().setLocation(gameLocation);
			}
		} else if (row == 6) { // UID
		}
		
		return gameData;
	}
		
	private Date getDateFromString(String dataString) {
		// generate LocalDate
		String[] dateStringArr = dataString.split("T");
		String dateString = dateStringArr[0];
		LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.BASIC_ISO_DATE);
		
		// generate LocalTime
		String timeString = dateStringArr[1].replace("Z", "");
		timeString = timeString.substring(0, 2) + ":" + timeString.substring(2, 4) + ":" + timeString.substring(4, 6);
		LocalTime localTime = LocalTime.parse(timeString);
		
		return getDateFromLocalDateAndLocalTime(localDate, localTime);
	}
	
	private Date getDateFromLocalDateAndLocalTime (LocalDate localDate, LocalTime localTime) {
		LocalDateTime ldt = LocalDateTime.of(localDate, localTime);
		Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		return date;
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
}

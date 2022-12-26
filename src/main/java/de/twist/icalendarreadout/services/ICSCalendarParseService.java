package de.twist.icalendarreadout.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import de.twist.icalendarreadout.models.Game;
import de.twist.icalendarreadout.models.GameData;

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
					orderData(j, currentLine);
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

	private void orderData(int row, String currentLine) {
		String dataString = "";
		
		if (row == 1) { // STAMP
			dataString = splitDataString(currentLine);
//			Date date = new Date();
//			System.out.println("\t" + date);
			System.out.println(row + " " + dataString);
			
			//TODO get Date 
//			String[] dateStringArr = dataString.split("T");
//			String dateString = dateStringArr[0];
//			System.out.println("test: " + dateString);
//			
//			Long longValue = Long.parseLong(dateString);
//			date.setTime(longValue * 1000L);
//			System.out.println("test value: " + date);
//			System.out.println("test: " + new Date( longValue * 1000));
			
		} else if (row == 2) { // START
			System.out.println(row + " " + currentLine);
		} else if (row == 3) { // END
			System.out.println(row + " " + currentLine);
		} else if (row == 4) { // SUMMERY
			dataString = splitDataString(currentLine);
			System.out.println(row + " " + dataString);
			
			// seperate home & guest team
			String[] dataStringArr = dataString.split("-");
			String homeTeam = dataStringArr[0];
			String guestTeam = dataStringArr[1].split(",")[0].replace("\\","");
			
			Game game = new Game();
			game.setHomeTeam(homeTeam);
			game.setGuestTeam(guestTeam);
			
			System.out.println("\t homeTeam: " + game.getHomeTeam());
			System.out.println("\tguestTeam: " + game.getGuestTeam());
			
		} else if (row == 5) { // LOCATION
			System.out.println(row + " " + currentLine);
		} else if (row == 6) { // UID
			System.out.println(row + " " + currentLine);
		}
	}
		
	private String splitDataString(String currentLine) {
		return currentLine.split(":")[1];
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

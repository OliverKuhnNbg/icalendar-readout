package de.twist.icalendarreadout.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import de.twist.icalendarreadout.models.GameData;

@Service
public class ICSCalendarParseService {

	
	 public List<GameData> parseCalendarEventsToList() {
		 return null;
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

package de.twist.icalendarreadout.controllers;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.twist.icalendarreadout.models.GameData;
import de.twist.icalendarreadout.services.ICSCalendarParseService;

@RestController
@RequestMapping("/")
public class GameSheduleController {
	
	@Autowired
	private ICSCalendarParseService parseService;
	
	@GetMapping(value="/get-all-game-dates")
	public List<GameData> getAllGameDates() {
		System.out.println(parseService.getSystemFile().exists() + "\n\n");
		File gameSheduleFile = parseService.getSystemFile();
		List<GameData> eventList = parseService.parseCalendarEventsToList(gameSheduleFile);
		
		System.out.println("\namount of events: " + eventList.size());
		System.out.println(eventList.get(49).getSummery().getLocation().getCity());
		return eventList;
	}
}

package de.twist.icalendarreadout.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.twist.icalendarreadout.models.GameData;
import de.twist.icalendarreadout.services.ICSCalendarParseService;

@RestController
@RequestMapping("/api")
public class GameSheduleController {
	
	@Autowired
	private ICSCalendarParseService parseService;
	
	@GetMapping(value="/get-all-game-dates")
	public String getAllGameDates() {
		System.out.println("\n\ntest call all games");
		System.out.println(parseService.getSystemFile().exists() + "\n\n");
		File gameSheduleFile = parseService.getSystemFile();
		parseService.parseCalendarEventsToList(gameSheduleFile);
		
		List<GameData> list = new ArrayList<>();
		return "<b>Oli</b>";
	}
}

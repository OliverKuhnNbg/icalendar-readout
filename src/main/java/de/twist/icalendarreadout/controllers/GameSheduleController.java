package de.twist.icalendarreadout.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.twist.icalendarreadout.models.GameData;

@RestController
@RequestMapping("/api")
public class GameSheduleController {
	
	@GetMapping(value="/get-all-game-dates")
	public String getAllGameDates() {
		System.out.println("\n\ntest call all games");
		
		List<GameData> list = new ArrayList<>();
		return "<b>Oli</b>";
	}
}

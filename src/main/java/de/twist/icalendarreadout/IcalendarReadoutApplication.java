package de.twist.icalendarreadout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.twist.icalendarreadout.services.ICSCalendarParseService;

@SpringBootApplication
public class IcalendarReadoutApplication {
	
	@Autowired
	private ICSCalendarParseService parseService;

	public static void main(String[] args) {
		SpringApplication.run(IcalendarReadoutApplication.class, args);
	}
}

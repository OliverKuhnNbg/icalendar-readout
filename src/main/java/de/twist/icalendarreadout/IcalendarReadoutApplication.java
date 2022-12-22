package de.twist.icalendarreadout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import de.twist.icalendarreadout.services.ICSCalendarParseService;

@SpringBootApplication
public class IcalendarReadoutApplication {
	
	@Autowired
	private ICSCalendarParseService parseService;

	public static void main(String[] args) {
		SpringApplication.run(IcalendarReadoutApplication.class, args);
		
//		Resource resource = new ClassPathResource("Spielplan_N-PMS_21-12-2022_bis_26-03-2023.ics");
//		File file;
//		String content;
//	
//		try {
//			file = resource.getFile();
//			content = new String(Files.readAllBytes(file.toPath()));
//			Scanner sc = new Scanner(file);
//			
//			while (sc.hasNextLine()) {
//				System.out.println(sc.nextLine());
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
	}
	
	@Bean
    List init(ICSCalendarParseService parseService) {
        
            System.out.println("test");
            File file = parseService.getSystemFile();
            
            Scanner sc;
			try {
				sc = new Scanner(file);
				
				while (sc.hasNextLine()) {
					System.out.println(sc.nextLine());
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("\n" + file.length());
			return null;
    }

}

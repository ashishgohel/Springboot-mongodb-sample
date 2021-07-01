package com.element.assignment;

import com.element.assignment.config.CustomResponseEntityErrorHandler;
import com.element.assignment.document.Trip;
import com.element.assignment.repository.TripRepository;
import com.mongodb.DuplicateKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.util.Date;

@SpringBootApplication
@Import(CustomResponseEntityErrorHandler.class)
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
    TripRepository tripRepository;

	@PostConstruct
	public void populateData(){
		logger.info("Populating data");
		try{
			Trip trip = tripRepository.findByTripName("Shire");
			if(trip == null){
				tripRepository.save(new Trip("Shire", new Date(), new Date(), 5, 100, 29.90));
				tripRepository.save(new Trip("Gondor", new Date(), new Date(), 11, 50, 59.90));
				tripRepository.save(new Trip("Mordor", new Date(), new Date(), 18, 40, 99.90));
			}
		}catch (DuplicateKeyException exception){
			logger.info("Entries are already present in the DB");
		}
	}


}

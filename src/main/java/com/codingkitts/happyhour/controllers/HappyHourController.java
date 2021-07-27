package com.codingkitts.happyhour.controllers;

import com.codingkitts.happyhour.entities.HappyHour;
import com.codingkitts.happyhour.models.userInput.UserInput;
import com.codingkitts.happyhour.services.HappyHourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HappyHourController {
    private static final Logger logger = LoggerFactory.getLogger(HappyHourController.class);

    private final HappyHourService happyHourService;

    public HappyHourController(HappyHourService happyHourService) {
        this.happyHourService = happyHourService;
    }

    //ADMIN Functions
    @GetMapping("/specials")
    public List<HappyHour> getAllHappyHours() {
        logger.info("Happy Hour: GET all happy hours called");

        return this.happyHourService.getAllHappyHours();
    }

    //GET Functions
    @GetMapping("/specials/{happyHourId}")
    public ResponseEntity<HappyHour> getHappyHourById(@PathVariable Long happyHourId) {
        logger.info("Happy Hour: GET happy hour by id - " + happyHourId);

        Optional<HappyHour> hh1 = this.happyHourService.getHappyHourById(happyHourId);

        return hh1.map(happyHour -> new ResponseEntity<>(happyHour, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/specials/search")
    public List<HappyHour> getHappyHoursInRadius(@RequestBody UserInput userInput) {
        //TODO: For the physicalAddress that comes in from the User, remove leading & trailing whitespace.
        return this.happyHourService.getHappyHoursWithinRadius(userInput.getRadius(), userInput.getPhysicalAddress());
    }

    //POST Functions
    @PostMapping("/special")
    public ResponseEntity<HappyHour> createNewHappyHour(@RequestBody HappyHour happyHour) {
        logger.info("Happy Hour: POST New Happy Hour" + happyHour.getVenueName());

        HappyHour hh1 = this.happyHourService.createNewHappyHour(happyHour);

        return new ResponseEntity<>(hh1, HttpStatus.CREATED);
    }

    //PUT Functions
    @PutMapping("/special")
    public ResponseEntity<HappyHour> editHappyHourById(@RequestBody HappyHour happyHour) {
        logger.info("Happy Hour: POST update Happy Hour " + happyHour.getVenueName());

        if (this.happyHourService.getHappyHourById(happyHour.getHappyHourId()).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(this.happyHourService.editHappyHour(happyHour), HttpStatus.OK);
    }

    //DELETE Functions
    @DeleteMapping("/special/{happyHourId}")
    public ResponseEntity<HappyHour> deleteHappyHourById(@PathVariable Long happyHourId) {
        logger.info("Happy Hour: DELETE Happy Hour by ID");

        return new ResponseEntity<>(this.happyHourService.deleteHappyHourById(happyHourId));

    }

    //TODO: Incorporate a request to add/remove Happy Hours for Users that search and find no happy hours. This helps
    //      remove a lot of the reliance on my side to populate the DB. 
}
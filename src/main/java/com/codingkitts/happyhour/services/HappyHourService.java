package com.codingkitts.happyhour.services;

import com.codingkitts.happyhour.entities.HappyHour;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface HappyHourService {

    //GET Functions
    List<HappyHour> getAllHappyHours();
    Optional<HappyHour> getHappyHourById(Long happyHourId);
    List<HappyHour> getHappyHoursWithinRadius(Integer searchRadius, String physicalAddress);

    //POST Functions
    HappyHour createNewHappyHour(HappyHour happyHour);

    //PUT Functions
    HappyHour editHappyHour(HappyHour happyHour);

    //DELETE Functions
    HttpStatus deleteHappyHourById(Long happyHourId);
}

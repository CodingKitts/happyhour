package com.codingkitts.happyhour.services.impl;

import com.codingkitts.happyhour.entities.HappyHour;
import com.codingkitts.happyhour.models.geocode.GeocodeLocation;
import com.codingkitts.happyhour.models.geocode.GeocodeResult;
import com.codingkitts.happyhour.repos.HappyHourRepository;
import com.codingkitts.happyhour.services.HappyHourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.time.Duration;
import java.util.*;

@Service
public class HappyHourServiceImpl implements HappyHourService {
    //TODO: At some point create an API vault in some cloud to leverage.

    private static final Logger logger = LoggerFactory.getLogger(HappyHourServiceImpl.class);
    private static final String G_API_LOC = "C:\\Users\\Harrison\\Desktop\\gapi-hh.txt";
    private static final String G_API_ADD = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    private final HappyHourRepository happyHourRepository;
    private final RestTemplate restTemplate;

    public HappyHourServiceImpl(HappyHourRepository happyHourRepository, RestTemplateBuilder restTemplateBuilder) {
        this.happyHourRepository = happyHourRepository;
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
    }

    //GET Functions
    @Override
    public List<HappyHour> getAllHappyHours() {
        return this.happyHourRepository.findAll();
    }

    @Override
    public Optional<HappyHour> getHappyHourById(Long happyHourId) {
        return this.happyHourRepository.findById(happyHourId);
    }

    @Override
    public List<HappyHour> getHappyHoursWithinRadius(Integer searchRadius, String physicalAddress) {
        //7.26.21: You were able to get the search working, you haven't limit tested it yet.
        //TODO: Make sure the returned HashMap is not empty.
        HashMap<String, Double> userLatLng = convertAddressToLatLong(physicalAddress);
        logger.info("USER LAT: "+userLatLng.get("Latitude"));
        logger.info("USER LNG: "+userLatLng.get("Longitude"));

        return this.happyHourRepository.findHappyHoursWithinDistance(userLatLng.get("Latitude"), userLatLng.get("Longitude"), searchRadius.doubleValue());
    }

    //POST Functions
    @Override
    public HappyHour createNewHappyHour(HappyHour happyHour) {
        return this.happyHourRepository.save(happyHour);
    }

    //PUT Functions
    @Override
    public HappyHour editHappyHour(HappyHour happyHour) {
        //TODO: Update this function
        //Make sure the ID exists.
        return this.happyHourRepository.save(happyHour);
    }

    //DELETE Functions
    @Override
    public HttpStatus deleteHappyHourById(Long happyHourId) {
        if (this.happyHourRepository.findById(happyHourId).isPresent()) {
            this.happyHourRepository.deleteById(happyHourId);
            if (this.happyHourRepository.findById(happyHourId).isPresent()) {
                return HttpStatus.BAD_REQUEST;
            } else {
                return HttpStatus.OK;
            }
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    //Helper function to convert a string street address to it's Latitude / Longitude value. This is done via Google API
    private HashMap<String, Double> convertAddressToLatLong(String physicalAddress) {
        //Initialize object for return.
        HashMap<String, Double> latLng = new HashMap<>();

        //Read API Key from File, or return error.
        String api = "&key=";
        try {
            api += getAPIKeyFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        final String url = G_API_ADD+physicalAddress+api;

        //Make Google Geocoding API call, store response in response.
        //TODO: Check for error in the response object, specifically bad HTTP Result Codes.
        ResponseEntity<GeocodeResult> response = this.restTemplate.getForEntity(url, GeocodeResult.class);

        //Get the latitude / longitude. Make sure there actually is a valid result in the Body.
        //TODO: Make sure the response body has a valid result.
        GeocodeLocation location = Objects.requireNonNull(response.getBody()).getResults().get(0).getGeometry().getGeocodeLocation();

        //Put the API returned Lat/Lng into the HashMap.
        //TODO: Make sure Lat/Lng are not empty.
        latLng.put("Latitude", location.getLatitude());
        latLng.put("Longitude", location.getLongitude());

        //Return HashMap.
        return latLng;
    }

    //TODO: Think about what to do when the API can't be read from the source. This is happening now technically.
    //Function to read API Key from file.
    private String getAPIKeyFromFile() throws FileNotFoundException, IOException {
        //Open File, Read Key, Close File, Return Key.
        File file = new File(G_API_LOC);

        BufferedReader br = new BufferedReader(new FileReader(file));

        return br.readLine();

        /*
            What do I do when the API Key can't be retrieved? Should I have a backup key? Do I need to think about
            what the fallback plan is? Should I store it in multiple places?
         */
    }
}

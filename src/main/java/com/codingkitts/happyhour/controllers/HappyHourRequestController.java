package com.codingkitts.happyhour.controllers;

import com.codingkitts.happyhour.entities.HappyHourRequest;
import com.codingkitts.happyhour.services.HappyHourRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HappyHourRequestController {

    private static final Logger logger = LoggerFactory.getLogger(HappyHourRequestController.class);

    private final HappyHourRequestService happyHourRequestService;

    public HappyHourRequestController(HappyHourRequestService happyHourRequestService) {
        this.happyHourRequestService = happyHourRequestService;
    }

    //TODO: Create tests for data validation edge cases.

    @GetMapping("/requests")
    public List<HappyHourRequest> getAllHappyHourRequests() {
        return this.happyHourRequestService.getAllHappyHourRequests();
    }

    @GetMapping("/requests/read")
    public List<HappyHourRequest> getAllReadHappyHourRequests() {
        return this.happyHourRequestService.getAllHappyHourRequestsRead();
    }

    @GetMapping("/requests/unread")
    public List<HappyHourRequest> getAllUnreadHappyHourRequests() {
        return this.happyHourRequestService.getAllHappyHourRequestsNotRead();
    }

    @GetMapping("/request/{happyHourRequestId}")
    public ResponseEntity<HappyHourRequest> getHappyHourRequestById(@PathVariable Long happyHourRequestId) {
        Optional<HappyHourRequest> hhr = this.happyHourRequestService.getHappyHourRequestById(happyHourRequestId);

        return hhr.map(happyHour -> new ResponseEntity<>(happyHour, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/request/create")
    public ResponseEntity<HappyHourRequest> createHappyHourRequest(@RequestBody HappyHourRequest happyHourRequest) {

        if (happyHourRequest.hasBeenRead() || happyHourRequest.getHappyHourRequestId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.happyHourRequestService.createNewHappyHourRequest(happyHourRequest), HttpStatus.OK);
    }

    @PutMapping("/request/{happyHourRequestId}")
    public ResponseEntity<HappyHourRequest> readHappyHourRequest(@PathVariable Long happyHourRequestId) {
        return new ResponseEntity<>(this.happyHourRequestService.setHappyHourRequestAsRead(happyHourRequestId));
    }

    @DeleteMapping("/request/{happyHourRequestId}")
    public ResponseEntity<HappyHourRequest> deleteHappyHourRequestById(@PathVariable Long happyHourRequestId) {
        return new ResponseEntity<>(this.happyHourRequestService.deleteHappyHourRequestById(happyHourRequestId));
    }
}

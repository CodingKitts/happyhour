package com.codingkitts.happyhour.services;

import com.codingkitts.happyhour.entities.HappyHourRequest;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface HappyHourRequestService {

    List<HappyHourRequest> getAllHappyHourRequests();

    List<HappyHourRequest> getAllHappyHourRequestsNotRead();

    List<HappyHourRequest> getAllHappyHourRequestsRead();

    Optional<HappyHourRequest> getHappyHourRequestById(Long happyHourRequestId);

    HappyHourRequest createNewHappyHourRequest(HappyHourRequest happyHourRequest);

    HttpStatus setHappyHourRequestAsRead(Long happyHourRequestId);

    HttpStatus deleteHappyHourRequestById(Long happyHourRequestId);
}

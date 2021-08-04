package com.codingkitts.happyhour.services.impl;

import com.codingkitts.happyhour.entities.HappyHourRequest;
import com.codingkitts.happyhour.repos.HappyHourRequestRepository;
import com.codingkitts.happyhour.services.HappyHourRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HappyHourRequestServiceImpl implements HappyHourRequestService {
    private static final Logger logger = LoggerFactory.getLogger(HappyHourRequestServiceImpl.class);

    private final HappyHourRequestRepository happyHourRequestRepository;

    public HappyHourRequestServiceImpl(HappyHourRequestRepository happyHourRequestRepository) {
        this.happyHourRequestRepository = happyHourRequestRepository;
    }

    @Override
    public List<HappyHourRequest> getAllHappyHourRequests() {
        return this.happyHourRequestRepository.findAll();
    }

    @Override
    public List<HappyHourRequest> getAllHappyHourRequestsNotRead() {
        return this.happyHourRequestRepository.getHappyHourRequestsByHasBeenReadIsFalse();
    }

    @Override
    public List<HappyHourRequest> getAllHappyHourRequestsRead() {
        return this.happyHourRequestRepository.getHappyHourRequestsByHasBeenReadIsTrue();
    }

    @Override
    public Optional<HappyHourRequest> getHappyHourRequestById(Long happyHourRequestId) {
        return this.happyHourRequestRepository.findById(happyHourRequestId);
    }

    @Override
    public HappyHourRequest createNewHappyHourRequest(HappyHourRequest happyHourRequest) {
        return this.happyHourRequestRepository.save(happyHourRequest);
    }

    //The question is do we care about the whole object being returned when updating if its been read? No..
    @Override
    public HttpStatus setHappyHourRequestAsRead(Long happyHourRequestId) {
        if (this.happyHourRequestRepository.findById(happyHourRequestId).isEmpty()) {
            return HttpStatus.NOT_FOUND;
        } else {
            if (this.happyHourRequestRepository.readHappyHour(happyHourRequestId) > 0) {
                return HttpStatus.OK;
            } else {
                return HttpStatus.BAD_REQUEST;
            }
        }
    }

    @Override
    public HttpStatus deleteHappyHourRequestById(Long happyHourRequestId) {
        if (this.happyHourRequestRepository.findById(happyHourRequestId).isPresent()) {
            this.happyHourRequestRepository.deleteById(happyHourRequestId);
            if (this.happyHourRequestRepository.findById(happyHourRequestId).isPresent()) {
                return HttpStatus.BAD_REQUEST;
            } else {
                return HttpStatus.OK;
            }
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}

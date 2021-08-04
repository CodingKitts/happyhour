package com.codingkitts.happyhour.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class HappyHourRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long happyHourRequestId;

    @NotBlank
    @Size(max = 50)
    private String requestVenueName;

    @NotBlank
    @Size(max = 20)
    private String requestVenueCity;

    @NotBlank
    @Size(max = 2)
    private String requestVenueState;

    private boolean hasBeenRead = false;

    public HappyHourRequest() {}

    public Long getHappyHourRequestId() {
        return happyHourRequestId;
    }

    public void setHappyHourRequestId(Long happyHourRequestId) {
        this.happyHourRequestId = happyHourRequestId;
    }

    public String getRequestVenueName() {
        return requestVenueName;
    }

    public void setRequestVenueName(String requestVenueName) {
        this.requestVenueName = requestVenueName;
    }

    public String getRequestVenueCity() {
        return requestVenueCity;
    }

    public void setRequestVenueCity(String requestVenueCity) {
        this.requestVenueCity = requestVenueCity;
    }

    public String getRequestVenueState() {
        return requestVenueState;
    }

    public void setRequestVenueState(String requestVenueState) {
        this.requestVenueState = requestVenueState;
    }

    public boolean hasBeenRead() {
        return hasBeenRead;
    }

    public void setHasBeenRead(boolean hasBeenRead) {
        this.hasBeenRead = hasBeenRead;
    }
}

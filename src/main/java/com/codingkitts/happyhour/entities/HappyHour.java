package com.codingkitts.happyhour.entities;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "happy_hour")
public class HappyHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long happyHourId;

    //Variables that hold details about the venue that is putting a happy hour on
    @NotBlank
    @Size(max = 50)
    private String venueName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String venueAddress;

    @NotBlank
    @Size(min = 10, max = 50)
    private String venueWebsite;

    @NotBlank
    @Size(max = 240)
    private String venueDescription;

    //Variables to hold the lat/long coordinates of the venue street address.
    @DecimalMax("90.00000")
    @DecimalMin("-90.00000")
    @NotNull
    private Double venueLat;

    @DecimalMax("180.00000")
    @DecimalMin("-180.00000")
    @NotNull
    private Double venueLng;

    //Variables to hold the various specials throughout the week. For the MVP, these will include hours.
    @NotBlank
    @Size(min = 10, max = 50)
    private String monSpecial;

    @NotBlank
    @Size(min = 10, max = 50)
    private String tueSpecial;

    @NotBlank
    @Size(min = 10, max = 50)
    private String wedSpecial;

    @NotBlank
    @Size(min = 10, max = 50)
    private String thurSpecial;

    @NotBlank
    @Size(min = 10, max = 50)
    private String friSpecial;

    @NotBlank
    @Size(min = 10, max = 50)
    private String satSpecial;

    @NotBlank
    @Size(min = 10, max = 50)
    private String sunSpecial;

    /*//TODO: Incorporate these elements later, post-MVP. They arent necessary.
    private LocalTime happyHourStart;
    private LocalTime happyHourEnd;
    private String venueTimeZone;
    private Date lastUpdated;*/

    public HappyHour() {}

    public HappyHour(HappyHour happyHour) {
        this.happyHourId = happyHour.getHappyHourId();
        this.venueName = happyHour.venueName;
        this.venueAddress = happyHour.venueAddress;
        this.venueWebsite = happyHour.venueWebsite;
        this.venueDescription = happyHour.venueDescription;
        this.venueLat = happyHour.venueLat;
        this.venueLng = happyHour.venueLng;
        this.monSpecial = happyHour.monSpecial;
        this.tueSpecial = happyHour.tueSpecial;
        this.wedSpecial = happyHour.wedSpecial;
        this.thurSpecial = happyHour.thurSpecial;
        this.friSpecial = happyHour.friSpecial;
        this.satSpecial = happyHour.satSpecial;
        this.sunSpecial = happyHour.sunSpecial;
    }

    public HappyHour(Long happyHourId, String venueName, String venueAddress, String venueWebsite, String venueDescription, Double venueLat, Double venueLng, String monSpecial, String tueSpecial, String wedSpecial, String thurSpecial, String friSpecial, String satSpecial, String sunSpecial) {
        this.happyHourId = happyHourId;
        this.venueName = venueName;
        this.venueAddress = venueAddress;
        this.venueWebsite = venueWebsite;
        this.venueDescription = venueDescription;
        this.venueLat = venueLat;
        this.venueLng = venueLng;
        this.monSpecial = monSpecial;
        this.tueSpecial = tueSpecial;
        this.wedSpecial = wedSpecial;
        this.thurSpecial = thurSpecial;
        this.friSpecial = friSpecial;
        this.satSpecial = satSpecial;
        this.sunSpecial = sunSpecial;
    }

    public HappyHour(String venueName, String venueAddress, String venueWebsite, String venueDescription, Double venueLat, Double venueLng, String monSpecial, String tueSpecial, String wedSpecial, String thurSpecial, String friSpecial, String satSpecial, String sunSpecial) {
        this.venueName = venueName;
        this.venueAddress = venueAddress;
        this.venueWebsite = venueWebsite;
        this.venueDescription = venueDescription;
        this.venueLat = venueLat;
        this.venueLng = venueLng;
        this.monSpecial = monSpecial;
        this.tueSpecial = tueSpecial;
        this.wedSpecial = wedSpecial;
        this.thurSpecial = thurSpecial;
        this.friSpecial = friSpecial;
        this.satSpecial = satSpecial;
        this.sunSpecial = sunSpecial;
    }

    public Long getHappyHourId() {
        return happyHourId;
    }

    public void setHappyHourId(Long happyHourId) {
        this.happyHourId = happyHourId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public String getVenueWebsite() {
        return venueWebsite;
    }

    public void setVenueWebsite(String venueWebsite) {
        this.venueWebsite = venueWebsite;
    }

    public String getVenueDescription() {
        return venueDescription;
    }

    public void setVenueDescription(String venueDescription) {
        this.venueDescription = venueDescription;
    }

    public Double getVenueLat() {
        return venueLat;
    }

    public void setVenueLat(Double venueLat) {
        this.venueLat = venueLat;
    }

    public Double getVenueLng() {
        return venueLng;
    }

    public void setVenueLng(Double venueLong) {
        this.venueLng = venueLong;
    }

    public String getMonSpecial() {
        return monSpecial;
    }

    public void setMonSpecial(String monSpecial) {
        this.monSpecial = monSpecial;
    }

    public String getTueSpecial() {
        return tueSpecial;
    }

    public void setTueSpecial(String tueSpecial) {
        this.tueSpecial = tueSpecial;
    }

    public String getWedSpecial() {
        return wedSpecial;
    }

    public void setWedSpecial(String wedSpecial) {
        this.wedSpecial = wedSpecial;
    }

    public String getThurSpecial() {
        return thurSpecial;
    }

    public void setThurSpecial(String thurSpecial) {
        this.thurSpecial = thurSpecial;
    }

    public String getFriSpecial() {
        return friSpecial;
    }

    public void setFriSpecial(String friSpecial) {
        this.friSpecial = friSpecial;
    }

    public String getSatSpecial() {
        return satSpecial;
    }

    public void setSatSpecial(String satSpecial) {
        this.satSpecial = satSpecial;
    }

    public String getSunSpecial() {
        return sunSpecial;
    }

    public void setSunSpecial(String sunSpecial) {
        this.sunSpecial = sunSpecial;
    }

}

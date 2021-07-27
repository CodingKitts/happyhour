package com.codingkitts.happyhour.models.geocode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeViewport {

    @JsonProperty("northeast")
    GeocodeLocation northeast;

    @JsonProperty("southwest")
    GeocodeLocation southwest;

    public GeocodeViewport() {}

    public GeocodeLocation getNortheast() {
        return northeast;
    }

    public void setNortheast(GeocodeLocation northeast) {
        this.northeast = northeast;
    }

    public GeocodeLocation getSouthwest() {
        return southwest;
    }

    public void setSouthwest(GeocodeLocation southwest) {
        this.southwest = southwest;
    }
}

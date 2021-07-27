package com.codingkitts.happyhour.models.geocode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeGeometry {

    @JsonProperty("location")
    GeocodeLocation geocodeLocation;

    @JsonProperty("location_type")
    String locationType;

    @JsonProperty("viewport")
    GeocodeViewport viewport;

    @JsonProperty("bounds")
    GeocodeViewport bounds;

    public GeocodeGeometry() {}

    public GeocodeLocation getGeocodeLocation() {
        return geocodeLocation;
    }

    public void setGeocodeLocation(GeocodeLocation geocodeLocation) {
        this.geocodeLocation = geocodeLocation;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public GeocodeViewport getViewport() {
        return viewport;
    }

    public void setViewport(GeocodeViewport viewport) {
        this.viewport = viewport;
    }

    public GeocodeViewport getBounds() {
        return bounds;
    }

    public void setBounds(GeocodeViewport bounds) {
        this.bounds = bounds;
    }
}

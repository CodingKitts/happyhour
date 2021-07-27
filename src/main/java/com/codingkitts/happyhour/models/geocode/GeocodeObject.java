package com.codingkitts.happyhour.models.geocode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeObject {

    @JsonProperty("place_id")
    String placeId;

    @JsonProperty("address_components")
    List<AddressComponent> addressComponents;

    @JsonProperty("formatted_address")
    String formattedAddress;

    @JsonProperty("geometry")
    GeocodeGeometry geometry;

    @JsonProperty("plus_code")
    GeocodePluscode plusCode;

    @JsonProperty("types")
    List<String> types;

    public GeocodeObject() {}

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public GeocodeGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(GeocodeGeometry geometry) {
        this.geometry = geometry;
    }

    public GeocodePluscode getPlusCode() {
        return plusCode;
    }

    public void setPlusCode(GeocodePluscode plusCode) {
        this.plusCode = plusCode;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}

package com.codingkitts.happyhour.models.userInput;

public class UserInput {
    private String physicalAddress;
    private Integer radius;

    public UserInput() {}

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }
}

package com.hyperzsb.spacemanager.emuneration;

import javax.persistence.AttributeConverter;

public enum Availability {
    UNAVAILABLE(0, "不可用"),
    AVAILABLE(1, "可用");

    private int value;
    private String availability;

    Availability(int value, String availability) {
        this.value = value;
        this.availability = availability;
    }

    public static Availability getAvailabilityByValue(int value) {
        if (value == 1)
            return AVAILABLE;
        else
            return UNAVAILABLE;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}

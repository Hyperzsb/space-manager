package com.hyperzsb.spacemanager.borrowing.enumeration;

public enum Availability {
    UNAVAILABLE(0, "unavailable"),
    AVAILABLE(1, "available");

    private int value;
    private String info;

    Availability(int value, String info) {
        this.value = value;
        this.info = info;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

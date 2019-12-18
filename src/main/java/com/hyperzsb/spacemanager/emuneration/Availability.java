package com.hyperzsb.spacemanager.emuneration;

public enum Availability {
    UNAVAILABLE(0, "不可用"),
    AVAILABLE(1, "可用");

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

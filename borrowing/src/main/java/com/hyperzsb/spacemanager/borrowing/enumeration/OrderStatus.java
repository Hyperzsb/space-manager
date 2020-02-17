package com.hyperzsb.spacemanager.borrowing.enumeration;

public enum OrderStatus {
    REJECTED(0, "rejected"),
    RATIFIED(1, "ratified"),
    UNDETERMINED(2, "undetermined");

    private int value;
    private String status;

    OrderStatus(int value, String status) {
        this.value = value;
        this.status = status;
    }

    public static OrderStatus getOrderStatusByValue(int value) {
        for (OrderStatus orderStatus : OrderStatus.values())
            if (orderStatus.value == value)
                return orderStatus;
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
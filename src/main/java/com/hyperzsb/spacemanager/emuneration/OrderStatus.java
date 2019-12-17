package com.hyperzsb.spacemanager.emuneration;

import javax.persistence.AttributeConverter;

public enum OrderStatus {
    REJECTED(0, "未批准"),
    RATIFIED(1, "已批准"),
    UNDETERMINED(2, "未确定");

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

//    public static class Convert implements AttributeConverter<OrderStatus, Integer> {
//
//        @Override
//        public Integer convertToDatabaseColumn(OrderStatus orderStatus) {
//            return orderStatus.getValue();
//        }
//
//        @Override
//        public OrderStatus convertToEntityAttribute(Integer value) {
//            return OrderStatus.getOrderStatusByValue(value);
//        }
//    }
}

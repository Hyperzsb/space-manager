package com.hyperzsb.spacemanager.converter;

import com.hyperzsb.spacemanager.emuneration.OrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderStatus orderStatus) {
        return orderStatus.getValue();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer value) {
        return OrderStatus.getOrderStatusByValue(value);
    }
}

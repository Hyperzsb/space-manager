package com.hyperzsb.spacemanager.borrowing.converter;

import com.hyperzsb.spacemanager.borrowing.enumeration.OrderStatus;

import javax.persistence.AttributeConverter;

public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {

    public OrderStatusConverter() {}

    @Override
    public Integer convertToDatabaseColumn(OrderStatus orderStatus) {
        return orderStatus.getValue();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer value) {
        return OrderStatus.getOrderStatusByValue(value);
    }

}

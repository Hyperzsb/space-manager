package com.hyperzsb.spacemanager.borrowing.converter;

import com.hyperzsb.spacemanager.borrowing.enumeration.Availability;

import javax.persistence.AttributeConverter;

public class AvailabilityConverter implements AttributeConverter<Availability, Integer> {

    public AvailabilityConverter() {}

    @Override
    public Integer convertToDatabaseColumn(Availability availability) {
        return availability.getValue();
    }

    @Override
    public Availability convertToEntityAttribute(Integer value) {
        return Availability.getAvailabilityByValue(value);
    }

}

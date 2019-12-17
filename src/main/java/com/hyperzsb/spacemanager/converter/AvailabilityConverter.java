package com.hyperzsb.spacemanager.converter;

import com.hyperzsb.spacemanager.emuneration.Availability;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

public class AvailabilityConverter implements AttributeConverter<Availability, Integer> {
    public AvailabilityConverter() {
    }

    @Override
    public Integer convertToDatabaseColumn(Availability availability) {
        return availability.getValue();
    }

    @Override
    public Availability convertToEntityAttribute(Integer value) {
        return Availability.getAvailabilityByValue(value);
    }
}

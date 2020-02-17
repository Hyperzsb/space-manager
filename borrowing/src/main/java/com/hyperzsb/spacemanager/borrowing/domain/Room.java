package com.hyperzsb.spacemanager.borrowing.domain;

import com.hyperzsb.spacemanager.borrowing.converter.AvailabilityConverter;
import com.hyperzsb.spacemanager.borrowing.enumeration.Availability;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "room_name")
    private String name;

    @Column(name = "room_note")
    private String note;

    @Column(name = "room_availability")
    @Convert(converter = AvailabilityConverter.class)
    private Availability availability;

    public Room(String name, String note, int availabilityValue) {
        this.name = name;
        this.note = note;
        this.availability = Availability.getAvailabilityByValue(availabilityValue);
    }

    public Room(String name, int availabilityValue) {
        this.name = name;
        this.name = null;
        this.availability = Availability.getAvailabilityByValue(availabilityValue);
    }

    public Room(String name, String note) {
        this.name = name;
        this.note = note;
        this.availability = Availability.UNAVAILABLE;
    }

    public Room(String name) {
        this.name = name;
        this.note = null;
        this.availability = Availability.UNAVAILABLE;
    }

    public Room() {
        this.name = null;
        this.note = null;
        this.availability = Availability.UNAVAILABLE;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

}

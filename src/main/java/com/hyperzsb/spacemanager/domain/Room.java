package com.hyperzsb.spacemanager.domain;

import com.hyperzsb.spacemanager.converter.AvailabilityConverter;
import com.hyperzsb.spacemanager.emuneration.Availability;

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

    public Room(Integer id, String name, String note, int availabilityValue) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.availability = Availability.getAvailabilityByValue(availabilityValue);
    }

    public Room(Integer id, String name, int availabilityValue) {
        this.id = id;
        this.name = name;
        this.name = null;
        this.availability = Availability.getAvailabilityByValue(availabilityValue);
    }

    public Room() {
        this.id = 0;
        this.name = null;
        this.note = null;
        this.availability = Availability.UNAVAILABLE;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

package com.hyperzsb.spacemanager.borrowing.vo;

import com.hyperzsb.spacemanager.borrowing.domain.Room;
import com.hyperzsb.spacemanager.borrowing.enumeration.Availability;

public class RoomVo {

    private Integer id;
    private String name;
    private String note;
    private int availabilityValue;
    private String availabilityInfo;

    public static Room convertToPo(RoomVo roomVo) {
        Room room = new Room();
        room.setId(roomVo.getId());
        room.setName(roomVo.getName());
        room.setNote(roomVo.getNote());
        room.setAvailability(Availability.getAvailabilityByValue(roomVo.getAvailabilityValue()));
        return room;
    }

    public static RoomVo convertToVo(Room room) {
        RoomVo roomVo = new RoomVo();
        roomVo.setId(room.getId());
        roomVo.setName(room.getName());
        roomVo.setNote(room.getNote());
        roomVo.setAvailabilityValue(room.getAvailability().getValue());
        roomVo.setAvailabilityInfo(room.getAvailability().getInfo());
        return roomVo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public int getAvailabilityValue() {
        return availabilityValue;
    }

    public void setAvailabilityValue(int availabilityValue) {
        this.availabilityValue = availabilityValue;
    }

    public String getAvailabilityInfo() {
        return availabilityInfo;
    }

    public void setAvailabilityInfo(String availabilityInfo) {
        this.availabilityInfo = availabilityInfo;
    }

}

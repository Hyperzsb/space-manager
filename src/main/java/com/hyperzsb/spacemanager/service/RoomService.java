package com.hyperzsb.spacemanager.service;

import com.hyperzsb.spacemanager.domain.Room;

import java.util.List;

public interface RoomService {
    Room addRoom(Room room);

    Room addRoom(String name, String note, int availabilityValue) throws Exception;

    Room getRoomById(Integer id);

    Room getRoomByName(String name);

    List<Room> getRoomByNameLike(String name);

    List<Room> getAllRoom();

    Room updateRoomById(Integer id, String name, String note, int availabilityValue);

    Room updateRoomByName(String name, String note, int availabilityValue);

    Room removeRoomById(Integer id);

    Room removeRoomByName(String name);

    List<Room> removeAllRoom();
}

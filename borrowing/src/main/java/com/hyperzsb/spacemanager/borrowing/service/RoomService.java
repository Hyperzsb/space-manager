package com.hyperzsb.spacemanager.borrowing.service;

import com.hyperzsb.spacemanager.borrowing.domain.Room;

import java.util.List;

public interface RoomService {

    Room addRoom(Room room);

    List<Room> getRooms();

    Room getRoomById(Integer id);

    Room getRoomByName(String name);

    List<Room> getRoomsByNameLike(String name);

    List<Room> getRoomsByAvailabilityValue(Integer availabilityValue);

    Room updateRoomById(Integer id, String name, String note, int availabilityValue);

    Room updateRoomByName(String name, String note, int availabilityValue);

    Room updateRoomAvailabilityById(Integer id, int availabilityValue);

    Room updateRoomAvailabilityByName(String name, int availabilityValue);

    List<Room> removeRooms();

    Room removeRoomById(Integer id);

    Room removeRoomByName(String name);

}

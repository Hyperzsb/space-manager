package com.hyperzsb.spacemanager.borrowing.service;

import com.hyperzsb.spacemanager.borrowing.domain.Room;
import com.hyperzsb.spacemanager.borrowing.enumeration.Availability;
import com.hyperzsb.spacemanager.borrowing.exception.RoomDaoException;
import com.hyperzsb.spacemanager.borrowing.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public Room addRoom(Room room) {
        if (roomRepository.findByName(room.getName()) != null)
            throw new RoomDaoException("Room existed");
        return roomRepository.save(room);
    }

    @Override
    @Transactional(readOnly = true)
    public Room getRoomById(Integer id) {
        if (roomRepository.findById(id).isPresent())
            return roomRepository.findById(id).get();
        else
            return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Room getRoomByName(String name) {
        return roomRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Room> getRoomByNameLike(String alikeName) {
        return roomRepository.findAllByNameLike(alikeName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Room updateRoomById(Integer id, String name, String note, int availabilityValue) {
        Room room;
        if (roomRepository.findById(id).isPresent()) {
            room = roomRepository.findById(id).get();
            room.setName(name);
            room.setNote(note);
            room.setAvailability(Availability.getAvailabilityByValue(availabilityValue));
            return roomRepository.save(room);
        } else {
            throw new RoomDaoException("No such room to update");
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Room updateRoomByName(String name, String note, int availabilityValue) {
        Room room;
        if (roomRepository.findByName(name) != null) {
            room = roomRepository.findByName(name);
            room.setName(name);
            room.setNote(note);
            room.setAvailability(Availability.getAvailabilityByValue(availabilityValue));
            return roomRepository.save(room);
        } else {
            throw new RoomDaoException("No such room to update");
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Room removeRoomById(Integer id) {
        Room room;
        if (roomRepository.findById(id).isPresent()) {
            room = roomRepository.findById(id).get();
            roomRepository.deleteById(id);
        } else {
            throw new RoomDaoException("No such room to delete");
        }
        return room;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Room removeRoomByName(String name) {
        Room room;
        if ((room = roomRepository.findByName(name)) != null) {
            roomRepository.deleteById(room.getId());
        } else {
            throw new RoomDaoException("No such room to delete");
        }
        return room;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Room> removeAllRoom() {
        List<Room> roomList = roomRepository.findAll();
        for (Room room : roomList)
            roomRepository.deleteById(room.getId());
        return roomList;
    }

}
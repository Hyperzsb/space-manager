package com.hyperzsb.spacemanager.borrowing.repository;

import com.hyperzsb.spacemanager.borrowing.domain.Room;
import com.hyperzsb.spacemanager.borrowing.enumeration.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Room findByName(String name);

    List<Room> findAllByNameLike(String alikeName);

    List<Room> findAllByAvailability(Availability availability);
}

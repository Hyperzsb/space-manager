package com.hyperzsb.spacemanager.repository;

import com.hyperzsb.spacemanager.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Room findByName(String name);

    List<Room> findAllByNameLike(String alikeName);
}

package com.hyperzsb.spacemanager.repository;

import com.hyperzsb.spacemanager.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}

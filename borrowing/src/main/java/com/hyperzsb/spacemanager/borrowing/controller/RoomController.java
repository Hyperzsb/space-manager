package com.hyperzsb.spacemanager.borrowing.controller;

import com.hyperzsb.spacemanager.borrowing.domain.Room;
import com.hyperzsb.spacemanager.borrowing.service.RoomService;
import com.hyperzsb.spacemanager.borrowing.vo.RoomVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private static Logger logger = LogManager.getLogger(RoomController.class);
    @Autowired
    private RoomService roomService;

    @GetMapping("/")
    public List<RoomVo> getRooms() {
        List<Room> roomList = roomService.getRooms();
        List<RoomVo> roomVoList = new ArrayList<RoomVo>();
        for (Room room : roomList) {
            roomVoList.add(RoomVo.convertToVo(room));
        }
        return roomVoList;
    }

    @GetMapping("/id/{id}")
    public RoomVo getRoomById(@PathVariable("id") Integer id) {
        Room room = roomService.getRoomById(id);
        return RoomVo.convertToVo(room);
    }

    @GetMapping("/name/{name}")
    public RoomVo getRoomByName(@PathVariable("name") String name) {
        Room room = roomService.getRoomByName(name);
        return RoomVo.convertToVo(room);
    }

    @GetMapping("/nameLike/{alikeName}")
    public List<RoomVo> getRoomsByNameLike(@PathVariable("alikeName") String alikeName) {
        List<Room> roomList = roomService.getRoomsByNameLike(alikeName);
        List<RoomVo> roomVoList = new ArrayList<RoomVo>();
        for (Room room : roomList) {
            roomVoList.add(RoomVo.convertToVo(room));
        }
        return roomVoList;
    }

    @GetMapping("/availability/{availabilityValue}")
    public List<RoomVo> getRoomsByAvailabilityValue(@PathVariable("availabilityValue") Integer availabilityValue) {
        List<Room> roomList = roomService.getRoomsByAvailabilityValue(availabilityValue);
        List<RoomVo> roomVoList = new ArrayList<RoomVo>();
        for (Room room : roomList) {
            roomVoList.add(RoomVo.convertToVo(room));
        }
        return roomVoList;
    }

}
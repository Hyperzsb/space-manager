package com.hyperzsb.spacemanager.controller;

import com.hyperzsb.spacemanager.domain.Room;
import com.hyperzsb.spacemanager.service.RoomService;
import com.hyperzsb.spacemanager.vo.RoomVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    private static Logger logger = LogManager.getLogger(RoomController.class);
    @Autowired
    private RoomService roomService;

    @PostMapping("/")
    public ResponseEntity<RoomVo> addRoom(@RequestBody RoomVo roomVo) {
        try {
            Room room = roomService.addRoom(RoomVo.convertToPo(roomVo));
            RoomVo resultRoomVo = RoomVo.convertToVo(room);
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "Y";
            httpHeaders.add("success", success);
            return new ResponseEntity<RoomVo>(resultRoomVo, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            String fail = "N";
            httpHeaders.add("success", fail);
            return new ResponseEntity<RoomVo>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    @ResponseBody
    public List<RoomVo> getRoom() {
        List<Room> roomList = roomService.getAllRoom();
        List<RoomVo> roomVoList = new ArrayList<RoomVo>();
        for (Room room : roomList) {
            roomVoList.add(RoomVo.convertToVo(room));
        }
        return roomVoList;
    }

    @GetMapping("/{name}")
    @ResponseBody
    public RoomVo getRoom(@PathVariable("name") String name) {
        Room room = roomService.getRoomByName(name);
        return RoomVo.convertToVo(room);
    }

    @PutMapping("/")
    @ResponseBody
    public RoomVo updateRoom(@RequestBody RoomVo roomVo) {
        Room room = RoomVo.convertToPo(roomVo);
        roomService.updateRoomByName(room.getName(), room.getNote(), room.getAvailability().getValue());
        return roomVo;
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    public RoomVo deleteRoom(@PathVariable("name") String name) {
        Room room = roomService.removeRoomByName(name);
        return RoomVo.convertToVo(room);
    }

}

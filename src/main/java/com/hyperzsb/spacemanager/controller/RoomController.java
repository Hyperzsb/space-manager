package com.hyperzsb.spacemanager.controller;

import com.hyperzsb.spacemanager.domain.Room;
import com.hyperzsb.spacemanager.service.RoomService;
import com.hyperzsb.spacemanager.vo.RoomVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

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
            Room room = roomService.addRoom(RoomVo.changeToPo(roomVo));
            RoomVo resultRoomVo = RoomVo.changeToVo(room);
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "Succeeded!";
            httpHeaders.add("success", success);
            return new ResponseEntity<RoomVo>(resultRoomVo, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            String fail = "Failed!";
            httpHeaders.add("success", fail);
            return new ResponseEntity<RoomVo>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public RoomVo getRoom(@PathVariable("id") Integer id) {
        Room room = roomService.getRoomById(id);
        return RoomVo.changeToVo(room);
    }

    @GetMapping("/{name}")
    @ResponseBody
    public RoomVo getRoom(@PathVariable("name") String name) {
        Room room = roomService.getRoomByName(name);
        return RoomVo.changeToVo(room);
    }

    @GetMapping("/")
    @ResponseBody
    public List<Room> getRoom() {
        return roomService.getAllRoom();
    }


}

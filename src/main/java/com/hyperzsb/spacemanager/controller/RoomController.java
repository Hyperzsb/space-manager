package com.hyperzsb.spacemanager.controller;

import com.hyperzsb.spacemanager.domain.Room;
import com.hyperzsb.spacemanager.service.RoomService;
import com.hyperzsb.spacemanager.vo.RoomVo;
import com.hyperzsb.spacemanager.vo.TestVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/room")
public class RoomController {
    private static Logger logger = LogManager.getLogger(RoomController.class);
    @Autowired
    private RoomService roomService;

//    @RequestMapping("/add")
//    @ResponseBody
//    public Room addRoom(@RequestParam(name = "name", required = true) String name,
//                        @RequestParam(name = "note", required = false) String note,
//                        @RequestParam(name = "avaValue", required = true) int availabilityValue) {
//        Room room = null;
//        try {
//            room = roomService.addRoom(name, note, availabilityValue);
//            logger.info("Room added successfully.");
//        } catch (Exception e) {
//            if (e instanceof RoomDaoException) {
//                logger.info("Room already existed.");
//            } else {
//                logger.info("Unexpected exception occurred.");
//            }
//        }
//        return room;
//    }

    @PostMapping("/")
    @ResponseBody
    public Room addRoom(@RequestBody TestVo testVo) {
        Room room = new Room("Test", "A Room", 1);
        room.setId(1);
        return room;
//        return roomService.addRoom(RoomVo.changeToPo(roomVo));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public RoomVo getRoom(@PathVariable("id") Integer id) {
        Room room = roomService.getRoomById(id);
        return RoomVo.changeToVo(room);
    }


}

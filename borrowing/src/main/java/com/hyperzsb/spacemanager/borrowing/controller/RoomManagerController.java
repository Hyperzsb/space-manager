package com.hyperzsb.spacemanager.borrowing.controller;

import com.hyperzsb.spacemanager.borrowing.domain.Room;
import com.hyperzsb.spacemanager.borrowing.exception.RoomDaoException;
import com.hyperzsb.spacemanager.borrowing.service.RoomService;
import com.hyperzsb.spacemanager.borrowing.vo.RoomVo;
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
@RequestMapping("/manager/room")
public class RoomManagerController {

    private static Logger logger = LogManager.getLogger(RoomController.class);
    @Autowired
    private RoomService roomService;

    private ResponseEntity<RoomVo> exceptionHandlerSingle(Exception e) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String fail;
        if (e instanceof RoomDaoException)
            fail = "N";
        else
            fail = "U";
        httpHeaders.add("success", fail);
        return new ResponseEntity<RoomVo>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<List<RoomVo>> exceptionHandlerMulti(Exception e) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String fail;
        if (e instanceof RoomDaoException)
            fail = "N";
        else
            fail = "U";
        httpHeaders.add("success", fail);
        return new ResponseEntity<List<RoomVo>>(new ArrayList<RoomVo>(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);

    }

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
            return exceptionHandlerSingle(e);
        }
    }

    @PutMapping("/")
    public ResponseEntity<RoomVo> updateRoom(@RequestBody RoomVo roomVo) {
        try {
            Room room = RoomVo.convertToPo(roomVo);
            roomService.updateRoomByName(room.getName(), room.getNote(), room.getAvailability().getValue());
            RoomVo resultRoomVo = RoomVo.convertToVo(room);
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "Y";
            httpHeaders.add("success", success);
            return new ResponseEntity<RoomVo>(resultRoomVo, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandlerSingle(e);
        }
    }

    @PutMapping("/id/{id}/availabilityValue/{availabilityValue}")
    public ResponseEntity<RoomVo> updateRoomAvailabilityById(@PathVariable("id") Integer id,
                                                             @PathVariable("availabilityValue") Integer availabilityValue) {
        try {
            Room room = roomService.updateRoomAvailabilityById(id, availabilityValue);
            RoomVo resultRoomVo = RoomVo.convertToVo(room);
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "Y";
            httpHeaders.add("success", success);
            return new ResponseEntity<RoomVo>(resultRoomVo, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandlerSingle(e);
        }
    }

    @PutMapping("/name/{name}/availabilityValue/{availabilityValue}")
    public ResponseEntity<RoomVo> updateRoomAvailabilityByName(@PathVariable("name") String name,
                                                               @PathVariable("availabilityValue") Integer availabilityValue) {
        try {
            Room room = roomService.updateRoomAvailabilityByName(name, availabilityValue);
            RoomVo resultRoomVo = RoomVo.convertToVo(room);
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "Y";
            httpHeaders.add("success", success);
            return new ResponseEntity<RoomVo>(resultRoomVo, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandlerSingle(e);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<List<RoomVo>> deleteRooms() {
        try {
            List<Room> roomList = roomService.removeRooms();
            List<RoomVo> resultRoomVoList = new ArrayList<>();
            for (Room room : roomList)
                resultRoomVoList.add(RoomVo.convertToVo(room));
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "Y";
            httpHeaders.add("success", success);
            return new ResponseEntity<List<RoomVo>>(resultRoomVoList, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandlerMulti(e);
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<RoomVo> deleteRoomById(@PathVariable("id") Integer id) {
        try {
            Room room = roomService.removeRoomById(id);
            RoomVo resultRoomVo = RoomVo.convertToVo(room);
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "Y";
            httpHeaders.add("success", success);
            return new ResponseEntity<RoomVo>(resultRoomVo, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandlerSingle(e);
        }
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<RoomVo> deleteRoomByName(@PathVariable("name") String name) {
        try {
            Room room = roomService.removeRoomByName(name);
            RoomVo resultRoomVo = RoomVo.convertToVo(room);
            HttpHeaders httpHeaders = new HttpHeaders();
            String success = "Y";
            httpHeaders.add("success", success);
            return new ResponseEntity<RoomVo>(resultRoomVo, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandlerSingle(e);
        }
    }
}

package com.hyperzsb.spacemanager.vo;

import com.hyperzsb.spacemanager.domain.Academy;
import com.hyperzsb.spacemanager.domain.Borrower;
import com.hyperzsb.spacemanager.domain.BorrowingOrder;
import com.hyperzsb.spacemanager.domain.Room;

import java.sql.Timestamp;
import java.util.Date;

public class BorrowingOrderVo {
    private String roomName;
    private Integer borrowerId;
    private String borrowerName;
    private String borrowerAcademyName;
    private String note;
    private String time;
    private String startTime;
    private String endTime;

    public BorrowingOrderVo(String roomName, Integer borrowerId, String borrowerName, String borrowerAcademyName,
                            String time, String startTime, String endTime) {
        this.roomName = roomName;
        this.borrowerId = borrowerId;
        this.borrowerName = borrowerName;
        this.borrowerAcademyName = borrowerAcademyName;
        this.time = time;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static BorrowingOrder convertToPo(BorrowingOrderVo borrowingOrderVo) {
        Borrower borrower = new Borrower();
        borrower.setId(borrowingOrderVo.getBorrowerId());
        borrower.setName(borrowingOrderVo.getBorrowerName());
        Academy academy = new Academy(borrowingOrderVo.getBorrowerAcademyName());
        borrower.setAcademy(academy);
        Room room = new Room(borrowingOrderVo.getRoomName());
        BorrowingOrder borrowingOrder = new BorrowingOrder();
        borrowingOrder.setBorrower(borrower);
        borrowingOrder.setRoom(room);
        borrowingOrder.setNote(borrowingOrderVo.getNote());
        borrowingOrder.setTime(Timestamp.valueOf(borrowingOrderVo.getTime()));
        borrowingOrder.setStartTime(Timestamp.valueOf(borrowingOrderVo.getStartTime()));
        borrowingOrder.setEndTime(Timestamp.valueOf(borrowingOrderVo.getEndTime()));
        return borrowingOrder;
    }

    public static BorrowingOrderVo convertToVo(BorrowingOrder borrowingOrder) {
        return null;
    }

    public BorrowingOrderVo() {
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerAcademyName() {
        return borrowerAcademyName;
    }

    public void setBorrowerAcademyName(String borrowerAcademyName) {
        this.borrowerAcademyName = borrowerAcademyName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

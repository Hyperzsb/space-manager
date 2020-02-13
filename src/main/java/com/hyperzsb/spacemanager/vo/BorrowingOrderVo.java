package com.hyperzsb.spacemanager.vo;

import com.hyperzsb.spacemanager.domain.Academy;
import com.hyperzsb.spacemanager.domain.Borrower;
import com.hyperzsb.spacemanager.domain.BorrowingOrder;
import com.hyperzsb.spacemanager.domain.Room;
import com.hyperzsb.spacemanager.emuneration.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;

public class BorrowingOrderVo {
    private static Logger logger = LogManager.getLogger(BorrowingOrderVo.class);
    private Integer orderId;
    private String roomName;
    private Integer borrowerId;
    private String borrowerName;
    private String borrowerAcademyName;
    private String note;
    private String time;
    private String startTime;
    private String endTime;
    private Integer orderStatus;

    public BorrowingOrderVo(Integer orderId, String roomName, Integer borrowerId, String borrowerName, String borrowerAcademyName,
                            String time, String startTime, String endTime, Integer orderStatus) {
        this.orderId = orderId;
        this.roomName = roomName;
        this.borrowerId = borrowerId;
        this.borrowerName = borrowerName;
        this.borrowerAcademyName = borrowerAcademyName;
        this.time = time;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderStatus = orderStatus;
    }

    public BorrowingOrderVo() {
    }

    public static BorrowingOrder convertToPo(BorrowingOrderVo borrowingOrderVo) {
        logger.info(borrowingOrderVo.toString());
        Borrower borrower = new Borrower();
        borrower.setId(borrowingOrderVo.getBorrowerId());
        borrower.setName(borrowingOrderVo.getBorrowerName());
        Academy academy = new Academy(borrowingOrderVo.getBorrowerAcademyName());
        borrower.setAcademy(academy);
        Room room = new Room(borrowingOrderVo.getRoomName());
        BorrowingOrder borrowingOrder = new BorrowingOrder();
        borrowingOrder.setId(borrowingOrderVo.getOrderId());
        borrowingOrder.setBorrower(borrower);
        borrowingOrder.setRoom(room);
        borrowingOrder.setNote(borrowingOrderVo.getNote());
        logger.info(borrowingOrderVo.getTime());
        borrowingOrder.setTime(Timestamp.valueOf(borrowingOrderVo.getTime()));
        borrowingOrder.setStartTime(Timestamp.valueOf(borrowingOrderVo.getStartTime()));
        borrowingOrder.setEndTime(Timestamp.valueOf(borrowingOrderVo.getEndTime()));
        return borrowingOrder;
    }

    public static BorrowingOrderVo convertToVo(BorrowingOrder borrowingOrder) {
        BorrowingOrderVo borrowingOrderVo = new BorrowingOrderVo();
        borrowingOrderVo.setOrderId(borrowingOrder.getId());
        borrowingOrderVo.setBorrowerId(borrowingOrder.getBorrower().getId());
        borrowingOrderVo.setBorrowerName(borrowingOrder.getBorrower().getName());
        borrowingOrderVo.setBorrowerAcademyName(borrowingOrder.getBorrower().getAcademy().getName());
        borrowingOrderVo.setRoomName(borrowingOrder.getRoom().getName());
        borrowingOrderVo.setNote(borrowingOrder.getNote());
        borrowingOrderVo.setTime(borrowingOrder.getTime().toString());
        borrowingOrderVo.setStartTime(borrowingOrder.getStartTime().toString());
        borrowingOrderVo.setEndTime(borrowingOrder.getEndTime().toString());
        borrowingOrderVo.setOrderStatus(borrowingOrder.getOrderStatus().getValue());
        return borrowingOrderVo;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "{" +
                "\"orderId\": " + orderId +
                ", \"roomName\": \"" + roomName + '\"' +
                ", \"borrowerId\": " + borrowerId +
                ", \"borrowerName\": \"" + borrowerName + '\"' +
                ", \"borrowerAcademyName\": \"" + borrowerAcademyName + '\"' +
                ", \"note\": \"" + note + '\"' +
                ", \"time\": \"" + time + '\"' +
                ", \"startTime\": \"" + startTime + '\"' +
                ", \"endTime\": \"" + endTime + '\"' +
                ", \"orderStatus\": " + orderStatus +
                "}";
    }
}

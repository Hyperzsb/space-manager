package com.hyperzsb.spacemanager.borrowing.domain;

import com.hyperzsb.spacemanager.borrowing.converter.OrderStatusConverter;
import com.hyperzsb.spacemanager.borrowing.enumeration.OrderStatus;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "borrowing_order")
public class BorrowingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "borrower_id", referencedColumnName = "id")
    private Borrower borrower;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Column(name = "order_note")
    private String note;

    @Column(name = "order_time")
    private Timestamp time;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "order_status")
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus orderStatus;

    public BorrowingOrder(Borrower borrower, Room room, String note, Timestamp time, Timestamp startTime, Timestamp endTime, OrderStatus orderStatus) {
        this.borrower = borrower;
        this.room = room;
        this.note = note;
        this.time = time;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderStatus = orderStatus;
    }

    public BorrowingOrder(Borrower borrower, Room room, String note, Timestamp time, Timestamp startTime, Timestamp endTime) {
        this.borrower = borrower;
        this.room = room;
        this.note = note;
        this.time = time;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderStatus = OrderStatus.UNDETERMINED;
    }

    public BorrowingOrder() {
        this.borrower = null;
        this.room = null;
        this.note = null;
        this.time = null;
        this.startTime = null;
        this.endTime = null;
        this.orderStatus = OrderStatus.UNDETERMINED;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

}

package com.hyperzsb.spacemanager.service;

import com.hyperzsb.spacemanager.domain.BorrowingOrder;

import java.util.List;

public interface BorrowingOrderService {
    BorrowingOrder addOrder(BorrowingOrder borrowingOrder);

    BorrowingOrder getOrderByOrderId(Integer id);

    List<BorrowingOrder> getOrderByBorrowerId(Integer id);

    List<BorrowingOrder> getOrderByBorrowerName(String name);

    List<BorrowingOrder> getOrderByRoomId(Integer id);

    List<BorrowingOrder> getOrderByRoomName(String name);

    BorrowingOrder updateOrderByOrderId(Integer id, BorrowingOrder borrowingOrder);

    BorrowingOrder deleteOrderByOrderId(Integer id);
}

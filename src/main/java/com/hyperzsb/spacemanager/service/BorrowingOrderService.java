package com.hyperzsb.spacemanager.service;

import com.hyperzsb.spacemanager.domain.BorrowingOrder;
import com.hyperzsb.spacemanager.exception.BorrowingOrderDaoException;

import java.util.List;

public interface BorrowingOrderService {
    BorrowingOrder addOrder(BorrowingOrder borrowingOrder) throws BorrowingOrderDaoException;

    BorrowingOrder getOrderByOrderId(Integer id) throws BorrowingOrderDaoException;

    List<BorrowingOrder> getOrderByBorrowerId(Integer id);

    List<BorrowingOrder> getOrderByBorrowerName(String name);

    List<BorrowingOrder> getOrderByRoomId(Integer id);

    List<BorrowingOrder> getOrderByRoomName(String name);

    BorrowingOrder updateOrderByOrderId(Integer id, BorrowingOrder borrowingOrder) throws BorrowingOrderDaoException;

    BorrowingOrder deleteOrderByOrderId(Integer id) throws BorrowingOrderDaoException;
}

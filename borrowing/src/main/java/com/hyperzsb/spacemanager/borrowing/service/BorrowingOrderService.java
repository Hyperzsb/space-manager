package com.hyperzsb.spacemanager.borrowing.service;

import com.hyperzsb.spacemanager.borrowing.domain.BorrowingOrder;
import com.hyperzsb.spacemanager.borrowing.exception.BorrowingOrderDaoException;

import java.util.List;

public interface BorrowingOrderService {
    BorrowingOrder addOrder(BorrowingOrder borrowingOrder) throws BorrowingOrderDaoException;

    List<BorrowingOrder> getOrder();

    BorrowingOrder getOrderByOrderId(Integer id) throws BorrowingOrderDaoException;

    List<BorrowingOrder> getOrderByBorrowerId(Integer id);

    List<BorrowingOrder> getOrderByBorrowerName(String name);

    List<BorrowingOrder> getOrderByRoomId(Integer id);

    List<BorrowingOrder> getOrderByRoomName(String name);

    BorrowingOrder updateOrderByOrderId(Integer id, BorrowingOrder borrowingOrder) throws BorrowingOrderDaoException;

    BorrowingOrder deleteOrderByOrderId(Integer id) throws BorrowingOrderDaoException;
}

package com.hyperzsb.spacemanager.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.hyperzsb.spacemanager.domain.BorrowingOrder;
import com.hyperzsb.spacemanager.repository.BorrowerRepository;
import com.hyperzsb.spacemanager.repository.BorrowingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BorrowingOrderServiceImpl implements BorrowingOrderService {
    @Autowired
    private BorrowingOrderRepository borrowingOrderRepository;
    @Autowired
    private BorrowerRepository borrowerRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public BorrowingOrder addOrder(BorrowingOrder borrowingOrder) {
        borrowerRepository.save(borrowingOrder.getBorrower());
        borrowingOrderRepository.save(borrowingOrder);
        return borrowingOrder;
    }

    @Override
    @Transactional(readOnly = true)
    public BorrowingOrder getOrderByOrderId(Integer id) {
        return borrowingOrderRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BorrowingOrder> getOrderByBorrowerId(Integer id) {
        return borrowingOrderRepository.findBorrowingOrdersByBorrowerId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BorrowingOrder> getOrderByBorrowerName(String name) {
        return borrowingOrderRepository.findBorrowingOrdersByBorrowerName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BorrowingOrder> getOrderByRoomId(Integer id) {
        return borrowingOrderRepository.findBorrowingOrdersByRoomId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BorrowingOrder> getOrderByRoomName(String name) {
        return borrowingOrderRepository.findBorrowingOrdersByRoomName(name);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public BorrowingOrder updateOrderByOrderId(Integer id, BorrowingOrder newBorrowingOrder) {
        BorrowingOrder oldBorrowingOrder = borrowingOrderRepository.findById(id).get();
        newBorrowingOrder.setId(oldBorrowingOrder.getId());
        borrowingOrderRepository.save(newBorrowingOrder);
        return newBorrowingOrder;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public BorrowingOrder deleteOrderByOrderId(Integer id) {
        BorrowingOrder borrowingOrder = borrowingOrderRepository.findById(id).get();
        borrowingOrderRepository.deleteById(id);
        return borrowingOrder;
    }
}

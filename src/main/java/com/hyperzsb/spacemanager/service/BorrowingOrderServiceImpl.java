package com.hyperzsb.spacemanager.service;

import com.hyperzsb.spacemanager.domain.Academy;
import com.hyperzsb.spacemanager.domain.Borrower;
import com.hyperzsb.spacemanager.domain.BorrowingOrder;
import com.hyperzsb.spacemanager.domain.Room;
import com.hyperzsb.spacemanager.exception.BorrowingOrderConflictException;
import com.hyperzsb.spacemanager.exception.BorrowingOrderDaoException;
import com.hyperzsb.spacemanager.repository.AcademyRepository;
import com.hyperzsb.spacemanager.repository.BorrowerRepository;
import com.hyperzsb.spacemanager.repository.BorrowingOrderRepository;
import com.hyperzsb.spacemanager.repository.RoomRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowingOrderServiceImpl implements BorrowingOrderService {
    private Logger logger = LogManager.getLogger(BorrowingOrderServiceImpl.class);
    @Autowired
    private BorrowingOrderRepository borrowingOrderRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BorrowerRepository borrowerRepository;
    @Autowired
    private AcademyRepository academyRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public BorrowingOrder addOrder(BorrowingOrder borrowingOrder) throws BorrowingOrderDaoException {
        try {
            Academy academy = academyRepository.findAcademyByName(borrowingOrder.getBorrower().getAcademy().getName());
            Borrower borrower = new Borrower(borrowingOrder.getBorrower().getId(),
                    borrowingOrder.getBorrower().getName(), academy);
            borrowingOrder.setBorrower(borrower);
            Room room = roomRepository.findByName(borrowingOrder.getRoom().getName());
            borrowingOrder.setRoom(room);
            List<BorrowingOrder> borrowingOrderList =
                    borrowingOrderRepository.findBorrowingOrdersByRoomName(borrowingOrder.getRoom().getName());
            for (BorrowingOrder bO : borrowingOrderList) {
                if ((bO.getStartTime().compareTo(borrowingOrder.getEndTime()) < 0
                        && bO.getEndTime().compareTo(borrowingOrder.getStartTime()) > 0)
                        || (bO.getEndTime().compareTo(borrowingOrder.getStartTime()) > 0
                        && bO.getStartTime().compareTo(borrowingOrder.getEndTime()) < 0)) {
                    throw new BorrowingOrderConflictException(bO);
                }
            }
            borrowerRepository.save(borrower);
            borrowingOrderRepository.save(borrowingOrder);
            return borrowingOrder;
        } catch (Exception e) {
            if (e instanceof BorrowingOrderConflictException) {
                throw e;
            } else {
                throw new BorrowingOrderDaoException("Unable to add new order");
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BorrowingOrder> getOrder() {
        return borrowingOrderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BorrowingOrder getOrderByOrderId(Integer id) throws BorrowingOrderDaoException {
        Optional<BorrowingOrder> borrowingOrder = borrowingOrderRepository.findById(id);
        if (borrowingOrder.isPresent())
            return borrowingOrder.get();
        else
            throw new BorrowingOrderDaoException("No such order");
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
    public BorrowingOrder updateOrderByOrderId(Integer id, BorrowingOrder newBorrowingOrder) throws BorrowingOrderDaoException {
        Optional<BorrowingOrder> oldBorrowingOrder = borrowingOrderRepository.findById(id);
        if (oldBorrowingOrder.isPresent()) {
            Academy academy = academyRepository.findAcademyByName(newBorrowingOrder.getBorrower().getAcademy().getName());
            Borrower borrower = new Borrower(newBorrowingOrder.getBorrower().getId(),
                    newBorrowingOrder.getBorrower().getName(), academy);
            newBorrowingOrder.setBorrower(borrower);
            Room room = roomRepository.findByName(newBorrowingOrder.getRoom().getName());
            newBorrowingOrder.setRoom(room);
            List<BorrowingOrder> borrowingOrderList =
                    borrowingOrderRepository.findBorrowingOrdersByRoomName(newBorrowingOrder.getRoom().getName());
            for (BorrowingOrder bO : borrowingOrderList) {
                if (bO.getStartTime().compareTo(newBorrowingOrder.getEndTime()) < 0
                        || bO.getEndTime().compareTo(newBorrowingOrder.getStartTime()) > 0) {
                    throw new BorrowingOrderConflictException(bO);
                }
            }
            if (!oldBorrowingOrder.get().getBorrower().getId().equals(borrower.getId()) ||
                    !oldBorrowingOrder.get().getBorrower().getName().equals(borrower.getName()) ||
                    !oldBorrowingOrder.get().getBorrower().getAcademy().getId().equals(borrower.getAcademy().getId())) {
                borrowerRepository.deleteById(oldBorrowingOrder.get().getBorrower().getId());
                borrowerRepository.save(borrower);
            }
            newBorrowingOrder.setId(oldBorrowingOrder.get().getId());
            borrowingOrderRepository.save(newBorrowingOrder);
            return newBorrowingOrder;
        } else {
            throw new BorrowingOrderDaoException("No such order");
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public BorrowingOrder deleteOrderByOrderId(Integer id) throws BorrowingOrderDaoException {
        Optional<BorrowingOrder> borrowingOrder = borrowingOrderRepository.findById(id);
        if (borrowingOrder.isPresent()) {
            borrowingOrderRepository.deleteById(id);
            return borrowingOrder.get();
        } else {
            throw new BorrowingOrderDaoException("No such order");
        }
    }
}

package com.hyperzsb.spacemanager.repository;

import com.hyperzsb.spacemanager.domain.BorrowingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingOrderRepository extends JpaRepository<BorrowingOrder, Integer> {
    List<BorrowingOrder> findBorrowingOrdersByBorrowerId(Integer id);

    List<BorrowingOrder> findBorrowingOrdersByBorrowerName(String name);

    List<BorrowingOrder> findBorrowingOrdersByRoomId(Integer id);

    List<BorrowingOrder> findBorrowingOrdersByRoomName(String name);
}

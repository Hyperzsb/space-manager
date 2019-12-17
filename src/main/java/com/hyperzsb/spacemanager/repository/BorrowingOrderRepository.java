package com.hyperzsb.spacemanager.repository;

import com.hyperzsb.spacemanager.domain.BorrowingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingOrderRepository extends JpaRepository<BorrowingOrder, Integer> {
}

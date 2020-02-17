package com.hyperzsb.spacemanager.borrowing.repository;

import com.hyperzsb.spacemanager.borrowing.domain.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower,Integer> {
}
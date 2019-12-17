package com.hyperzsb.spacemanager.repository;

import com.hyperzsb.spacemanager.domain.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<Borrower,Integer> {
}

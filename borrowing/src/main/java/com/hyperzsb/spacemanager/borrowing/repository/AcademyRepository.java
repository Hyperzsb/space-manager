package com.hyperzsb.spacemanager.borrowing.repository;

import com.hyperzsb.spacemanager.borrowing.domain.Academy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademyRepository extends JpaRepository<Academy, Integer> {
    Academy findAcademyByName(String name);
}

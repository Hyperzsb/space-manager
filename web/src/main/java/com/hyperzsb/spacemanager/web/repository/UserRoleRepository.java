package com.hyperzsb.spacemanager.web.repository;

import com.hyperzsb.spacemanager.web.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findUserRolesByUserId(Long userId);
}

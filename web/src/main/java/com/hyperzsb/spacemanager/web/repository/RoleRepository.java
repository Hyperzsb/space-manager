package com.hyperzsb.spacemanager.web.repository;

import com.hyperzsb.spacemanager.web.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
}

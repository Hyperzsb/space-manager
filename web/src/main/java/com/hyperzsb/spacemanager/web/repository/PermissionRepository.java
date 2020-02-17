package com.hyperzsb.spacemanager.web.repository;

import com.hyperzsb.spacemanager.web.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findPermissionById(Long id);
}

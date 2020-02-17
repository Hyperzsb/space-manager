package com.hyperzsb.spacemanager.web.repository;

import com.hyperzsb.spacemanager.web.domain.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    List<RolePermission> findRolePermissionsByRoleId(Long roleId);
}

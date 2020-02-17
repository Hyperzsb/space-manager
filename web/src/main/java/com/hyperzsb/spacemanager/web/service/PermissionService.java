package com.hyperzsb.spacemanager.web.service;

import com.hyperzsb.spacemanager.web.domain.Permission;
import com.hyperzsb.spacemanager.web.domain.Role;

import java.util.List;

public interface PermissionService {
    List<Permission> getPermissionsByRole(Role role);
}

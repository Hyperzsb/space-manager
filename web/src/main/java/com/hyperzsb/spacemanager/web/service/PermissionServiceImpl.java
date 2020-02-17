package com.hyperzsb.spacemanager.web.service;

import com.hyperzsb.spacemanager.web.domain.Permission;
import com.hyperzsb.spacemanager.web.domain.Role;
import com.hyperzsb.spacemanager.web.domain.RolePermission;
import com.hyperzsb.spacemanager.web.repository.PermissionRepository;
import com.hyperzsb.spacemanager.web.repository.RolePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> getPermissionsByRole(Role role) {
        List<RolePermission> rolePermissionList = rolePermissionRepository.findRolePermissionsByRoleId(role.getId());
        List<Permission> permissionList = new ArrayList<>();
        for (RolePermission rolePermission : rolePermissionList)
            permissionList.add(permissionRepository.findPermissionById(rolePermission.getPermissionId()));
        return permissionList;
    }

}

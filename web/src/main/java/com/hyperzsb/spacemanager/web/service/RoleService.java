package com.hyperzsb.spacemanager.web.service;

import com.hyperzsb.spacemanager.web.domain.Role;
import com.hyperzsb.spacemanager.web.domain.User;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();
    List<Role> getRolesByUser(User user);
}

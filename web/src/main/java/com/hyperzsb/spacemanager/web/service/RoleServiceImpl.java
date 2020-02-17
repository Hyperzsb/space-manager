package com.hyperzsb.spacemanager.web.service;

import com.hyperzsb.spacemanager.web.domain.Role;
import com.hyperzsb.spacemanager.web.domain.User;
import com.hyperzsb.spacemanager.web.domain.UserRole;
import com.hyperzsb.spacemanager.web.repository.RoleRepository;
import com.hyperzsb.spacemanager.web.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> getRolesByUser(User user) {
        List<UserRole> userRoleList = userRoleRepository.findUserRolesByUserId(user.getId());
        List<Role> roleList = new ArrayList<>();
        for (UserRole userRole : userRoleList)
            roleList.add(roleRepository.findRoleById(userRole.getRoleId()));
        return roleList;
    }

}

package com.hyperzsb.spacemanager.web.service;

import com.hyperzsb.spacemanager.web.domain.Role;
import com.hyperzsb.spacemanager.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username);
        if (user != null) {
            List<Role> roleList = roleService.getRolesByUser(user);
            user.setAuthorities(roleList);
            return user;
        } else {
            throw new UsernameNotFoundException("No user found. ");
        }
    }

}

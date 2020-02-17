package com.hyperzsb.spacemanager.web.service;

import com.hyperzsb.spacemanager.web.domain.User;
import com.hyperzsb.spacemanager.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

}

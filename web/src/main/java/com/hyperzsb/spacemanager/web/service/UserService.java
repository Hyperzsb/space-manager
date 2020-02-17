package com.hyperzsb.spacemanager.web.service;

import com.hyperzsb.spacemanager.web.domain.User;

public interface UserService {
    User getUserByUserName(String userName);
}
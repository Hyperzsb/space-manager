package com.hyperzsb.spacemanager.web.repository;

import com.hyperzsb.spacemanager.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserName(String userName);
}

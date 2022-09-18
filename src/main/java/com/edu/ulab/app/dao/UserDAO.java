package com.edu.ulab.app.dao;

import com.edu.ulab.app.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    User saveUser(User user);

    boolean isExistedUser(User user);

    User getUser(Long id);

    void deleteUser(Long id);
}
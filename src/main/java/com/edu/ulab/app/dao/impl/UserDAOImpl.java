package com.edu.ulab.app.dao.impl;

import com.edu.ulab.app.dao.UserDAO;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.storage.Storage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserDAOImpl implements UserDAO {
    private final AtomicLong USER_ID = new AtomicLong();
    Storage storage = new Storage();

    @Override
    public User saveUser(User user) {
        final Long userId = USER_ID.incrementAndGet();
        user.setId(userId);
        storage.getUserMap().put(userId, user);
        return user;
    }

    @Override
    public boolean isExistedUser(User user) {
        List<User> userList = new ArrayList<>(storage.getUserMap().values());
        List<String> listNames = userList.stream()
                .filter(Objects::nonNull)
                .map(User::getFullName).toList();

        for (String listName : listNames) {
            if (listName.equals(user.getFullName())){
                return true;
            }
        }
          return false;
    }

    @Override
    public User getUser(Long id) {
        return storage.getUserMap().get(id);
    }

    @Override
    public void deleteUser(Long id) {
        storage.getUserMap().remove(id);
    }
}
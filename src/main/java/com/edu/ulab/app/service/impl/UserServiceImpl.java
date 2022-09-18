package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.converter.UserConverter;
import com.edu.ulab.app.dao.UserDAO;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private final UserConverter userConverter;

    public UserServiceImpl(UserConverter userConverter, UserDAO userDAO) {
        this.userConverter = userConverter;
        this.userDAO = userDAO;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // сгенерировать идентификатор
        // создать пользователя
        // вернуть сохраненного пользователя со всеми необходимыми полями id
        User user = userDAO.saveUser(userConverter.fromUserDtoToUser(userDto));
        return userConverter.fromUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userConverter.fromUserDtoToUser(userDto);
        if (userDAO.isExistedUser(user)) {
            return userConverter.fromUserToUserDto(userDAO.saveUser(user));
        }
        return userDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        User users = userDAO.getUser(id);
        if (users != null) {
            return userConverter.fromUserToUserDto(users);
        }
        return null;
    }

    @Override
    public void deleteUserById(Long id) {
        userDAO.deleteUser(id);
    }
}

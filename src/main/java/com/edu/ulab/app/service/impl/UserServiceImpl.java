package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.BadRequestException;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.storage.CrudStorageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class UserServiceImpl implements UserService {

    private final CrudStorageRepository crudStorageRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper, CrudStorageRepository crudStorageRepository) {
        this.userMapper = userMapper;
        this.crudStorageRepository = crudStorageRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // сгенерировать идентификатор
        // создать пользователя
        // вернуть сохраненного пользователя со всеми необходимыми полями id

        if (userDto != null) {
            User user = crudStorageRepository.saveUser(userMapper.fromUserDtoToUser(userDto));
            return userMapper.fromUserToUserDto(user);
        }
        throw new BadRequestException("User cannot be null");
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userMapper.fromUserDtoToUser(userDto);
        if (crudStorageRepository.isExistedUserById(user.getId()) && userDto != null) {
            return userMapper.fromUserToUserDto(crudStorageRepository.saveUser(user));
        }
        return userDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        if (!crudStorageRepository.isExistedUserById(id)) {
            throw new NotFoundException("Not found id");
        }
        User users = crudStorageRepository.getUser(id);
        return userMapper.fromUserToUserDto(users);
    }

    @Override
    public void deleteUserById(Long id) {
        if (!crudStorageRepository.isExistedUserById(id)) {
            throw new NotFoundException("Not found id");
        }
        crudStorageRepository.deleteUser(id);
    }
}

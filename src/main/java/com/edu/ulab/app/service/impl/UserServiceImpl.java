package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.Person;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.exception.NullPointException;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.repository.UserRepository;
import com.edu.ulab.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        Person user = userMapper.userDtoToPerson(userDto);
        log.info("Mapped user: {}", user);
        Person savedUser = userRepository.save(user);
        log.info("Saved user: {}", savedUser);
        return userMapper.personToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        if (userDto == null) {
            throw new NullPointException("userDto is null");
        }
        Person user = userMapper.userDtoToPerson(userDto);
        log.info("Mapped user: {}", user);
        String fullName = userDto.getFullName();
        long idFullName = 0;
        Iterable<Person> all = userRepository.findAll();
        boolean isExistedFullName = false;
        for (Person person : all) {
            if (person.getFullName().equals(fullName)) {
                idFullName = person.getId();
                isExistedFullName = true;
                break;
            }
        }
        if (!isExistedFullName) {
            throw new NotFoundException("userDto not found");
        }
        Optional<Person> byIdForUpdate = userRepository.findByIdForUpdate(idFullName);
        Person updateUser = byIdForUpdate.orElse(null);
        log.info("Saved user: {}", updateUser);
        return userMapper.personToUserDto(updateUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        Person person = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("id person not found"));

        UserDto userDto = userMapper.personToUserDto(person);
        log.info("Get id person: {}", id);
        return userDto;
    }

    @Override
    public void deleteUserById(Long id) {
        Person person = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("id person not found"));

        userRepository.delete(person);
        log.info("Delete id person: {}", id);
    }
}

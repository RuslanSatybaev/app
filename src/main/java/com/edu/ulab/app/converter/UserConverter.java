package com.edu.ulab.app.converter;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User fromUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFullName(userDto.getFullName());
        user.setTitle(userDto.getTitle());
        user.setAge(userDto.getAge());
        return user;
    }

    public UserDto fromUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setTitle(user.getTitle());
        userDto.setAge(user.getAge());
        return userDto;
    }
}

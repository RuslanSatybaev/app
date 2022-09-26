package com.edu.ulab.app.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String title;
    private int age;

    public UserDto(Long id, String fullName, String title, int age) {
        this.id = id;
        this.fullName = fullName;
        this.title = title;
        this.age = age;
    }

    public UserDto() {

    }
}

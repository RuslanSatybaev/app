package com.edu.ulab.app.entity;

import com.edu.ulab.app.dto.BookDto;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
public class User {
    private Long id;
    private String fullName;
    private String title;
    private int age;

    private Set<BookDto> books = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
package com.edu.ulab.app.dao;

import com.edu.ulab.app.dto.BookDto;

import java.util.List;

public interface BookDAO {

    void saveBook(BookDto bookDto);

    BookDto getBook(Long id);

    List<Long> getBooks(Long userId);

    void deleteBook(Long id);
}
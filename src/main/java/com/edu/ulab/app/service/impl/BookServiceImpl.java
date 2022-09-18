package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dao.BookDAO;
import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    public BookDto createBook(BookDto bookDto) {
        bookDAO.saveBook(bookDto);
        return bookDto;
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        bookDAO.saveBook(bookDto);
        return bookDto;
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookDAO.getBook(id);
    }

    @Override
    public List<Long> getBooks(Long userId) {
        return bookDAO.getBooks(userId);
    }

    @Override
    public void deleteBookById(Long id) {
        bookDAO.deleteBook(id);
    }
}

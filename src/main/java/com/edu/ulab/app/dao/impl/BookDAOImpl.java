package com.edu.ulab.app.dao.impl;

import com.edu.ulab.app.dao.BookDAO;
import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.storage.Storage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BookDAOImpl implements BookDAO {
    private final AtomicLong BOOK_ID = new AtomicLong();
    Storage storage = new Storage();

    @Override
    public void saveBook(BookDto bookDto) {
        final Long bookId = BOOK_ID.incrementAndGet();
        bookDto.setId(bookId);
        storage.getBookDtoMap().put(bookId, bookDto);
    }

    @Override
    public BookDto getBook(Long id) {
        return storage.getBookDtoMap().get(id);
    }

    @Override
    public List<Long> getBooks(Long userId) {
        return storage.getBooksByUserId(userId);
    }

    @Override
    public void deleteBook(Long id) {
        storage.getBookDtoMap().remove(id);
    }
}
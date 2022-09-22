package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.exception.BadRequestException;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.storage.CrudStorageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    
    private final CrudStorageRepository crudStorageRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(CrudStorageRepository crudStorageRepository, BookMapper bookMapper) {
        this.crudStorageRepository = crudStorageRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        if (bookDto != null) {
            Book book1 = bookMapper.bookDtoToBook(bookDto);
            Book book = crudStorageRepository.saveBook(book1);
            return bookMapper.bookEntityToBookDot(book);
        }
        throw new BadRequestException("Book cannot be null");
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book book = bookMapper.bookDtoToBook(bookDto);
        if (crudStorageRepository.isExistedBookById(book.getId()) && bookDto != null) {
            return bookMapper.bookEntityToBookDot(crudStorageRepository.saveBook(book));
        }
        return bookDto;
    }

    @Override
    public BookDto getBookById(Long id) {
        if (!crudStorageRepository.isExistedBookById(id)) {
            throw new NotFoundException("Not found id");
        }
        Book books = crudStorageRepository.getBook(id);
        return bookMapper.bookEntityToBookDot(books);
    }

    @Override
    public List<Long> getBooks(Long userId) {
        return crudStorageRepository.getBooks(userId);
    }

    @Override
    public void deleteBookById(Long id) {
        if (!crudStorageRepository.isExistedBookById(id)) {
            throw new NotFoundException("Not found id");
        }
        crudStorageRepository.deleteBook(id);
    }
}

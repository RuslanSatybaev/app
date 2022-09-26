package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.repository.BookRepository;
import com.edu.ulab.app.repository.UserRepository;
import com.edu.ulab.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository,
                           UserRepository userRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = bookMapper.bookDtoToBook(bookDto);
        log.info("Mapped book: {}", book);
        Book savedBook = bookRepository.save(book);
        log.info("Saved book: {}", savedBook);
        return bookMapper.bookToBookDto(savedBook);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book book = bookMapper.bookDtoToBook(bookDto);
        log.info("Mapped book: {}", book);
        Optional<Book> byIdForUpdate = bookRepository.findByIdForUpdate(book.getId());
        Book updateBook = byIdForUpdate.orElse(null);
        log.info("Saved book: {}", updateBook);
        return bookMapper.bookToBookDto(updateBook);
    }

    @Override
    public BookDto getBookById(Long id) {
        Optional<Book> getBook = bookRepository.findById(id);

        return bookMapper.bookToBookDto(getBook.orElse(null));
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("id book not found"));
        bookRepository.deleteById(id);
    }

    @Override
    public void deleteBooksByUserId(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("id person not found"));

        Iterable<Book> all = bookRepository.findAll();
        for (Book book : all) {
            if (Objects.equals(book.getUserId(), userId)) {
                bookRepository.delete(book);
                log.info("Delete book: {}", book);
            }
        }
    }

    @Override
    public List<Long> getBooksIdByUserId(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("id person not found"));

        Iterable<Book> all = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : all) {
            bookMapper.bookToBookDto(book);
            BookDto bookDto = bookMapper.bookToBookDto(book);
            bookDtoList.add(bookDto);
        }
        return bookDtoList.stream()
                .filter(book -> Objects.nonNull(book) && book.getUserId().equals(userId))
                .map(BookDto::getId).toList();
    }
}

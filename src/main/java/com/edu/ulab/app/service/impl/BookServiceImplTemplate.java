package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class BookServiceImplTemplate implements BookService {

    private final JdbcTemplate jdbcTemplate;
//    private final JdbcOperations jdbcOperations;

    public BookServiceImplTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        final String INSERT_SQL = "INSERT INTO BOOK(TITLE, AUTHOR, PAGE_COUNT, USER_ID) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(INSERT_SQL, new String[]{"id"});
                        ps.setString(1, bookDto.getTitle());
                        ps.setString(2, bookDto.getAuthor());
                        ps.setLong(3, bookDto.getPageCount());
                        ps.setLong(4, bookDto.getUserId());
                        return ps;
                    }
                },
                keyHolder);

        bookDto.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return bookDto;
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        // реализовать недстающие методы
        return null;
    }

    @Override
    public BookDto getBookById(Long id) {
        String GET_BOOKS_SQL = "SELECT * FROM BOOK WHERE ID = ?";

        return jdbcTemplate.queryForObject(GET_BOOKS_SQL, new Object[]{id}, (rs, rowNum) ->
                new BookDto(
                        rs.getLong("ID"),
                        rs.getLong("USER_ID"),
                        rs.getString("TITLE"),
                        rs.getString("AUTHOR"),
                        rs.getLong("PAGE_COUNT")

                ));
    }

    @Override
    public void deleteBookById(Long id) {
        // реализовать недстающие методы
    }

    @Override
    public void deleteBooksByUserId(Long userId) {

    }

    @Override
    public List<Long> getBooksIdByUserId(Long userId) {
        String GET_BOOKS_SQL = "SELECT ID FROM BOOK WHERE USER_ID = ?";
        List<BookDto> bookDto = jdbcTemplate.query(GET_BOOKS_SQL, new Object[]{userId}, (rs, rowNum) ->
                new BookDto(
                        rs.getLong("ID")
                ));
        List<Long> booksIdList = new ArrayList<>();
        assert bookDto != null;
        booksIdList.add(bookDto.stream().map(BookDto::getId).count());
        return  booksIdList;
    }
}

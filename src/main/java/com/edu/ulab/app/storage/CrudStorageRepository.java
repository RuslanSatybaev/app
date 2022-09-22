package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;

import java.util.List;

public interface CrudStorageRepository {
    User saveUser(User user);



    boolean isExistedUserById(Long id);

    boolean isExistedBookById(Long id);

    User getUser(Long id);

    void deleteUser(Long id);

    Book saveBook(Book book);

    Book getBook(Long id);

    List<Long> getBooks(Long userId);

    void deleteBook(Long id);



}

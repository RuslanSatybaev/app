package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Repository
public class Storage implements CrudStorageRepository {

    // todo создать хранилище в котором будут содержаться данные
    // сделать абстракции через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения
    // продумать возможные ошибки
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может быть много книг и нужно создать эту связь
    // так же учесть, что методы хранилища принимают друго тип данных - учесть это в абстракции

    private final AtomicLong USER_ID = new AtomicLong();
    private final AtomicLong BOOK_ID = new AtomicLong();

    private final Map<Long, User> USER = new HashMap<>();
    private final Map<Long, Book> BOOK = new HashMap<>();

    public Map<Long, User> getUserMap() {
        return USER;
    }

    public Map<Long, Book> getBookMap() {
        return BOOK;
    }

    @Override
    public User saveUser(User user) {
        final Long userId = USER_ID.incrementAndGet();
        user.setId(userId);
        getUserMap().put(userId, user);
        return user;
    }

    @Override
    public User getUser(Long id) {
        return getUserMap().get(id);
    }

    @Override
    public void deleteUser(Long id) {
        getUserMap().remove(id);
    }

    @Override
    public Book saveBook(Book book) {
        final Long bookId = BOOK_ID.incrementAndGet();
        book.setId(bookId);
        getBookMap().put(bookId, book);
        return book;
    }

    @Override
    public Book getBook(Long id) {
        return getBookMap().get(id);
    }

    @Override
    public List<Long> getBooks(Long userId) {
        return getBooksByUserId(userId);
    }

    @Override
    public void deleteBook(Long id) {
        getBookMap().remove(id);
    }


    @Override
    public boolean isExistedUserById(Long id) {
        List<User> userList = new ArrayList<>(getUserMap().values());
        List<Long> listIds = userList.stream()
                .filter(Objects::nonNull)
                .map(User::getId).toList();

        for (Long listId : listIds) {
            if (Objects.equals(listId, id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isExistedBookById(Long id) {
        List<Book> bookList = new ArrayList<>(getBookMap().values());
        List<Long> listIds = bookList.stream()
                .filter(Objects::nonNull)
                .map(Book::getId).toList();

        for (Long listId : listIds) {
            if (Objects.equals(listId, id)) {
                return true;
            }
        }
        return false;
    }

    public List<Long> getBooksByUserId(Long userId) {
        List<Book> bookList = new ArrayList<>(getBookMap().values());

        return bookList.stream()
                .filter(book -> Objects.nonNull(book) && book.getUserId().equals(userId))
                .map(Book::getId).toList();
    }
}

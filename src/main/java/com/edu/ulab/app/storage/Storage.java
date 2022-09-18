package com.edu.ulab.app.storage;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;

import java.util.*;


public class Storage {
    //todo создать хранилище в котором будут содержаться данные
    // сделать абстракции через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения
    // продумать возможные ошибки
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может быть много книг и нужно создать эту связь
    // так же учесть, что методы хранилища принимают друго тип данных - учесть это в абстракции

    private final Map<Long, User> USER_DTO_MAP = new HashMap<>();
    private final Map<Long, BookDto> BOOK_DTO_MAP = new HashMap<>();

    public Map<Long, User> getUserMap() {
        return USER_DTO_MAP;
    }

    public Map<Long, BookDto> getBookDtoMap() {
        return BOOK_DTO_MAP;
    }

    public List<Long> getBooksByUserId(Long userId) {
        List<BookDto> bookDtoList = new ArrayList<>(getBookDtoMap().values());

        return bookDtoList.stream()
                .filter(book -> Objects.nonNull(book) && book.getUserId().equals(userId))
                .map(BookDto::getId).toList();
    }
}

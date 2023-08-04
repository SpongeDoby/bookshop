package com.lqf.service;

import com.lqf.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBookList();

    Book getBookById(Integer id);

}

package com.lqf.dao;

import com.lqf.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> getBookList();

    Book getBookById(Integer id);
}

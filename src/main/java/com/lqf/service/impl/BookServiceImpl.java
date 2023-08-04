package com.lqf.service.impl;

import com.lqf.dao.BookDao;
import com.lqf.entity.Book;
import com.lqf.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    @Override
    public List<Book> getBookList() {
        return bookDao.getBookList();
    }

    @Override
    public Book getBookById(Integer id) {
        return bookDao.getBookById(id);
    }
}

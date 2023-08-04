package com.lqf.controller;

import com.lqf.entity.Book;
import com.lqf.service.BookService;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class BookController {
    private BookService bookService;

    public String setBookList(HttpSession session){
        List<Book> bookList = bookService.getBookList();
        session.setAttribute("bookList",bookList);
        return "index";
    }
}

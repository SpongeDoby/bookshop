package com.lqf.dao.impl;

import com.lqf.dao.BookDao;
import com.lqf.entity.Book;
import com.lqf.myssm.dao.BaseDao;

import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public List<Book> getBookList() {
        String sql="select * from t_book where bookStatus=0 ORDER BY price ASC";
        return super.executeQuery(sql);
    }

    @Override
    public Book getBookById(Integer id) {
        String sql="select * from t_book where id=?";
        return super.getOne(sql,id);
    }
}

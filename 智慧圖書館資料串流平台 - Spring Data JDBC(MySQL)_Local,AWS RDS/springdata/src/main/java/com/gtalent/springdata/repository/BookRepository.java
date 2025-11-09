package com.gtalent.springdata.repository;

import com.gtalent.springdata.model.Book;
import java.util.List;

public interface BookRepository {
    int save(Book book);
    int update(Book book);
    Book findById(int id);
    int deleteById(int id);
    List<Book> findAll();
    int deleteAll();
}

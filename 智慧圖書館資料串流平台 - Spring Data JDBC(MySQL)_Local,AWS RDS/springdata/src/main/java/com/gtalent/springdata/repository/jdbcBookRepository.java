package com.gtalent.springdata.repository;

import com.gtalent.springdata.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class jdbcBookRepository implements BookRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Book book){
        return jdbcTemplate.update("INSERT INTO books (id, title, description) VALUES(?,?,?)",
        book.getId(), book.getTitle(), book.getDescription());
    }

    @Override
    public int update(Book book){
        return jdbcTemplate.update("UPDATE books SET title=?, description=? WHERE id=?",
                book.getTitle(), book.getDescription(), book.getId());
    }

    @Override
    public Book findById(int id){
        Book book = jdbcTemplate.queryForObject("SELECT * FROM books WHERE id=?",
                BeanPropertyRowMapper.newInstance(Book.class), id);
        return book;
        //return jdbcTemplate.queryForObject("SELECT * FROM books WHERE id=?");
    }

    @Override
    public int deleteById(int id){
        return jdbcTemplate.update("DELETE FROM books WHERE id=?", id);
    }

    @Override
    public int deleteAll(){
        return jdbcTemplate.update("DELETE FROM books");
    }

    @Override
    public List<Book> findAll(){
        return jdbcTemplate.query("SELECT * FROM books", BeanPropertyRowMapper.newInstance(Book.class));
    }
}

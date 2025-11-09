package com.gtalent.projectSpringJpa.repository;


import com.gtalent.projectSpringJpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository <Book, Integer> {
    List<Book> findByDescription(String description);
    // SELECT * FROM books WHERE description = ?

    // + 原生SQL
    // @Query(value="SELECT * FROM books WHERE LOWER(title) LIKE %:keyword%", nativeQuery = true)
    // List<Book> searchByTitle(@Param("title") String title);

}

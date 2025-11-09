package com.gtalent.projectSpringR2DBC.repository;

import com.gtalent.projectSpringR2DBC.model.Book;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends R2dbcRepository<Book, Integer> {
    //Flux<Book> findByDescription(String description);
    // SELECT * FROM books WHERE description = ?
    Flux<Book> findByTitleContaining(String title);
    // SELECT * FROM books WHERE title LIKE %title%;
}

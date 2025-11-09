package com.gtalent.projectSpringR2DBC.service;


import com.gtalent.projectSpringR2DBC.model.Book;
import com.gtalent.projectSpringR2DBC.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public Mono<ResponseEntity<Flux<Book>>> getAllBook(String title){
        Flux<Book> result = (title == null)
                ? bookRepository.findAll(): bookRepository.findByTitleContaining(title);
        return result.collectList()
                .map(list -> list.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().build());
    }


    public Mono<ResponseEntity<Book>> getBookById(int id){
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseEntity<Book>> createBook(Book book){
        Book newBook = new Book(book.getTitle(), book.getDescription());
        return bookRepository.save(newBook)
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());
    }


    public Mono<Void> deleteBookById(int id){
        return bookRepository.deleteById(id);

    }

    public Mono<Void> deleteAllBook(){
        return bookRepository.deleteAll();
    }
}

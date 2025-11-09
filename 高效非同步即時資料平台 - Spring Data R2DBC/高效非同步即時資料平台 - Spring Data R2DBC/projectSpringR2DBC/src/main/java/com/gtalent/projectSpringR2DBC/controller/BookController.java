package com.gtalent.projectSpringR2DBC.controller;


import com.gtalent.projectSpringR2DBC.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.gtalent.projectSpringR2DBC.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public Mono<ResponseEntity<Flux<Book>>> getAllBook(@RequestParam(required = false)String title){
        return bookService.getAllBook(title);
    }

    @GetMapping("/book/{id}")
    public Mono<ResponseEntity<Book>> getBookById(@PathVariable("id") int id){
        return bookService.getBookById(id);
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Book>> createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }


   //@PutMapping("/books/{id}")
   //@ResponseStatus(HttpStatus.OK)
   //public Mono<Book> updateBook(@PathVariable("id") int id,@RequestBody Book book){
   //        return bookService.update(id, book);
   //}

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteBookById(@PathVariable("id") int id){
        return bookService.deleteBookById(id);
    }

    @DeleteMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllBook(){
        return bookService.deleteAllBook();
    }
}

package com.gtalent.springdata.controller;

import com.gtalent.springdata.model.Book;
import com.gtalent.springdata.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    // www.google.com/books?
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false)String title) {
        try {
            List<Book> books = new ArrayList<>();
            // title == null -> 查全部
            if (title == null) {
                bookRepository.findAll().forEach(books::add); // f -> books.add(x)
            } else {

            }

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){
        try{
            Book book = bookRepository.findById(id);
            if (book != null){
                return new ResponseEntity<>(book, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/booksS")
    public ResponseEntity<String> createBook(@RequestBody Book book){
        try {
            bookRepository.save(new Book(book.getId(), book.getTitle(), book.getDescription()));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") int id, @RequestBody Book book){
        try{
            Book savebook = bookRepository.findById(id);
            if(savebook != null){
                savebook.setTitle(book.getTitle());
                savebook.setDescription(book.getDescription());

                bookRepository.update(savebook);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") int id){
        try {
            int result = bookRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/books")
    public ResponseEntity<String> deleteAllBooks(){
        try {
            int result = bookRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

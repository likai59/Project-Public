package com.gtalent.projectSpringJpa.controller;

import com.gtalent.projectSpringJpa.model.Book;
import com.gtalent.projectSpringJpa.repository.BookRepository;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAll(@RequestParam(required = false) String title){
        try {
            List<Book> books = new ArrayList<>();
            if (title == null) {
                bookRepository.findAll().forEach(books::add);
            } else {
                //TBD
            }
            if (books.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().build();
            //return new ResponseEntity<>(books, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){
        Optional<Book> book = bookRepository.findById(id);
        try{
            if(book.isPresent()){
                return new ResponseEntity<>(book.get(), HttpStatus.OK);
            }else{
                //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/books")
    public ResponseEntity<String> createBook(@RequestBody Book book){
        try{
            bookRepository.save(new Book(book.getId(), book.getTitle(), book.getDescription()));
            //return new ResponseEntity<>(HttpStatus.OK);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") int id, @RequestBody Book book){
        try{
            Optional<Book> b = bookRepository.findById(id);
            if(b.isPresent()){
                Book savebook = b.get();
                savebook.setTitle(book.getTitle());
                savebook.setDescription(book.getDescription());

                bookRepository.save(savebook);
                return ResponseEntity.ok().build();
                //return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return ResponseEntity.notFound().build();
                //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") int id){
        try{
            bookRepository.deleteById(id);
            return ResponseEntity.noContent().build();
            //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/books")
    public ResponseEntity<String> deleteAllBooks(){
        try{
            bookRepository.deleteAll();
            //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

}

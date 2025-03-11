package com.apache.camel.controller;

import com.apache.camel.entities.Book;
import com.apache.camel.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(Book book){
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllBooks(){
        return new ResponseEntity<>(bookService.findAllBooks(),HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findBookByName(@PathVariable String name){
        return new ResponseEntity<>(bookService.findBookByName(name),HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> removeBook(@PathVariable int id){
        bookService.removeBook(id);
        ResponseEntity responseEntity = new ResponseEntity<>("Book Deleted SuccessFully", HttpStatus.OK);
        return responseEntity;
    }

}

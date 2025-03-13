package com.apache.camel.service;

import com.apache.camel.entities.Book;
import com.apache.camel.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private  BookRepository bookRepository;


    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookByName(String name) {
        return bookRepository.findBookByName(name);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void removeBook(int bookId) {
        bookRepository.deleteById(bookId);
    }

    public Page<Book> findAllByPagination(int page,int size,String sortBy,String direction)
    {
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sortBy));
        return bookRepository.findAll(pageable);
    }

}


package com.apache.camel.ApacheCamelDemo.repository;

import com.apache.camel.ApacheCamelDemo.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findBookByName(String name);
}

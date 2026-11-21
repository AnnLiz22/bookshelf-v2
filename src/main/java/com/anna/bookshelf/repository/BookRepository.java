package com.anna.bookshelf.repository;

import com.anna.bookshelf.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

  boolean existsByIsbn(String isbn);

}

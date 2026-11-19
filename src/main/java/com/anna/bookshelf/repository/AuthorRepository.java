package com.anna.bookshelf.repository;

import com.anna.bookshelf.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository <Author, Long> {
}

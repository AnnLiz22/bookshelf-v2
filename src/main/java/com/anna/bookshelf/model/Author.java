package com.anna.bookshelf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
  private List<Book> books = new ArrayList<>();

  public Author() {
  }

  public Author(String name) {
    setName(name);
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if(name == null || name.isBlank() || name.length() < 2){
      throw new IllegalArgumentException("Author name must contain at least 2 characters.");
    }
    this.name = name;
  }

  public List<Book> getBooks() {
    return books;
  }

  // Helpers
  public void addBook(Book book) {
    if (book == null) {
      throw new IllegalArgumentException("Book cannot be null.");
    }
    if (!books.contains(book)) {
      books.add(book);
      book.setAuthor(this);
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
package com.anna.bookshelf.dto;

import com.anna.bookshelf.model.Book;
import com.anna.bookshelf.model.Genre;
import com.anna.bookshelf.model.ReadingStatus;

public class BookResponse {

  private Long id;
  private String title;
  private Long authorId;
  private String authorName;
  private Genre genre;
  private ReadingStatus readingStatus;
  private int year;
  private String isbn;

  public BookResponse(Book book) {
    this.id = book.getId();
    this.title = book.getTitle();
    this.authorId = book.getAuthor().getId();
    this.authorName = book.getAuthor().getName();
    this.genre = book.getGenre();
    this.readingStatus = book.getReadingStatus();
    this.year = book.getYear();
    this.isbn = book.getIsbn();
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public String getAuthorName() {
    return authorName;
  }

  public Genre getGenre() {
    return genre;
  }

  public ReadingStatus getReadingStatus() {
    return readingStatus;
  }

  public int getYear() {
    return year;
  }

  public String getIsbn() {
    return isbn;
  }
}

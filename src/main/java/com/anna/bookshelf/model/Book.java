package com.anna.bookshelf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  private Author author;

  @Enumerated(EnumType.STRING)
  private Genre genre;

  @Enumerated(EnumType.STRING)
  private ReadingStatus readingStatus;

  private int year;

  @Column(unique = true, length = 13)
  private String isbn;


  protected Book() {
    this.readingStatus = ReadingStatus.WANT_TO_READ;
  }

  public Book(String title, Author author, Genre genre, int year, String isbn) {

    setTitle(title);
    setAuthor(author);
    setGenre(genre);
    setYear(year);
    setIsbn(isbn);
    this.readingStatus = ReadingStatus.WANT_TO_READ;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be null or empty.");
    }
    this.title = title;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {

    if(author == null){
      throw new IllegalArgumentException("Author is required.");
    }
    this.author = author;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    if(year > LocalDate.now().getYear()){
      throw new IllegalArgumentException("Year cannot be greater than current year. You can add it later.");
    }
    this.year = year;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    if(!(isbn.length() == 10 || isbn.length() == 13)){
      throw new IllegalArgumentException("Isbn is incorrect. You can add it later.");
    }
    this.isbn = isbn;
  }

  public ReadingStatus getReadingStatus() {
    return readingStatus;
  }

  public void setReadingStatus(ReadingStatus readingStatus) {
    this.readingStatus = readingStatus;
  }

  @Override
  public String toString() {
    return
        '\'' +   title + '\'' +
            ", author: " + author +
            ", genre: " + genre +
            ", readingStatus: " + readingStatus +
            ", year: " + year + " isbn: " + isbn + ".\n";
  }
}

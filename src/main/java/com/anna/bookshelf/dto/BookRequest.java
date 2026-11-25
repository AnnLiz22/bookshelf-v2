package com.anna.bookshelf.dto;

import com.anna.bookshelf.model.Genre;
import com.anna.bookshelf.validation.ValidIsbn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BookRequest {
  @NotBlank(message = "Title is required")
  private String title;

  @NotNull
  @Positive
  private Long authorId;

  private Genre genre;

  @Positive
  private int year;

  @NotBlank
  @NotNull
  @ValidIsbn
  private String isbn;

  public BookRequest() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
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
    this.year = year;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }
}


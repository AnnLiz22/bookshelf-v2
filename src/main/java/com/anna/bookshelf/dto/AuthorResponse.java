package com.anna.bookshelf.dto;

import com.anna.bookshelf.model.Author;

public class AuthorResponse {

  private Long id;
  private String name;

  public AuthorResponse(Author author) {
    this.id = author.getId();
    this.name = author.getName();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}

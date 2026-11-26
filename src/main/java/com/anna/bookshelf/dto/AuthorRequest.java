package com.anna.bookshelf.dto;

import com.anna.bookshelf.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public class AuthorRequest {

  @NotBlank(message = "Author name must not be empty.")
  @Size(min = 2, max=100, message = "Author name must contain between 2 and 100 characters.")
  String name;

  public AuthorRequest(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

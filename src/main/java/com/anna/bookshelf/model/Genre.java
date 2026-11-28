package com.anna.bookshelf.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;

public enum Genre {

  FICTION(1, "Fiction"),
  FANTASY(2, "Fantasy"),
  SCIENCE(3, "Science"),
  HISTORY(4, "History"),
  BIOGRAPHY(5, "Biography"),
  TECHNOLOGY(6, "Technology"),
  OTHER(7, "Other books");

  private final int number;
  private final String description;

  Genre(int number, String description) {
    this.number = number;
    this.description = description;
  }

  public int getNumber() {
    return number;
  }

  public String getDescription() {
    return description;
  }

  public static void printGenres() {
    Arrays.stream(Genre.values())
        .forEach(genre ->
            System.out.println(genre.getNumber() + ". " + genre.getDescription()));
  }

  @JsonCreator
  public static Genre fromString(String value) {
    if (value == null || value.isBlank()) {
      return null;
    }
    return Genre.valueOf(value.trim().toUpperCase());
  }
}

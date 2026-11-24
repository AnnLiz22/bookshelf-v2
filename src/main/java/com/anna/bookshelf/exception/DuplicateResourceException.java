package com.anna.bookshelf.exception;

public class DuplicateResourceException extends IllegalArgumentException {
  public DuplicateResourceException(String s) {
    super(s);
  }
}

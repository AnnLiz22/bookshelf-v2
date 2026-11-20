package com.anna.bookshelf.model;


import java.util.Arrays;

public enum ReadingStatus {

  WANT_TO_READ (1, "I want to read"),
  READING (2, "Reading..."),
  FINISHED(3, "Finished"),
  ABANDONED(4, "Abandoned");

  private final int number;
  private final String description;

  ReadingStatus(int number, String description) {
    this.number = number;
    this.description = description;
  }

  public int getNumber() {
    return number;
  }

  public String getDescription() {
    return description;
  }

  public static void printReadingStatuses(){
    Arrays.stream(ReadingStatus.values())
        .forEach(r -> System.out.println(r.getNumber()
            + ". " + r.getDescription()));
  }
}

package com.anna.bookshelf.service;

import com.anna.bookshelf.model.Book;
import com.anna.bookshelf.model.Genre;
import com.anna.bookshelf.model.ReadingStatus;
import com.anna.bookshelf.repository.BookRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
  private final BookRepository bookRepository;

  public StatisticsService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Long getNumberOfBooks(){
   return bookRepository.count();
  }

  public Map<String, Long> getNumOfBooksForEachReadingStatus(){

  List<Book> books = bookRepository.findAll();
  return books.stream()
      .collect(Collectors.groupingBy(b->b.getReadingStatus().getDescription(), Collectors.counting()));

  }

  public Map<Genre, Long> getNumOfBooksForEachGenre(){
    List<Book> books = bookRepository.findAll();
    return books.stream().collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()));
  }

  public Map.Entry<Genre, Long> getTheMostPopularGenre(){

  return  getNumOfBooksForEachGenre().entrySet().stream()
            .max(Map.Entry.comparingByValue()).orElseThrow();

  }
}

package com.anna.bookshelf.controller;

import com.anna.bookshelf.model.Genre;
import com.anna.bookshelf.model.ReadingStatus;
import com.anna.bookshelf.service.StatisticsService;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

  private final StatisticsService statisticsService;

  public StatisticsController(StatisticsService statisticsService) {
    this.statisticsService = statisticsService;
  }
  @GetMapping
  public Long showNumOfBooks(){
  return statisticsService.getNumberOfBooks();
  }

  @GetMapping("/bookStatus")
  public Map<String, Long> showNumOfBooksForEachReadingStatus(){
    return statisticsService.getNumOfBooksForEachReadingStatus();
  }

  @GetMapping("/booksByGenre")
  public Map<Genre, Long> showNumOfBooksForEachGenre(){
    return statisticsService.getNumOfBooksForEachGenre();
  }

  @GetMapping("/mostPopularGenre")
  public Map.Entry<Genre, Long> showMostPopularGenre(){
    return statisticsService.getTheMostPopularGenre();
  }

}

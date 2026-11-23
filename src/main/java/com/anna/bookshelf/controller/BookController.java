package com.anna.bookshelf.controller;

import com.anna.bookshelf.dto.BookRequest;
import com.anna.bookshelf.model.Book;
import com.anna.bookshelf.model.Author;
import com.anna.bookshelf.service.BookService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<Book> getAllBooks(){
    return bookService.getAllBooks();
  }

  @GetMapping("/{id}")
  public Book getBookById(@PathVariable Long id){
   return bookService.getBookById(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBookById(@PathVariable Long id){
    bookService.deleteBook(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Book addBook(@RequestBody BookRequest bookRequest){
    return bookService.addBook(bookRequest);
  }

  @PutMapping("/{id}")
  public Book updateBook(@PathVariable Long id, @RequestBody Book book){
    return bookService.updateBook(id, book);
  }
}

package com.anna.bookshelf.service;

import com.anna.bookshelf.model.Author;
import com.anna.bookshelf.model.Book;
import com.anna.bookshelf.repository.AuthorRepository;
import com.anna.bookshelf.repository.BookRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
  }

  public List<Book> getAllBooks(){
    return bookRepository.findAll();
  }

  public Book getBookById(Long id) {
    return bookRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Book with id: " + id + " not found"));

  }
  public void deleteBook(Long id){
    if (!bookRepository.existsById(id)) {
      throw new RuntimeException("Book with id: " + id + " not found");
    }
    bookRepository.deleteById(id);
  }
  public Book addBook(Book book, Long authorId){
    if (bookRepository.existsByIsbn(book.getIsbn())) {
      throw new IllegalArgumentException(
          "Book with ISBN: " + book.getIsbn() + " already exists."
      );
    }
    Author author = authorRepository.findById(authorId).orElseThrow();
    book.setAuthor(author);
   return bookRepository.save(book);
  }

  public Book updateBook(Long id, Book updatedBook){
    Book existingBook =
        bookRepository
            .findById(id)
            .orElseThrow(()->new RuntimeException("Book doesn't exist"));

    existingBook.setTitle(updatedBook.getTitle());
    existingBook.setAuthor(updatedBook.getAuthor());
    existingBook.setGenre(updatedBook.getGenre());
    existingBook.setYear(updatedBook.getYear());
    existingBook.setIsbn(updatedBook.getIsbn());

   return bookRepository.save(existingBook);
  }

}

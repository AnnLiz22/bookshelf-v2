package com.anna.bookshelf.service;

import com.anna.bookshelf.dto.BookRequest;
import com.anna.bookshelf.dto.BookResponse;
import com.anna.bookshelf.exception.DuplicateResourceException;
import com.anna.bookshelf.exception.ResourceNotFoundException;
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

  public List<BookResponse> getAllBooks(){
    List<Book> books = bookRepository.findAll();
    return books.stream().map(BookResponse::new).toList();
  }

  public BookResponse getBookById(Long id) {
    Book book = bookRepository.findById(id)
        .orElseThrow(()
            -> new ResourceNotFoundException("Book with id: " + id + " not found"));
    return new BookResponse(book);
  }

  public Book addBook(BookRequest request){

    if (bookRepository.existsByIsbn(request.getIsbn())) {
      throw new DuplicateResourceException(
          "Book with ISBN: " + request.getIsbn() + " already exists."
      );
    }
    Author author = authorRepository.findById(request.getAuthorId())
        .orElseThrow(() -> new ResourceNotFoundException("Author not found."));

    Book book = new Book(
        request.getTitle(),
        author,
        request.getGenre(),
        request.getYear(),
        request.getIsbn()
    );
   return bookRepository.save(book);
  }

  public Book updateBook(Long id, BookRequest bookRequest){
    Book existingBook =
        bookRepository
            .findById(id)
            .orElseThrow(()
                -> new ResourceNotFoundException("Book with id: " +id+ " doesn't exist."));

    Author author = authorRepository.findById(bookRequest.getAuthorId())
        .orElseThrow(()
            -> new ResourceNotFoundException("Author not found."));

    existingBook.setTitle(bookRequest.getTitle());
    existingBook.setAuthor(author);
    existingBook.setGenre(bookRequest.getGenre());
    existingBook.setYear(bookRequest.getYear());
    existingBook.setIsbn(bookRequest.getIsbn());

   return bookRepository.save(existingBook);
  }

  public void deleteBook(Long id){
    if (!bookRepository.existsById(id)) {
      throw new ResourceNotFoundException("Book with id: " + id + " not found.");
    }
    bookRepository.deleteById(id);
  }
}

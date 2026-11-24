package com.anna.bookshelf.service;

import com.anna.bookshelf.exception.DuplicateResourceException;
import com.anna.bookshelf.exception.ResourceNotFoundException;
import com.anna.bookshelf.model.Author;
import com.anna.bookshelf.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorService {

  private final AuthorRepository authorRepository;

  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

 public Author addAuthor(Author author){
    if(authorRepository.existsByName(author.getName())){
      throw new DuplicateResourceException("Author " + author.getName() +" already exists.");
    }
   return authorRepository.save(author);
 }

 public List <Author> getAllAuthors(){
    return authorRepository.findAll();
 }

 public Author findAuthor(Long id){
   return authorRepository.findById(id).orElseThrow(()
       -> new ResourceNotFoundException("Author with the id " + id + " not found."));
 }

  public Author updateAuthor(Long id, Author updatedAuthor) {
   Author existingAuthor = authorRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Author with id: "+id +" not found."));
    existingAuthor.setName(updatedAuthor.getName());

    return authorRepository.save(existingAuthor);
  }

  public void deleteAuthor(Long id){
    Author author = findAuthor(id);
    authorRepository.delete(author);
  }
}

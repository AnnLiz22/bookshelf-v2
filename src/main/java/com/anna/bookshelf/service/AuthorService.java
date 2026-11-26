package com.anna.bookshelf.service;

import com.anna.bookshelf.dto.AuthorRequest;
import com.anna.bookshelf.dto.AuthorResponse;
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

 public AuthorResponse addAuthor(AuthorRequest authorRequest){
    if(authorRepository.existsByName(authorRequest.getName())){
      throw new DuplicateResourceException("Author " + authorRequest.getName() +" already exists.");
    }
    Author author = new Author(authorRequest.getName());
    authorRepository.save(author);
   return new AuthorResponse(author);
 }

 public List <AuthorResponse> getAllAuthors(){
    List <Author> authors = authorRepository.findAll();
    return authors.stream().map(AuthorResponse::new).toList();
 }

 public AuthorResponse findAuthor(Long id){
   Author author = authorRepository.findById(id).orElseThrow(()
       -> new ResourceNotFoundException("Author with the id " + id + " not found."));
   return new AuthorResponse(author);
 }

  public AuthorResponse updateAuthor(Long id, AuthorRequest authorRequest) {
   Author existingAuthor = authorRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Author with id: "+id +" not found."));
    existingAuthor.setName(authorRequest.getName());
     authorRepository.save(existingAuthor);
    return new AuthorResponse(existingAuthor);
  }

  public void deleteAuthor(Long id){
    if(!authorRepository.existsById(id)){
      throw new ResourceNotFoundException("Author with id: " + id +" not found.");
    };
    authorRepository.deleteById(id);
  }
}

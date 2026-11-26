package com.anna.bookshelf.controller;

import com.anna.bookshelf.dto.AuthorRequest;
import com.anna.bookshelf.dto.AuthorResponse;
import com.anna.bookshelf.model.Author;
import com.anna.bookshelf.service.AuthorService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/authors")
public class AuthorController {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AuthorResponse addAuthor(@Valid @RequestBody AuthorRequest author){
  return  authorService.addAuthor(author);
  }

  @GetMapping
  public List<AuthorResponse> getAuthors(){
    return authorService.getAllAuthors();
  }

  @GetMapping("/{id}")
  public AuthorResponse getAuthor(@PathVariable Long id){
    return authorService.findAuthor(id);
  }

  @PutMapping("/{id}")
  public AuthorResponse updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorRequest authorRequest){
    return authorService.updateAuthor(id, authorRequest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAuthor(@PathVariable Long id){
    authorService.deleteAuthor(id);
  }
}

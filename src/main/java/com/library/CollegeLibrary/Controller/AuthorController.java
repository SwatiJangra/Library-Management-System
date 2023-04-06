package com.library.CollegeLibrary.Controller;

import com.library.CollegeLibrary.Entity.Author;
import com.library.CollegeLibrary.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add-author")
    public ResponseEntity<String> addAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
        return new ResponseEntity<>("Author has been addedd successfully!", HttpStatus.CREATED);
    }
    @GetMapping("/get-all-authors")
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }
}

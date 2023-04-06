package com.library.CollegeLibrary.Service;

import com.library.CollegeLibrary.Entity.Author;
import com.library.CollegeLibrary.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}

package com.library.CollegeLibrary.Controller;

import com.library.CollegeLibrary.DTO.BookRequestDTO;
import com.library.CollegeLibrary.DTO.BookResponseDTO;
import com.library.CollegeLibrary.Entity.Book;
import com.library.CollegeLibrary.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

//    @PostMapping("/add-book")
//    public ResponseEntity<String> addBook(@RequestBody Book book) throws Exception {
//        try {
//            bookService.addBook(book);
//        } catch (Exception e) {
//            throw new Exception("Book not present in the db ---> " + e.getMessage());
//        }
//        return new ResponseEntity<>("Book added successfully!", HttpStatus.CREATED);
//    }

    // Refined code for above

    @PostMapping("/add-book")
    public BookResponseDTO addBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.addBook(bookRequestDTO);
    }
}

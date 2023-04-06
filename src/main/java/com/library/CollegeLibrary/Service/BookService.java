package com.library.CollegeLibrary.Service;

import com.library.CollegeLibrary.DTO.BookRequestDTO;
import com.library.CollegeLibrary.DTO.BookResponseDTO;
import com.library.CollegeLibrary.Entity.Author;
import com.library.CollegeLibrary.Entity.Book;
import com.library.CollegeLibrary.Repository.AuthorRepository;
import com.library.CollegeLibrary.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

//    public void addBook(Book book) throws Exception {
//        Author author;
//        try {
//            author=authorRepository.findById(book.getAuthor().getId()).get();
//        } catch (Exception e) {
//            throw new Exception("Author is not present in db ---> " + e.toString());
//        }
//        //fetching books written by the author
//        List<Book> bookList=author.getBookList();
//        //adding new book in the list
//        bookList.add(book);
//
//        authorRepository.save(author);
//    }

    // Refined code for above

    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) {
        // get the author object
        Author author=authorRepository.findById(bookRequestDTO.getAuthorId()).get();
        Book book=new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setGenre(bookRequestDTO.getGenre());
        book.setPrice(bookRequestDTO.getPrice());
        book.setIssued(false); // bcz initially book is not related to any card or student
        book.setAuthor(author);

        // add the book in the list of books of author
        author.getBookList().add(book);
        authorRepository.save(author); // will save both book and author

        BookResponseDTO bookResponseDTO=new BookResponseDTO();
        bookResponseDTO.setTitle(bookRequestDTO.getTitle());
        bookResponseDTO.setAuthorName(author.getName());
        bookResponseDTO.setPrice(bookRequestDTO.getPrice());

        return bookResponseDTO;
    }
}

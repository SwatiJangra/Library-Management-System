package com.library.CollegeLibrary.Service;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.library.CollegeLibrary.DTO.IssueBookRequestDTO;
import com.library.CollegeLibrary.DTO.IssueBookResponseDTO;
import com.library.CollegeLibrary.Entity.Book;
import com.library.CollegeLibrary.Entity.LibraryCard;
import com.library.CollegeLibrary.Entity.Transaction;
import com.library.CollegeLibrary.Enum.CardStatus;
import com.library.CollegeLibrary.Enum.TransactionStatus;
import com.library.CollegeLibrary.Repository.BookRepository;
import com.library.CollegeLibrary.Repository.LibraryCardRepository;
import com.library.CollegeLibrary.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    LibraryCardRepository libraryCardRepository;
    @Autowired
    BookRepository bookRepository;

    public IssueBookResponseDTO issueBook(IssueBookRequestDTO issueBookRequestDTO) throws Exception {
        // Create transaction Object
        Transaction transaction=new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssuedOperation(true);

        LibraryCard libraryCard;
        try {
            // Step:1-->
            libraryCard=libraryCardRepository.findById(issueBookRequestDTO.getCardId()).get();
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid Card-Id");
            transactionRepository.save(transaction);
            throw new Exception("Invalid Card-Id!");
        }
        Book book;
        try {
            book = bookRepository.findById(Integer.parseInt(issueBookRequestDTO.getBookId())).get();
        } catch(Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid Book-Id");
            transactionRepository.save(transaction);
            throw new Exception("Invalid Book-ID!");
        }
        // if both card and book found, then
        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);
        if(libraryCard.getCardStatus() != CardStatus.ACTIVATED) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Your card is not activated!");
            transactionRepository.save(transaction);
            throw new Exception("Your card is not activated!");
        }
        if(book.isIssued() == true) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Sorry! Book is already issued!");
            transactionRepository.save(transaction);
            throw new Exception("Sorry! Book is already issued!");
        }
        // after this, book can be issue
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("Transaction was successful!");
        book.setIssued(true);
        book.setLibraryCard(libraryCard);
        book.getTransactions().add(transaction);
        libraryCard.getTransactionList().add(transaction); // adding transaction log
        libraryCard.getBooksIssued().add(book);

        libraryCardRepository.save(libraryCard); // will save book and transactions

        // converting into dto object
        IssueBookResponseDTO issueBookResponseDTO=new IssueBookResponseDTO();
        issueBookResponseDTO.setBookName(book.getTitle());
        issueBookResponseDTO.setTransactionId(transaction.getTransactionNumber());
        issueBookResponseDTO.setTransactionStatus(TransactionStatus.SUCCESS);

        return issueBookResponseDTO;
    }
}

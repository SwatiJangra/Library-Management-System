package com.library.CollegeLibrary.Service;

import com.library.CollegeLibrary.Repository.LibraryCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryCardService {
    @Autowired
    LibraryCardRepository libraryCardRepository;
}

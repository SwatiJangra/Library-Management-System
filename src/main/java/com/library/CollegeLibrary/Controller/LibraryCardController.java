package com.library.CollegeLibrary.Controller;

import com.library.CollegeLibrary.Service.LibraryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library-card")
public class LibraryCardController {
    @Autowired
    LibraryCardService libraryCardService;
}

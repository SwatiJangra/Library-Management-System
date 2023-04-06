package com.library.CollegeLibrary.Controller;

import com.library.CollegeLibrary.DTO.IssueBookRequestDTO;
import com.library.CollegeLibrary.DTO.IssueBookResponseDTO;
import com.library.CollegeLibrary.Enum.TransactionStatus;
import com.library.CollegeLibrary.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue-book")
    public IssueBookResponseDTO issueBook(@RequestBody IssueBookRequestDTO issueBookRequestDTO) throws Exception {
        IssueBookResponseDTO issueBookResponseDTO=new IssueBookResponseDTO();
        try {
            issueBookResponseDTO=transactionService.issueBook(issueBookRequestDTO);
        } catch (Exception e) {
            throw new Exception("Invalid Card-id ---> " + e.toString());
        }
        return issueBookResponseDTO;
    }
}

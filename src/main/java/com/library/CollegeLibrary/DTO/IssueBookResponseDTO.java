package com.library.CollegeLibrary.DTO;

import com.library.CollegeLibrary.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueBookResponseDTO {
    private String transactionId;
    private String bookName;
    private TransactionStatus transactionStatus;
}

package com.library.CollegeLibrary.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookResponseDTO {
    private String title;
    private int price;
    private String authorName;
}

package com.library.CollegeLibrary.DTO;

import com.library.CollegeLibrary.Enum.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookRequestDTO {
    private String title;
    private int price;
    private Genre genre;
    private int authorId;
}

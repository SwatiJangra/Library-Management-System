package com.library.CollegeLibrary.DTO;

import com.library.CollegeLibrary.Enum.Department;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentRequestDTO {
    private String name;
    private String email;
    private int age;
    private Department department;
}

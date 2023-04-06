package com.library.CollegeLibrary.Controller;

import com.library.CollegeLibrary.DTO.StudentRequestDTO;
import com.library.CollegeLibrary.DTO.StudentResponseDTO;
import com.library.CollegeLibrary.DTO.StudentUpdateEmailRequestDTO;
import com.library.CollegeLibrary.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

//    @PostMapping("/add-student")
//    public ResponseEntity<String> addStudent(@RequestBody Student student) {
//        studentService.addStudent(student);
//        return new ResponseEntity<>("Student has been added successfully!", HttpStatus.CREATED);
//    }

    //Refined version of above

    @PostMapping("/add-student")
    public StudentResponseDTO addStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        return studentService.addStudent(studentRequestDTO);
    }
    @GetMapping("/find-student-by-email")
    public void findStudentByEmail(@RequestParam("email") String email) throws Exception {
        try {
            studentService.findStudentByEmail(email);
        } catch (Exception e) {
            throw new Exception("Student not present ---> " + e.getMessage());
        }
    }
    @PutMapping("/update-email")
    public StudentResponseDTO updateEmail(@RequestBody StudentUpdateEmailRequestDTO studentUpdateEmailRequestDTO) {
        return studentService.updateEmail(studentUpdateEmailRequestDTO);
    }
}

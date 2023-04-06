package com.library.CollegeLibrary.Service;

import com.library.CollegeLibrary.DTO.StudentRequestDTO;
import com.library.CollegeLibrary.DTO.StudentResponseDTO;
import com.library.CollegeLibrary.DTO.StudentUpdateEmailRequestDTO;
import com.library.CollegeLibrary.Entity.LibraryCard;
import com.library.CollegeLibrary.Entity.Student;
import com.library.CollegeLibrary.Enum.CardStatus;
import com.library.CollegeLibrary.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

//    public void addStudent(Student student) {
//        // Create the library card for the new student
//        LibraryCard libraryCard=new LibraryCard();
//        libraryCard.setCardStatus(CardStatus.ACTIVATED);
//        libraryCard.setValidTill("03/2025");
//        libraryCard.setStudent(student);
//
//        student.setLibraryCard(libraryCard);
//
//        studentRepository.save(student);
//    }

    // Updated version of above code
    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO) {
        // create student object
        Student student=new Student();
        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setAge(studentRequestDTO.getAge());
        student.setDepartment(studentRequestDTO.getDepartment());

        // create library-card object
        LibraryCard libraryCard=new LibraryCard();
        libraryCard.setCardStatus(CardStatus.ACTIVATED);
        // linking card to student details
        libraryCard.setStudent(student);

        //set card in student
        student.setLibraryCard(libraryCard);

        // save details of student
        studentRepository.save(student); // save both student and library-card in repository

        // convert student into student dto object
        StudentResponseDTO studentResponseDTO=new StudentResponseDTO();
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setDepartment(student.getDepartment());
        studentResponseDTO.setEmail(studentRequestDTO.getEmail());
        studentResponseDTO.setAge(studentRequestDTO.getAge());

        return studentResponseDTO;
    }

    public String findStudentByEmail(String email) throws Exception {
        Student student;
        try {
            student=studentRepository.findByEmail(email);
        } catch (Exception e) {
            throw new Exception("Student not found ---> " + e.getMessage());
        }
        return student.getName();
    }

    public StudentResponseDTO updateEmail(StudentUpdateEmailRequestDTO studentUpdateEmailRequestDTO) {
        Student student=studentRepository.findById(studentUpdateEmailRequestDTO.getId()).get();
        student.setEmail(studentUpdateEmailRequestDTO.getEmail());
        //update step--> create new student and add into that
        Student updatedStudent=studentRepository.save(student);
        // convert updatedStudent to responseDto
        StudentResponseDTO studentResponseDTO=new StudentResponseDTO();
        studentResponseDTO.setId(updatedStudent.getId());
        studentResponseDTO.setName(updatedStudent.getName());
        studentResponseDTO.setEmail(updatedStudent.getEmail());
        studentResponseDTO.setDepartment(updatedStudent.getDepartment());
        return studentResponseDTO;

    }
}

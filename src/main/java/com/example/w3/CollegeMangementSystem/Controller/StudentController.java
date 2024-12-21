package com.example.w3.CollegeMangementSystem.Controller;


import com.example.w3.CollegeMangementSystem.DTO.StudentDTO;
import com.example.w3.CollegeMangementSystem.Services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {


    private final StudentService studentServ;

    public StudentController(StudentService studentServ) {
        this.studentServ = studentServ;
    }

    @GetMapping("/{id}")
    public StudentDTO getStudent(@PathVariable Long id){
        return studentServ.getStudent(id);
    }

    @PostMapping
    public StudentDTO addStudent(@RequestBody StudentDTO body){

        return studentServ.addStudent(body);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        return studentServ.deleteStudent(id);
    }

    @PutMapping("/{studentId}/subject/{subjectId}")
    public Optional<StudentDTO> assignSubjecttoStudent(@PathVariable Long studentId, @PathVariable Long subjectId){

        return studentServ.assignSubjecttoStudent(studentId,subjectId);
    }

    @PutMapping("/update/{id}")
    public StudentDTO updateStudent(@PathVariable Long id,@RequestBody StudentDTO body){
        return studentServ.updateStudent(id,body);
    }



}

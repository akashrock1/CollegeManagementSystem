package com.example.w3.CollegeMangementSystem.Controller;


import com.example.w3.CollegeMangementSystem.DTO.SubjectDTO;
import com.example.w3.CollegeMangementSystem.Entity.SubjectEntity;
import com.example.w3.CollegeMangementSystem.Services.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/subject")
public class SubjectController {

    private final SubjectService subjectServ;

    public SubjectController(SubjectService subjectServ) {
        this.subjectServ = subjectServ;
    }

    @GetMapping("/{id}")
    public SubjectDTO getSubject(@PathVariable Long id){

        return subjectServ.getSubject(id);

    }

    @PostMapping
    public SubjectDTO postSubject(@RequestBody SubjectDTO body){

        return subjectServ.postSubject(body);

    }

    @PutMapping("/{subject_id}/professor/{professor_id}")
    public Optional<SubjectEntity> assignSubjectToProfessor(@PathVariable Long subject_id, @PathVariable Long professor_id){

       return subjectServ.assignSubjectToProfessor(subject_id,professor_id);


    }



}

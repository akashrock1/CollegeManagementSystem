package com.example.w3.CollegeMangementSystem.Controller;


import com.example.w3.CollegeMangementSystem.DTO.ProfessorDTO;
import com.example.w3.CollegeMangementSystem.Services.ProfessorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/professor")
public class ProfessorController {

    private final ProfessorService professorServ;

    public ProfessorController(ProfessorService professorServ) {
        this.professorServ = professorServ;
    }

    @GetMapping("/{id}")
    public ProfessorDTO getProfessor(@PathVariable Long id){
        return professorServ.getProfessor(id);
    }

    @PostMapping
    public ProfessorDTO addProfessor(@RequestBody ProfessorDTO body){

        return professorServ.addProfessor(body);


    }

    @DeleteMapping("/{id}")
    public String deleteProfessor(@PathVariable Long id){
        return professorServ.deleteProfessor(id);
    }

    @PutMapping("/{professor_id}/student/{student_id}")
    public void assignStudentToProfessor(@PathVariable Long professor_id,@PathVariable Long student_id){
        professorServ.assignStudentToProfessor(professor_id,student_id);
    }

}

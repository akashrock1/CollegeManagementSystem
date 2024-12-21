package com.example.w3.CollegeMangementSystem.Services;


import com.example.w3.CollegeMangementSystem.DTO.ProfessorDTO;
import com.example.w3.CollegeMangementSystem.DTO.StudentDTO;
import com.example.w3.CollegeMangementSystem.Entity.ProfessorEntity;
import com.example.w3.CollegeMangementSystem.Entity.StudentEntity;
import com.example.w3.CollegeMangementSystem.Exceptions.ResourceNotFoundException;
import com.example.w3.CollegeMangementSystem.Repository.ProfessorRepository;
import com.example.w3.CollegeMangementSystem.Repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepo;

    private final StudentRepository studentRepo;
    @Autowired
    private ModelMapper mapper;

    private Logger log= LoggerFactory.getLogger(ProfessorService.class);

    public ProfessorService(ProfessorRepository professorRepo, StudentRepository studentRepo) {
        this.professorRepo = professorRepo;
        this.studentRepo = studentRepo;
    }

    public ProfessorDTO getProfessor(Long id) {
        log.info("Executing getProfessor() Method : ");
        ProfessorEntity professor=professorRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Professor Not found with id : "+id));
        log.info("Successfully get the professor details .");
        ProfessorDTO data=mapper.map(professor, ProfessorDTO.class);
        return data;

    }

    public ProfessorDTO addProfessor(ProfessorDTO body) {
        log.info("Executing addProfessor() Method :");
        ProfessorEntity data=mapper.map(body, ProfessorEntity.class);
        professorRepo.save(data);
        log.info("Successfully Added the professor details .");
        return mapper.map(data, ProfessorDTO.class);
    }

    public String deleteProfessor(Long id) {
        log.info("Executing deleteprofessor() Method :");
        ProfessorEntity professor=professorRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Professor not found having Id :"+id));
        professorRepo.delete(professor);
        log.info("Successfully Deleted the Professor Data");
        return "Deleted Professor having Id : "+id;

    }

    public Optional<StudentDTO> assignStudentToProfessor(Long professorId, Long studentId) {
        log.info("Executing assignStudentToProfessor() Method :");
        Optional<ProfessorEntity> professor= Optional.ofNullable(professorRepo.findById(professorId).orElseThrow(() -> new ResourceNotFoundException("Professor not found having Id : " + studentId)));
        Optional<StudentEntity> student= Optional.ofNullable(studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student not found havig Id :" + studentId)));

        return professor.flatMap(prof->
                student.map(studs->{
                    studs.getProfessor().add(prof);
                    studentRepo.save(studs);
                    log.info("Successfully assigned Professors details to student");
                    return mapper.map(student, StudentDTO.class);
                })

        );

    }


}

package com.example.w3.CollegeMangementSystem.Services;


import com.example.w3.CollegeMangementSystem.DTO.SubjectDTO;
import com.example.w3.CollegeMangementSystem.Entity.ProfessorEntity;
import com.example.w3.CollegeMangementSystem.Entity.SubjectEntity;
import com.example.w3.CollegeMangementSystem.Exceptions.ResourceNotFoundException;
import com.example.w3.CollegeMangementSystem.Repository.ProfessorRepository;
import com.example.w3.CollegeMangementSystem.Repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepo;
    private final ProfessorRepository professorRepo;

    @Autowired
    private ModelMapper mapper;
    private Logger log= LoggerFactory.getLogger(SubjectService.class);


    public SubjectService(SubjectRepository subjectRepo, ProfessorRepository professorRepo) {
        this.subjectRepo = subjectRepo;
        this.professorRepo = professorRepo;
    }

    public SubjectDTO getSubject(Long id) {
        log.info("Executing getSubject Method :");
       SubjectEntity subject=subjectRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Subject Not Found With Id :"+id));
       log.info("Successfully received the subject details");
       return mapper.map(subject, SubjectDTO.class);
    }

    public SubjectDTO postSubject(SubjectDTO body){
        log.info("Executing postSubject Method :");
        SubjectEntity data=mapper.map(body,SubjectEntity.class);
        subjectRepo.save(data);
        log.info("successfully sent the Subject Data  to server ");
        return mapper.map(data,SubjectDTO.class);
    }

    public Optional<SubjectEntity> assignSubjectToProfessor(Long subjectId, Long professorId) {
        log.info("Assigning Subject to Professor : ");
        Optional<SubjectEntity> subjects= Optional.ofNullable(subjectRepo.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Subject not Found having Id : " + subjectId)));
        Optional<ProfessorEntity> professors = Optional.ofNullable(professorRepo.findById(professorId).orElseThrow(() -> new ResourceNotFoundException("Professor not found having Id : " + professorId)));

        return subjects.flatMap(sub ->
                professors.map(prof ->{
                    sub.setProfessor(prof);
                    subjectRepo.save(sub);
                    log.info("Successfully Assigned the professor to respective Subjects.");
                    return subjects;
                })
                ).orElse(null);

    }
}

package com.example.w3.CollegeMangementSystem.Services;


import com.example.w3.CollegeMangementSystem.DTO.StudentDTO;
import com.example.w3.CollegeMangementSystem.Entity.StudentEntity;
import com.example.w3.CollegeMangementSystem.Entity.SubjectEntity;
import com.example.w3.CollegeMangementSystem.Exceptions.ResourceNotFoundException;
import com.example.w3.CollegeMangementSystem.Repository.StudentRepository;
import com.example.w3.CollegeMangementSystem.Repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

   private final StudentRepository studentRepo;
    private final SubjectRepository subjectRepo;

    @Autowired
    private ModelMapper mapper;

    Logger log=LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepo, SubjectRepository subjectRepo) {
        this.studentRepo = studentRepo;
        this.subjectRepo = subjectRepo;
    }

    public StudentDTO addStudent(StudentDTO body) {
        log.info("Executing addStudent Method : ");
        StudentEntity data=mapper.map(body, StudentEntity.class);
        studentRepo.save(data);
        log.info("Successfully added Student Data");
        return mapper.map(data, StudentDTO.class);
    }

    public StudentDTO getStudent(Long id)  {
        log.info("Executing getStudent method : ");
        StudentEntity student=studentRepo.findById(id)
                .orElseThrow(()-> {
                            log.error("Not able to find the student id");
                            return new ResourceNotFoundException("Student not found With Id : " + id);
                        }
                );

        StudentDTO y=mapper.map(student, StudentDTO.class);
        log.info("Successfully fetched the Student details for the requested Id");
        return y;
    }


    public Optional<StudentDTO> assignSubjecttoStudent(Long studentId, Long subjectId) {
        log.info("Executing assignSubjecttoStudent() method : ");
          Optional<StudentEntity> students = Optional.ofNullable(studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student Not found having Id :" + studentId)));
          Optional<SubjectEntity> subjects= Optional.ofNullable(subjectRepo.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Subject Not found having Id :" + subjectId)));

          return students.flatMap(student ->
                  subjects.map(subject ->{
                     student.getSubjects().add(subject);
                     studentRepo.save(student);
                     log.info("Successfully Assigned subjects to student");
                     return mapper.map(student,StudentDTO.class);
                  }));

    }

    public String deleteStudent(Long id) {
        log.info("Executing deleteStudent() Method : ");
        StudentEntity student=studentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Student Not found having Id : "+id));
        studentRepo.delete(student);
        log.info("Successfully Deleted the Student having Id : "+id);
        return "Deleted Student having Id : "+id;
    }

    public StudentDTO updateStudent(Long id,StudentDTO new_student) {
        log.info("executing updateStudent Method : ");
        StudentEntity student_old =studentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Student Id "+id +" Not found"));

        new_student.setId(id);

        mapper.map(new_student,student_old);

        StudentEntity p=studentRepo.save(student_old);
        log.info("Successfully updated the Student Data.");
        return mapper.map(p,StudentDTO.class);

    }
}

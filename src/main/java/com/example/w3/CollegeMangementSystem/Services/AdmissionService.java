package com.example.w3.CollegeMangementSystem.Services;

import com.example.w3.CollegeMangementSystem.DTO.AdmissionRecordDTO;
import com.example.w3.CollegeMangementSystem.Entity.AdmissionRecordEntity;
import com.example.w3.CollegeMangementSystem.Entity.StudentEntity;
import com.example.w3.CollegeMangementSystem.Exceptions.ResourceNotFoundException;
import com.example.w3.CollegeMangementSystem.Repository.AdmissionRecordRepository;
import com.example.w3.CollegeMangementSystem.Repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdmissionService {

    private final AdmissionRecordRepository admissionRepo;
    private final StudentRepository studentRepo;

    @Autowired
    private ModelMapper mapper;

    Logger log= LoggerFactory.getLogger(AdmissionService.class);

    public AdmissionService(AdmissionRecordRepository admissionRepo, StudentRepository studentRepo) {
        this.admissionRepo = admissionRepo;
        this.studentRepo = studentRepo;
    }


    public AdmissionRecordDTO addAdmissionRecord(AdmissionRecordDTO body){
        log.info("Executing addAdmissionRecord() method :");
        AdmissionRecordEntity data=mapper.map(body,AdmissionRecordEntity.class);
        admissionRepo.save(data);
        log.info("Successfully added the details info Admission Record");
        return mapper.map(data,AdmissionRecordDTO.class);
    }

    public AdmissionRecordDTO getAdmissionRecord(Long id){
        log.info("Executing getAdmissionRecord() Method :");
        AdmissionRecordEntity data=admissionRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("AdmissionRecord Not Found for Requested Id :"+id));
        log.info("Successfully fetched the Admissiondetails  :"+data);
        return mapper.map(data,AdmissionRecordDTO.class);
    }


    public Optional<AdmissionRecordEntity> addStudenttoAdmissionRecord(Long admissionId, Long studentId) {
        log.info("Executing addStudenttoAdmissionRecord() method :");
       Optional<AdmissionRecordEntity> admission= Optional.ofNullable(admissionRepo.findById(admissionId).orElseThrow(() -> new ResourceNotFoundException("admissionrecord not found having Id :" + admissionId)));
       StudentEntity student=studentRepo.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student not found having Id :"+studentId));

       return admission.flatMap(adm->
               {
                   adm.setStudent(student);
                   admissionRepo.save(adm);
                   log.info("Successfully assigned student to admissionrecord");
                   return admission;
               }
               );


    }
}

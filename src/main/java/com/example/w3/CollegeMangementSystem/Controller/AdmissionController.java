package com.example.w3.CollegeMangementSystem.Controller;


import com.example.w3.CollegeMangementSystem.DTO.AdmissionRecordDTO;
import com.example.w3.CollegeMangementSystem.Entity.AdmissionRecordEntity;
import com.example.w3.CollegeMangementSystem.Services.AdmissionService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admission")
public class AdmissionController {

    private final AdmissionService admissionServ;

    public AdmissionController(AdmissionService admissionServ) {
        this.admissionServ = admissionServ;
    }

    @GetMapping("/{id}")
    public AdmissionRecordDTO getAdmissionRecord(@PathVariable Long id){

        return admissionServ.getAdmissionRecord(id);

    }

    @PostMapping
    public AdmissionRecordDTO addAdmissionRecord(@RequestBody AdmissionRecordDTO body){
        return admissionServ.addAdmissionRecord(body);
    }

    @PutMapping("/{admission_id}/student/{student_id}")
    public Optional<AdmissionRecordEntity> addStudenttoAdmissionRecord(@PathVariable Long admission_id, @PathVariable Long student_id){
        return admissionServ.addStudenttoAdmissionRecord(admission_id,student_id);
    }


}

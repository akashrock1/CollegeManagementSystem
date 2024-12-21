package com.example.w3.CollegeMangementSystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionRecordDTO {
    Long id;
    int fees;
    StudentDTO student;
}

package com.example.w3.CollegeMangementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    Long id;
    String name;
    List<ProfessorDTO> professor;
    List<SubjectDTO> subjects;

}

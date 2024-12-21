package com.example.w3.CollegeMangementSystem.Repository;

import com.example.w3.CollegeMangementSystem.Entity.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity,Long> {
}

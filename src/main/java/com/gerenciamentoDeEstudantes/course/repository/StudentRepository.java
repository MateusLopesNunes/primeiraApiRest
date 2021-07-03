package com.gerenciamentoDeEstudantes.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamentoDeEstudantes.course.model.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

package com.gerenciamentoDeEstudantes.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamentoDeEstudantes.course.dto.MessageResponseDTO;
import com.gerenciamentoDeEstudantes.course.model.entities.Student;
import com.gerenciamentoDeEstudantes.course.repository.StudentRepository;

@Service
public class StudentService {

	private StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
		//@Autowired injeta dependencia no repositorio de estudantes
		//o uso em construtor é interesante para testes unitarios
	}

	public MessageResponseDTO create(Student obj) {
		Student student = studentRepository.save(obj);
		return MessageResponseDTO.builder().message("registered student, id: " + student.getPlate()).build();
		// adciona uma estudante ao banco, e retorna a mensagem acima
		// o builder foi usado para estanciar automaticamente a classe
		// MessageResponseDTO;
		// importante para testes unitarios
	}	
}
package com.gerenciamentoDeEstudantes.course.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamentoDeEstudantes.course.dto.MessageResponseDTO;
import com.gerenciamentoDeEstudantes.course.dto.request.StudentDTO;
import com.gerenciamentoDeEstudantes.course.mapper.StudentMapper;
import com.gerenciamentoDeEstudantes.course.model.entities.Student;
import com.gerenciamentoDeEstudantes.course.repository.StudentRepository;

@Service
public class StudentService {

	private StudentRepository studentRepository;
	
	private StudentMapper studentMapper = StudentMapper.INSTANCE;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
		//@Autowired injeta dependencia no repositorio de estudantes
		//o uso em construtor é interesante para testes unitarios
	}

	public MessageResponseDTO create(StudentDTO obj) {
		Student studentConverted = studentMapper.toModel(obj);
		Student student = studentRepository.save(studentConverted);
		return MessageResponseDTO.builder().message("registered student, id: " + student.getPlate()).build();
		// adciona uma estudante ao banco, e retorna a mensagem acima
		// o builder foi usado para estanciar automaticamente a classe
		// MessageResponseDTO;
		// importante para testes unitarios
	}
	
	public List<StudentDTO> listAll() {
		List<Student> studAll = studentRepository.findAll();
		return studAll.stream().map(studentMapper::toDTO).collect(Collectors.toList());
	}
}

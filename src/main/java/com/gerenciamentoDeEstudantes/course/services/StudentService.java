package com.gerenciamentoDeEstudantes.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
		Student CreateStudent = studentMapper.toModel(obj);
		studentRepository.save(CreateStudent);
		return messageResponse(obj.getPlate(), "Registered student with plate: ");
		// adciona uma estudante ao banco, e retorna a mensagem acima
		// o builder foi usado para estanciar automaticamente a classe
		// MessageResponseDTO;
		// importante para testes unitarios
	}
	
	public List<StudentDTO> listAll() {
		List<Student> studAll = studentRepository.findAll();
		return studAll.stream().map(studentMapper::toDTO).collect(Collectors.toList());
	}
	
	public StudentDTO findByPlate(Long plate) throws StudentNotFoundExceptions {
		Student FindStudent = verifyIfExists(plate);

		//findById retorna um optional.
		//Retorna uma entidade, dps da validação do Optional.
		return studentMapper.toDTO(FindStudent);
	}

	public MessageResponseDTO updateStudent(Long plate, StudentDTO obj) {
		new StudentNotFoundExceptions(plate);
		Student studentUpdate = studentMapper.toModel(obj);
		studentRepository.save(studentUpdate);
		return messageResponse(obj.getPlate(), "Update student with plate: ");
	}

	public void deleteById(Long plate) throws StudentNotFoundExceptions {
		verifyIfExists(plate);
		
		studentRepository.deleteById(plate);
	}
	
	private Student verifyIfExists(Long plate) throws StudentNotFoundExceptions {
		return studentRepository.findById(plate)
		.orElseThrow(() -> new StudentNotFoundExceptions(plate));
	}
	
	private MessageResponseDTO messageResponse(Long plate, String message) {
		return MessageResponseDTO.builder().message(message + plate).build();
	}
}

package com.gerenciamentoDeEstudantes.course.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamentoDeEstudantes.course.dto.MessageResponseDTO;
import com.gerenciamentoDeEstudantes.course.dto.request.StudentDTO;
import com.gerenciamentoDeEstudantes.course.model.entities.Student;
import com.gerenciamentoDeEstudantes.course.services.StudentNotFoundExceptions;
import com.gerenciamentoDeEstudantes.course.services.StudentService;

@RestController
@RequestMapping("/api/v1/student")
public class StudentyController {
	
	private StudentService studentService;
	
	@Autowired
	public StudentyController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createStudent(@RequestBody @Valid StudentDTO obj) {
		return studentService.create(obj);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<StudentDTO> listAll() {
		return studentService.listAll();
	}
	
	@GetMapping("/{plate}") //Informa que o sistema vai chamar este método quando informar o plate com parametro em uma requisição get
	@ResponseStatus(HttpStatus.OK)
	public StudentDTO findById(@PathVariable Long plate) throws StudentNotFoundExceptions { 
		//@PathVariable indica que o caminho pode ser variavel
		return studentService.findByPlate(plate);
	}
	
	@PutMapping
	public MessageResponseDTO updateStudent(@RequestBody @Valid StudentDTO obj) {
		return studentService.updateStudent(obj);
	}
	
	@DeleteMapping("/{plate}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long plate) throws StudentNotFoundExceptions {
		studentService.deleteById(plate);
	}
}

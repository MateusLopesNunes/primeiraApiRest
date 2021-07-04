package com.gerenciamentoDeEstudantes.course.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundExceptions extends Exception{
	
	public PersonNotFoundExceptions(Long number) {
		super("Student not found with id: " + number);
	}
}

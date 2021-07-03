package com.gerenciamentoDeEstudantes.course.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.gerenciamentoDeEstudantes.course.dto.request.StudentDTO;
import com.gerenciamentoDeEstudantes.course.model.entities.Student;

@Mapper
public interface StudentMapper {
	
	StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
	//cria uma implemetação da interface, auomaticamente
	
	@Mapping(source = "birthDate", target = "birthDate", dateFormat = "dd/MM/yyyy")
	Student toModel(StudentDTO obj);

	StudentDTO toDTO(Student obj);
}

package com.gerenciamentoDeEstudantes.course.dto.request;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

	private Long plate;

	@NotEmpty
	@Size(min = 3, max = 100)
	private String firstName;

	@NotEmpty
	@Size(min = 3, max = 100)
	private String lastName;

	@NotEmpty
	@Size(min = 10, max = 150)
	private String curso;

	@NotEmpty
	@CPF
	private String cpf;

	private String birthDate;

	@NotEmpty
	@Valid
	private Set<PhoneDTO> phones = new HashSet<>();

	@Valid
	private UniversityDTO university;
}

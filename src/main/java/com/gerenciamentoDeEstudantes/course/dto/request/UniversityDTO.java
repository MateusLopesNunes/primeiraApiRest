package com.gerenciamentoDeEstudantes.course.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDTO {

	private Long id;

	@NotEmpty
	@Size(min = 2, max = 150)
	private String name;
}

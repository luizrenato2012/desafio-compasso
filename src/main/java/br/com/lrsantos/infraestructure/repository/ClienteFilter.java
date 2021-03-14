package br.com.lrsantos.infraestructure.repository;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ClienteFilter {

	@NotEmpty(message = "Nome Completo obrigatorio")
	private String nomeCompleto;
}

package br.com.lrsantos.infraestructure.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CidadeFilter {

	private String nome;
	
	private String estado;
}

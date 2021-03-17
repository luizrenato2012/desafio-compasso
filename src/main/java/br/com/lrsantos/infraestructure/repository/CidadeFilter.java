package br.com.lrsantos.infraestructure.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadeFilter {

	private String nome;
	private String estado;
}

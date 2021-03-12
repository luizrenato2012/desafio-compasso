package br.com.lrsantos.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Nome da cidade é obrigatório")
	private String nome;
	
	@NotNull(message = "Estado da cidade é obrigatório")
	private String estado;
}

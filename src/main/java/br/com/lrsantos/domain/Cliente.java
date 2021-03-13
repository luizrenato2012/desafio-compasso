package br.com.lrsantos.domain;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nome_completo")
	private String nomeCompleto;
	
	@Enumerated
	private Sexo sexo;
	
	@Column(name="data_nascimento")
	private LocalDate dataNascimento;
	
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;
	
//	private Integer idade;
	
	public Integer getIdade() {
		if (this.dataNascimento!=null) {
			Period period = Period.between(dataNascimento, LocalDate.now());
			return period.getYears();
		}
		return null;
	}
//	cidade onde mora.
}

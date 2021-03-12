package br.com.lrsantos.ui.dto;

import org.springframework.beans.BeanUtils;

import br.com.lrsantos.domain.Cidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CidadeDTO {

	private Long id;
	private String nome;
	private String estado;
	
	public Cidade toCidade() {
		return new Cidade(this.id, this.nome, this.estado);
	}
	
	public CidadeDTO of(Cidade cidade) {
		BeanUtils.copyProperties(cidade, this);
		return this;
	}
}

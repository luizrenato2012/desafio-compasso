package br.com.lrsantos.ui.dto;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import br.com.lrsantos.domain.Cidade;
import br.com.lrsantos.domain.Cliente;
import br.com.lrsantos.infraestructure.util.ValueObjectUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

	private Long id;
	private String nomeCompleto;
	private String sexo;
	private LocalDate dataNascimento;
	private CidadeDTO cidade;
	private Integer idade;
	
	public static ClienteDTO of(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO();
		BeanUtils.copyProperties(cliente, dto, "sexo", "cidade");
		dto.sexo =  ValueObjectUtil.getValueOrNull(cliente.getSexo(), cliente.getSexo().getCodigo());
		Cidade cidade = cliente.getCidade();
		dto.cidade = ValueObjectUtil.getValueOrNull(cidade, CidadeDTO.of(cidade));
		return dto;
	}
	
}

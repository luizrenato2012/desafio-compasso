package br.com.lrsantos.ui.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import br.com.lrsantos.domain.Cidade;
import br.com.lrsantos.domain.Cliente;
import br.com.lrsantos.domain.Sexo;
import br.com.lrsantos.infraestructure.util.ValueObjectUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

	private Long id;
	
	@NotEmpty(message = "Nome Completo é obrigatorio")
	private String nomeCompleto;
	
	@NotEmpty(message = "Sexo é obrigatorio")
	private String sexo;
	
	private LocalDate dataNascimento;
	
	@NotNull(message = "Cidade é obrigatoria")
	private CidadeDTO cidade;
	
	private Integer idade;
	
	public static ClienteDTO of(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO();
		BeanUtils.copyProperties(cliente, dto, "sexo", "cidade");
		dto.sexo =  ValueObjectUtil.getValueOrNull(cliente.getSexo(), cliente.getSexo().toString());
		Cidade cidade = cliente.getCidade();
		dto.cidade = ValueObjectUtil.getValueOrNull(cidade, CidadeDTO.of(cidade));
		return dto;
	}
	
	public Cliente to() {
		Cliente cliente = new Cliente();
		BeanUtils.copyProperties(this, cliente, "cidade", "sexo");
		Cidade cidade = this.cidade.toCidade();
		cliente.setCidade(cidade);
		cliente.setSexo(Sexo.valueOf(this.sexo));
		return cliente;
	}
	
	
}

package br.com.lrsantos.application.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.lrsantos.application.exception.ObjetoNaoEncontradoException;
import br.com.lrsantos.application.exception.RequestInvalidoException;
import br.com.lrsantos.domain.Cidade;
import br.com.lrsantos.domain.Cliente;
import br.com.lrsantos.infraestructure.repository.CidadeRepository;
import br.com.lrsantos.infraestructure.repository.ClienteRepository;
import br.com.lrsantos.ui.dto.CidadeDTO;
import br.com.lrsantos.ui.dto.ClienteDTO;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<ClienteDTO> listaTodos() {
		return clienteRepository.findAll().stream()
			.map(cliente -> ClienteDTO.of(cliente))
			.collect(Collectors.toList());
	}
	
	public List<ClienteDTO> listaPorNome(String nome) {
		List<Cliente> clientes = lista(nome);
		return clientes.stream()
			.map(cliente -> ClienteDTO.of(cliente))
			.collect(Collectors.toList());
	}
	
	private List<Cliente> lista(String nome) {
		Cliente clienteExample = new Cliente();
		clienteExample.setNomeCompleto(nome);
		return clienteRepository.findAll(
				ExampleHelper.criaExampleDePesquisaString("nomeCompleto", clienteExample));
	}
	
	public ClienteDTO buscaPorId(Long id) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		Cliente cliente = optional.orElseThrow(()-> new ObjetoNaoEncontradoException("Cliente id [" + id + "] não encontrado"));
		return ClienteDTO.of(cliente);
	}
	
	public ClienteDTO inclui(ClienteDTO dto) {
		CidadeDTO cidadeDTO = dto.getCidade();
		Cidade cidade = cidadeRepository.findById(cidadeDTO.getId())
				.orElseThrow(() -> new RequestInvalidoException("Cidade " + cidadeDTO.getId() + 
						" nao encontrada"));
		Cliente cliente = dto.to();
		cliente.setCidade(cidade);
		cliente = clienteRepository.save(cliente);
		return ClienteDTO.of(cliente);
	}

	public void exclui(Long id) {
		try {
			clienteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RequestInvalidoException("Cliente id ["+ id + "] inexistente.");
		}
	}

	public ClienteDTO alteraNome(Long id, Map<String, String> mapNome) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente id["+ id + "] nao encontrado"));
		cliente.setNomeCompleto(mapNome.get("nomeCompleto"));
		Cliente clienteSalvo = clienteRepository.save(cliente);
		return ClienteDTO.of(clienteSalvo);
	}
	
}

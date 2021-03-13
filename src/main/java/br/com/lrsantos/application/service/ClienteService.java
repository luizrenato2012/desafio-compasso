package br.com.lrsantos.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lrsantos.application.exception.ObjetoNaoEncontradoException;
import br.com.lrsantos.domain.Cliente;
import br.com.lrsantos.infraestructure.repository.ClienteRepository;
import br.com.lrsantos.ui.dto.ClienteDTO;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public List<ClienteDTO> listaTodos() {
		return repository.findAll().stream()
			.map(cliente -> ClienteDTO.of(cliente))
			.collect(Collectors.toList());
	}
	
	public List<ClienteDTO> buscaPor(String nome) {
		List<Cliente> clientes = busca(nome);
		return clientes.stream()
			.map(cliente -> ClienteDTO.of(cliente))
			.collect(Collectors.toList());
	}
	
	private List<Cliente> busca(String nome) {
		Cliente clienteExample = new Cliente();
		clienteExample.setNomeCompleto(nome);
		return repository.findAll(
				ExampleHelper.criaExampleDePesquisaString(nome, clienteExample));
	}
	
	public ClienteDTO buscaPor(Long id) {
		Optional<Cliente> optional = repository.findById(id);
		Cliente cliente = optional.orElseThrow(()-> new ObjetoNaoEncontradoException("Cliente id [" + id + "] não encontrado"));
		return ClienteDTO.of(optional.get());
	}
	
}

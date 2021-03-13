package br.com.lrsantos.ui.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lrsantos.application.service.ClienteService;
import br.com.lrsantos.ui.dto.ClienteDTO;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	private ClienteService service;
	
	@GetMapping("/nome/{nome}")
	public List<ClienteDTO> buscaPorNome(@PathVariable String nome) {
		return service.buscaPor(nome);
	}
	
	@GetMapping("/id/{id}")
	public ClienteDTO buscaPorNome(@PathVariable Long id) {
		return service.buscaPor(id);
	}
	
}

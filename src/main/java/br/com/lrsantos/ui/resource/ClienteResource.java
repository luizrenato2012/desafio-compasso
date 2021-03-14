package br.com.lrsantos.ui.resource;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.lrsantos.application.service.ClienteService;
import br.com.lrsantos.infraestructure.repository.ClienteFilter;
import br.com.lrsantos.ui.dto.ClienteDTO;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listaPorNome(@Valid ClienteFilter filter) {
		return ResponseEntity.ok(service.listaPorNome(filter.getNomeCompleto()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> buscaPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.buscaPorId(id));
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> inclui(@RequestBody @Valid ClienteDTO clienteDTO) {
		return ResponseEntity.ok(service.inclui(clienteDTO));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void exclui(@PathVariable Long id) {
		service.exclui(id);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ClienteDTO> atualizaNome(@PathVariable Long id, 
			@RequestBody Map<String,String> mapNome) {
		return ResponseEntity.ok(service.alteraNome(id, mapNome));
	}
	
}

package br.com.lrsantos.ui.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lrsantos.application.service.CidadeService;
import br.com.lrsantos.infraestructure.repository.CidadeFilter;
import br.com.lrsantos.ui.dto.CidadeDTO;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeService service;

	@GetMapping
	public List<CidadeDTO> busca(CidadeFilter filter) {
		return service.buscaPor(filter);
	}
	
	@PostMapping
	public CidadeDTO inclui(@RequestBody @Valid CidadeDTO dto) {
		return service.inclui(dto);
	}
}

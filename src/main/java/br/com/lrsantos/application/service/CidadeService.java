package br.com.lrsantos.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import br.com.lrsantos.domain.Cidade;
import br.com.lrsantos.infraestructure.repository.CidadeFilter;
import br.com.lrsantos.infraestructure.repository.CidadeRepository;
import br.com.lrsantos.ui.dto.CidadeDTO;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repository;
	
	public List<CidadeDTO> listaTodas() {
		return repository.findAll().stream()
			.map(cidade -> CidadeDTO.of(cidade))
			.collect(Collectors.toList());
	}
	
	public CidadeDTO inclui(CidadeDTO cidadeDTO) {
		Cidade cidade = repository.save(cidadeDTO.toCidade());
		return CidadeDTO.of(cidade);
	}
	
	public List<CidadeDTO> buscaPorNome(String nome) {
		Cidade argumentoCidade = new Cidade();
		argumentoCidade.setNome(nome);
		return busca("nome", argumentoCidade);
	}
	
	public List<CidadeDTO> buscaPorEstado(String estado) {
		Cidade argumentoCidade = new Cidade();
		argumentoCidade.setEstado(estado);
		return busca("estado", argumentoCidade);
	}
	
	private List<CidadeDTO> busca(String campoPesquisa, Cidade argumentoCidade) {
		Example<Cidade> example = ExampleHelper.criaExampleDePesquisaString(campoPesquisa, argumentoCidade);
		return repository.findAll(example).stream()
				.map(cidade -> CidadeDTO.of(cidade))
				.collect(Collectors.toList());
	}
	

}

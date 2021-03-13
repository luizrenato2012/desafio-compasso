package br.com.lrsantos.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
			.map(cidade -> new CidadeDTO().of(cidade))
			.collect(Collectors.toList());
	}
	
	public CidadeDTO inclui(CidadeDTO cidadeDTO) {
		Cidade cidade = repository.save(cidadeDTO.toCidade());
		return new CidadeDTO().of(cidade);
	}
	
	public List<CidadeDTO> busca(CidadeFilter filter) {
		Example example = criaExample(filter);
		return ((List<Cidade>)repository.findAll(example)).stream()
				.map(cidade -> new CidadeDTO().of(cidade))
				.collect(Collectors.toList());
	}

	private Example criaExample(CidadeFilter filter) {
		Cidade cidade = new Cidade(null, filter.getNome(), filter.getEstado()); 
		String campoPesquisa = !ObjectUtils.isEmpty(filter.getNome()) ? "nome" : "estado";
//		ExampleMatcher matcher = ExampleMatcher.matchingAll()
//				.withIgnoreCase(campoPesquisa)
//				.withStringMatcher(StringMatcher.CONTAINING);
//		return Example.of(cidade, matcher);
		return new ExampleHelper().criaExampleDePesquisaString(campoPesquisa, cidade);
	}

}

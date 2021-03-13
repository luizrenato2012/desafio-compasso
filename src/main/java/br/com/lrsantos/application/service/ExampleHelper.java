package br.com.lrsantos.application.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

public class ExampleHelper {

	public static <T> Example<T> criaExampleDePesquisaString(String campoPesquisa, T t) {
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withIgnoreCase(campoPesquisa)
				.withStringMatcher(StringMatcher.CONTAINING);
		return Example.of(t, matcher);
	}
}

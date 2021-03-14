package br.com.lrsantos.application;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.lrsantos.domain.Cidade;
import br.com.lrsantos.domain.Cliente;
import br.com.lrsantos.domain.Sexo;
import br.com.lrsantos.infraestructure.repository.CidadeRepository;
import br.com.lrsantos.infraestructure.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DatabaseInitializer implements CommandLineRunner{
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void run(String... args) throws Exception {
		log.info(">>>Iniciando geração de dados de exemplo");
		Cidade ceilandia = Cidade.builder().nome("Ceilandia").estado("DF").build();
		Cidade taguatinga =	Cidade.builder().nome("Taguatinga").estado("DF").build();
		Cidade goiania=	Cidade.builder().nome("Goiania").estado("GO").build();
		
		cidadeRepository.save(ceilandia);
		cidadeRepository.save(taguatinga);
		cidadeRepository.save(goiania);
		
		Cliente briana= Cliente.builder().nomeCompleto("Brianna Fidalgo")
				.cidade(ceilandia)
				.dataNascimento(LocalDate.of(2000, Month.APRIL, 10))
				.sexo(Sexo.F)
				.build();
		
		Cliente diva= Cliente.builder().nomeCompleto("Diva Machado ")
				.cidade(taguatinga)
				.dataNascimento(LocalDate.of(1995, Month.OCTOBER, 20))
				.sexo(Sexo.F)
				.build();
		Cliente nelson= Cliente.builder().nomeCompleto("Nélson Bingre ")
				.cidade(goiania)
				.dataNascimento(LocalDate.of(1980, Month.JANUARY, 10))
				.sexo(Sexo.M)
				.build();
		clienteRepository.saveAll(Arrays.asList(briana,diva,nelson ));
		log.info(">>>Finalizada geração de dados de exemplo");
	}

}

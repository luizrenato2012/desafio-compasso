package br.com.lrsantos.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lrsantos.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}

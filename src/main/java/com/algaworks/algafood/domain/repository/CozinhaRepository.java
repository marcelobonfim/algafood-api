package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {
	
	public List<Cozinha> findByNome(String nome);
	
	public List<Cozinha> findByNomeContaining(String nome);
	
	public boolean existsByNome(String nome);

}

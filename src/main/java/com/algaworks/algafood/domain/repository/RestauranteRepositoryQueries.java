package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.algaworks.algafood.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> findComJPQL(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	List<Restaurante> findComCriteria(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	List<Restaurante> findComFreteGratis(String nome);

}
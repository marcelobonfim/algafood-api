package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	public Optional<Produto> findByRestauranteIdAndId(@PathVariable Long idRestaurante, @PathVariable Long idProduto);
	
	List<Produto> findByRestaurante(Restaurante restaurante);
}

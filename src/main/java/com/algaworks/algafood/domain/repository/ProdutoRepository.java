package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries {
	
	public Optional<Produto> findByRestauranteIdAndId(@PathVariable Long idRestaurante, @PathVariable Long idProduto);
	
	List<Produto> findTodosByRestaurante(Restaurante restaurante);
	
	@Query("from Produto p where p.ativo = true and p.restaurante = :restaurante")
	List<Produto> findAtivosByRestaurante(Restaurante restaurante);
	
	@Query("select f from FotoProduto f join f.produto p "
			+ "where p.restaurante.id = :restauranteId and f.produto.id = :produtoId")
	public Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);
}

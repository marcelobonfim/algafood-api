package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	public Produto buscarOuFalhar(Long idRestaurante, Long idProduto) {
		return produtoRepository.findByRestauranteIdAndId(idRestaurante, idProduto).orElseThrow(() -> new ProdutoNaoEncontradoException(idRestaurante, idProduto));
	}

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

}

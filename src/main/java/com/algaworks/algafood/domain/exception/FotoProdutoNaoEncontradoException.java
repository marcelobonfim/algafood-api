package com.algaworks.algafood.domain.exception;

public class FotoProdutoNaoEncontradoException  extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public FotoProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public FotoProdutoNaoEncontradoException(Long idRestaurante, Long idProduto) {
		this(String.format("Não existe um cadastro de foto do produto com código %d para o restaurante de código %d", 
                idProduto, idRestaurante));
	}

}
